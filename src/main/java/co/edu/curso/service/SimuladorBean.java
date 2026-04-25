package co.edu.curso.service;

import co.edu.curso.model.ProgresoEntity;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 * EJB Stateful — Simulador de sesión del estudiante.
 *
 * Mantiene el estado de la clase actual durante la sesión.
 * Delega a OrganizadorBean y SolucionadorBean para persistencia
 * y lógica de reglas.
 */
@Stateful
public class SimuladorBean implements Serializable {

    @Inject
    private OrganizadorBean organizador;

    @Inject
    private SolucionadorBean solucionador;

    private Integer claseActualNumero;
    private SolucionadorBean.Recomendacion ultimaRecomendacion;

    // ---- Ciclo de vida de la sesión ----

    /**
     * Inicia la sesión en la clase indicada.
     */
    public ProgresoEntity iniciarSesion(Integer numeroClase) {
        this.claseActualNumero    = numeroClase;
        this.ultimaRecomendacion  = null;
        return organizador.iniciarClase(numeroClase);
    }

    /**
     * Registra calificación y porcentaje de la clase activa,
     * luego evalúa automáticamente la recomendación.
     */
    public SolucionadorBean.Recomendacion completarYEvaluar(
            Double calificacion, Double porcentaje) {

        if (claseActualNumero == null) {
            throw new IllegalStateException(
                "No hay ninguna clase activa en esta sesión.");
        }

        organizador.completarClase(claseActualNumero, calificacion, porcentaje);
        this.ultimaRecomendacion = solucionador.evaluar(claseActualNumero);
        return this.ultimaRecomendacion;
    }

    /**
     * Si la última recomendación fue AVANZAR, mueve la sesión a la clase N+1.
     */
    public ProgresoEntity avanzarSiguienteClase() {
        if (ultimaRecomendacion == null
                || !ultimaRecomendacion.esAvanzar()
                || ultimaRecomendacion.getClaseSugerida() == null) {
            throw new IllegalStateException(
                "No se puede avanzar: la clase actual aún no está aprobada.");
        }
        Integer siguiente = ultimaRecomendacion.getClaseSugerida().getNumeroclase();
        return iniciarSesion(siguiente);
    }

    // ---- Consultas de estado ----

    public Integer getClaseActualNumero()               { return claseActualNumero; }
    public SolucionadorBean.Recomendacion getUltimaRecomendacion()
                                                        { return ultimaRecomendacion; }

    public double getPorcentajeGeneralCurso() {
        return organizador.porcentajeGeneral();
    }

    // ---- Fin de sesión ----

    @Remove
    public void cerrarSesion() {
        this.claseActualNumero   = null;
        this.ultimaRecomendacion = null;
    }
}
