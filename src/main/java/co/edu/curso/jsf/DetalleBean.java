package co.edu.curso.jsf;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.model.PreguntaDTO;
import co.edu.curso.model.ProgresoEntity;
import co.edu.curso.service.CuestionarioService;
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
 * Bean para la página de detalle de una clase.
 * Flujo: Ver video → Completar cuestionario → Sistema calcula nota → Recomienda avanzar/reforzar
 */
@Named("detalleBean")
@ViewScoped
public class DetalleBean implements Serializable {

    // ---- Estados del flujo ----
    public enum Fase { VIDEO, CUESTIONARIO, RESULTADO }

    @Inject private OrganizadorBean    organizador;
    @Inject private SimuladorBean      simulador;
    @Inject private SolucionadorBean   solucionador;
    @Inject private CuestionarioService cuestionarioService;

    private Integer   numeroClase;
    private ClaseEntity    clase;
    private ProgresoEntity progreso;
    private SolucionadorBean.Recomendacion recomendacion;

    // ---- Cuestionario ----
    private List<PreguntaDTO> preguntas;
    private int[]   respuestasSeleccionadas; // una por pregunta
    private int     puntaje;                 // preguntas correctas
    private double  calificacion;            // 0.0 – 5.0
    private double  porcentajeProgreso;      // 0-100 según preguntas respondidas correctamente
    private boolean cuestionarioEnviado = false;

    // ---- Fase actual ----
    private Fase fase = Fase.VIDEO;

    @PostConstruct
    public void init() { /* videoClase se inyecta via f:viewParam */ }

    public void cargar() {
        if (numeroClase == null) return;
        organizador.inicializarProgresoSiNecesario();
        clase = organizador.buscarClasePorNumero(numeroClase).orElse(null);
        if (clase == null) return;
        progreso = organizador.obtenerProgresoPorClase(clase.getId()).orElse(null);

        // Si ya estaba completada, ir directo a resultado
        if (progreso != null && ProgresoEntity.Estado.COMPLETADO.equals(progreso.getEstado())) {
            calcularCalificacionDesdeProgreso();
            fase = Fase.RESULTADO;
        } else if (progreso != null && ProgresoEntity.Estado.EN_PROGRESO.equals(progreso.getEstado())) {
            // Volver al inicio del flujo para que el estudiante vea el video de nuevo
            progreso.setEstado(ProgresoEntity.Estado.PENDIENTE);
            progreso.setProgresoPorcentaje(0.0);
            progreso.setCalificacion(null);
            try { organizador.actualizarProgreso(progreso); } catch (Exception ignored) {}
            fase = Fase.VIDEO;
        } else {
            fase = Fase.VIDEO;
        }
    }

    /** El estudiante terminó de ver el video y quiere pasar al cuestionario */
    public void videoVisto() {
        if (clase == null) return;
        try {
            progreso = simulador.iniciarSesion(clase.getNumeroclase());
            inicializarCuestionario();
            fase = Fase.CUESTIONARIO;
            addInfo("¡Video completado! Responde el cuestionario para finalizar la clase.");
        } catch (Exception e) {
            addError("Error al iniciar la clase: " + e.getMessage());
        }
    }

    private void inicializarCuestionario() {
        preguntas = cuestionarioService.generarCuestionario(clase.getNumeroclase());
        respuestasSeleccionadas = new int[preguntas.size()];
        for (int i = 0; i < respuestasSeleccionadas.length; i++) {
            respuestasSeleccionadas[i] = -1;
        }
        cuestionarioEnviado = false;
    }

    /** Registra la respuesta seleccionada para la pregunta i */
    public void responder(int indicePregunta, int indiceOpcion) {
        if (respuestasSeleccionadas != null && indicePregunta < respuestasSeleccionadas.length) {
            respuestasSeleccionadas[indicePregunta] = indiceOpcion;
            if (preguntas != null && indicePregunta < preguntas.size()) {
                preguntas.get(indicePregunta).setRespuesta(indiceOpcion);
            }
        }
    }

