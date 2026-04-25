package co.edu.curso.jsf;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.model.ProgresoEntity;
import co.edu.curso.service.OrganizadorBean;
import co.edu.curso.service.SimuladorBean;
import co.edu.curso.service.SolucionadorBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * JSF ManagedBean — Vista principal del curso.
 * Controla la lista de clases, progreso y recomendaciones.
 */
@Named("cursoBean")
@ViewScoped
public class CursoBean implements Serializable {

    @Inject private OrganizadorBean organizador;
    @Inject private SimuladorBean   simulador;
    @Inject private SolucionadorBean solucionador;

    private List<ClaseEntity>   clases;
    private List<ProgresoEntity> progresos;
    private ClaseEntity          claseSeleccionada;
    private ProgresoEntity       progresoActual;
    private SolucionadorBean.Recomendacion recomendacion;

    // Campos del formulario de evaluación
    private Double calificacionInput;
    private Double porcentajeInput;

    @PostConstruct
    public void init() {
        organizador.inicializarProgresoSiNecesario();
        cargarDatos();
    }

    private void cargarDatos() {
        clases    = organizador.listarTodasLasClases();
        progresos = organizador.listarProgreso();
    }

    /** Selecciona una clase y carga su progreso */
    public void seleccionarClase(ClaseEntity clase) {
        this.claseSeleccionada = clase;
        this.recomendacion     = null;
        this.progresoActual    = organizador
                .obtenerProgresoPorClase(clase.getId())
                .orElse(null);
    }

    /** Inicia la clase seleccionada */
    public void iniciarClase() {
        if (claseSeleccionada == null) return;
        try {
            progresoActual = simulador.iniciarSesion(
                    claseSeleccionada.getNumeroclase());
            addInfo("Clase iniciada correctamente.");
            cargarDatos();
        } catch (Exception e) {
            addError("Error al iniciar: " + e.getMessage());
        }
    }

    /** Completa la clase con los datos del formulario y obtiene recomendación */
    public void completarYEvaluar() {
        if (claseSeleccionada == null
                || calificacionInput == null
                || porcentajeInput == null) {
            addError("Complete todos los campos.");
            return;
        }
        try {
            recomendacion = simulador.completarYEvaluar(
                    calificacionInput, porcentajeInput);
            progresoActual = organizador
                    .obtenerProgresoPorClase(claseSeleccionada.getId())
                    .orElse(progresoActual);
            cargarDatos();
            addInfo("Evaluación registrada. " + recomendacion.getMensaje());
        } catch (Exception e) {
            addError("Error al completar: " + e.getMessage());
        }
    }

    /** Avanza a la siguiente clase según la recomendación */
    public void avanzar() {
        try {
            progresoActual = simulador.avanzarSiguienteClase();
            claseSeleccionada = progresoActual.getClase();
            recomendacion = null;
            cargarDatos();
            addInfo("Avanzando a la clase " + claseSeleccionada.getNumeroclase());
        } catch (Exception e) {
            addError(e.getMessage());
        }
    }

    // ---- Helpers para la vista ----

    public double getPorcentajeGeneral() {
        return organizador.porcentajeGeneral();
    }

    public long getTotalCompletadas() {
        return organizador.clasesCompletadas();
    }

    // ---- Getters / Setters ----

    public List<ClaseEntity>   getClases()              { return clases; }
    public List<ProgresoEntity> getProgresos()          { return progresos; }
    public ClaseEntity  getClaseSeleccionada()          { return claseSeleccionada; }
    public ProgresoEntity getProgresoActual()           { return progresoActual; }
    public SolucionadorBean.Recomendacion getRecomendacion() { return recomendacion; }
    public Double getCalificacionInput()                { return calificacionInput; }
    public void   setCalificacionInput(Double v)        { this.calificacionInput = v; }
    public Double getPorcentajeInput()                  { return porcentajeInput; }
    public void   setPorcentajeInput(Double v)          { this.porcentajeInput = v; }

    // ---- Mensajes JSF ----
    private void addInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
}
