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

@Named("cursoBean")
@ViewScoped
public class CursoBean implements Serializable {

    @Inject private OrganizadorBean  organizador;
    @Inject private SimuladorBean    simulador;
    @Inject private SolucionadorBean solucionador;

    private List<ClaseEntity>    clases;
    private List<ProgresoEntity> progresos;
    private ClaseEntity          claseSeleccionada;
    private ProgresoEntity       progresoActual;
    private SolucionadorBean.Recomendacion recomendacion;

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

    /**
     * Selecciona una clase desde la tabla y carga su progreso.
     * Si no existe progreso, fuerza la inicializacion y vuelve a buscar.
     */
    public void seleccionarClase(ClaseEntity clase) {
        this.claseSeleccionada = clase;
        this.recomendacion     = null;

        this.progresoActual = organizador
                .obtenerProgresoPorClase(clase.getId())
                .orElseGet(() -> {
                    organizador.inicializarProgresoSiNecesario();
                    cargarDatos();
                    return organizador
                            .obtenerProgresoPorClase(clase.getId())
                            .orElse(null);
                });
    }

    /** Inicia la clase seleccionada (PENDIENTE -> EN_PROGRESO) */
    public void iniciarClase() {
        if (claseSeleccionada == null) return;
        try {
            progresoActual = simulador.iniciarSesion(
                    claseSeleccionada.getNumeroclase());
            addInfo("Clase " + claseSeleccionada.getNumeroclase() + " iniciada correctamente.");
            cargarDatos();
        } catch (Exception e) {
            addError("Error al iniciar: " + e.getMessage());
        }
    }

    /** Completa la clase con calificacion y porcentaje, y obtiene recomendacion */
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
            addInfo("Evaluacion registrada. " + recomendacion.getMensaje());
        } catch (Exception e) {
            addError("Error al completar: " + e.getMessage());
        }
    }

    /** Avanza a la siguiente clase si la recomendacion es AVANZAR */
    public void avanzar() {
        try {
            progresoActual    = simulador.avanzarSiguienteClase();
            claseSeleccionada = progresoActual.getClase();
            recomendacion     = null;
            cargarDatos();
            addInfo("Avanzando a la clase " + claseSeleccionada.getNumeroclase()
                    + ": " + claseSeleccionada.getTema());
        } catch (Exception e) {
            addError(e.getMessage());
        }
    }

    public double getPorcentajeGeneral() { return organizador.porcentajeGeneral(); }
    public long   getTotalCompletadas()  { return organizador.clasesCompletadas(); }

    public List<ClaseEntity>    getClases()              { return clases; }
    public List<ProgresoEntity> getProgresos()           { return progresos; }
    public ClaseEntity  getClaseSeleccionada()           { return claseSeleccionada; }
    public ProgresoEntity getProgresoActual()            { return progresoActual; }
    public SolucionadorBean.Recomendacion getRecomendacion() { return recomendacion; }
    public Double getCalificacionInput()                 { return calificacionInput; }
    public void   setCalificacionInput(Double v)         { this.calificacionInput = v; }
    public Double getPorcentajeInput()                   { return porcentajeInput; }
    public void   setPorcentajeInput(Double v)           { this.porcentajeInput = v; }

    private void addInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
}