    /** Envía el cuestionario, calcula nota automáticamente y persiste */
    public void enviarCuestionario() {
        if (clase == null || preguntas == null) return;

        // Validar que todas están respondidas
        for (int i = 0; i < preguntas.size(); i++) {
            if (respuestasSeleccionadas[i] < 0) {
                preguntas.get(i).setRespuesta(respuestasSeleccionadas[i]);
                addError("Debes responder todas las preguntas antes de enviar.");
                return;
            }
            preguntas.get(i).setRespuesta(respuestasSeleccionadas[i]);
        }

        // Calcular puntaje
        puntaje = 0;
        for (PreguntaDTO p : preguntas) {
            if (p.isCorrecta()) puntaje++;
        }

        // Calificación: escala 0.0 - 5.0 proporcional al puntaje
        calificacion = (puntaje * 5.0) / preguntas.size();
        // Redondear a 1 decimal
        calificacion = Math.round(calificacion * 10.0) / 10.0;

        // Porcentaje de progreso = % de preguntas correctas (refleja comprensión real)
        porcentajeProgreso = (puntaje * 100.0) / preguntas.size();

        // Persistir y evaluar
        try {
            recomendacion = simulador.completarYEvaluar(calificacion, porcentajeProgreso);
            progreso = organizador.obtenerProgresoPorClase(clase.getId()).orElse(progreso);
            cuestionarioEnviado = true;
            fase = Fase.RESULTADO;
            addInfo("Cuestionario completado. " + recomendacion.getMensaje());
        } catch (Exception e) {
            addError("Error al guardar evaluación: " + e.getMessage());
        }
    }

    /** Avanza a la siguiente clase */
    public String avanzar() {
        try {
            ProgresoEntity siguiente = simulador.avanzarSiguienteClase();
            Integer numSig = siguiente.getClase().getNumeroclase();
            return "detalle.xhtml?clase=" + numSig + "&faces-redirect=true";
        } catch (Exception e) {
            addError(e.getMessage());
            return null;
        }
    }

    /** Reintentar cuestionario (volver a la fase VIDEO) */
    public void reintentar() {
        fase = Fase.VIDEO;
        recomendacion = null;
        cuestionarioEnviado = false;
        // Resetear progreso de la clase para poder reiniciar
        try {
            if (progreso != null) {
                progreso.setEstado(ProgresoEntity.Estado.PENDIENTE);
                progreso.setProgresoPorcentaje(0.0);
                progreso.setCalificacion(null);
                organizador.actualizarProgreso(progreso);
            }
        } catch (Exception ignored) {}
    }

    private void calcularCalificacionDesdeProgreso() {
        if (progreso != null) {
            calificacion       = progreso.getCalificacion()        != null ? progreso.getCalificacion()        : 0;
            porcentajeProgreso = progreso.getProgresoPorcentaje() != null ? progreso.getProgresoPorcentaje()  : 0;
            puntaje = (int) Math.round((calificacion / 5.0) * 5);
            recomendacion = solucionador.evaluar(clase.getNumeroclase());
            cuestionarioEnviado = true;
        }
    }

    // ---- Helpers para la vista ----
    public boolean isFaseVideo()        { return Fase.VIDEO.equals(fase); }
    public boolean isFaseCuestionario() { return Fase.CUESTIONARIO.equals(fase); }
    public boolean isFaseResultado()    { return Fase.RESULTADO.equals(fase); }
    public boolean isAprobada()         { return progreso != null && progreso.estaAprobada(); }

    public int getRespuesta(int i) {
        return (respuestasSeleccionadas != null && i < respuestasSeleccionadas.length)
               ? respuestasSeleccionadas[i] : -1;
    }

    /** Clase CSS para cada opción: seleccionada / correcta / incorrecta (solo post-envío) */
    public String getCssOpcion(int iPregunta, int iOpcion) {
        if (!cuestionarioEnviado) {
            return getRespuesta(iPregunta) == iOpcion ? "opcion-seleccionada" : "opcion";
        }
        PreguntaDTO p = preguntas.get(iPregunta);
        if (iOpcion == p.getCorrecta())    return "opcion opcion-correcta";
        if (iOpcion == p.getRespuesta())   return "opcion opcion-incorrecta";
        return "opcion";
    }

    public String getLetraOpcion(int i) { return String.valueOf((char)('A' + i)); }

    // ---- Getters / Setters ----
    public Integer              getNumeroClase()    { return numeroClase; }
    public void                 setNumeroClase(Integer n) { this.numeroClase = n; }
    public ClaseEntity          getClase()          { return clase; }
    public ProgresoEntity       getProgreso()       { return progreso; }
    public List<PreguntaDTO>    getPreguntas()      { return preguntas; }
    public int[]                getRespuestasSeleccionadas() { return respuestasSeleccionadas; }
    public int                  getPuntaje()        { return puntaje; }
    public int                  getTotalPreguntas() { return preguntas != null ? preguntas.size() : 5; }
    public int getRespuestasContestadas() {
        if (respuestasSeleccionadas == null) return 0;
        int count = 0;
        for (int r : respuestasSeleccionadas) { if (r >= 0) count++; }
        return count;
    }
    public double               getCalificacion()   { return calificacion; }
    public double               getPorcentajeProgreso() { return porcentajeProgreso; }
    public boolean              isCuestionarioEnviado() { return cuestionarioEnviado; }
    public Fase                 getFase()           { return fase; }
    public SolucionadorBean.Recomendacion getRecomendacion() { return recomendacion; }

    private void addInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
}
