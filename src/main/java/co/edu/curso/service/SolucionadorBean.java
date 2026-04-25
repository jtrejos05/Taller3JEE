package co.edu.curso.service;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.model.ProgresoEntity;
import co.edu.curso.repository.ClaseRepository;
import co.edu.curso.repository.ProgresoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Optional;

/**
 * EJB Stateless — Solucionador (motor de recomendaciones).
 *
 * Aplica las 3 reglas de negocio:
 *   1. Estado == COMPLETADO
 *   2. Progreso >= umbral (80 %)
 *   3. Calificación >= 3.0
 *
 * Si las 3 se cumplen → recomienda avanzar a la clase N+1.
 * Si alguna falla     → recomienda reforzar la clase actual.
 */
@Stateless
public class SolucionadorBean {

    private static final double UMBRAL_PROGRESO     = 80.0;
    private static final double UMBRAL_CALIFICACION = 3.0;

    @Inject
    private ClaseRepository claseRepo;

    @Inject
    private ProgresoRepository progresoRepo;

    // ---- DTO interno de recomendación ----

    public static class Recomendacion {
        public enum Tipo { AVANZAR, REFORZAR }

        private final Tipo         tipo;
        private final ClaseEntity  claseActual;
        private final ClaseEntity  claseSugerida;
        private final String       mensaje;

        public Recomendacion(Tipo tipo, ClaseEntity actual,
                             ClaseEntity sugerida, String mensaje) {
            this.tipo          = tipo;
            this.claseActual   = actual;
            this.claseSugerida = sugerida;
            this.mensaje       = mensaje;
        }

        public Tipo        getTipo()          { return tipo; }
        public ClaseEntity getClaseActual()   { return claseActual; }
        public ClaseEntity getClaseSugerida() { return claseSugerida; }
        public String      getMensaje()       { return mensaje; }
        public boolean     esAvanzar()        { return Tipo.AVANZAR.equals(tipo); }
    }

    // ---- API principal ----

    /**
     * Evalúa el progreso de la clase indicada y genera la recomendación.
     *
     * @param numeroClase número de la clase actual (1–36)
     * @return Recomendacion con tipo AVANZAR o REFORZAR
     */
    public Recomendacion evaluar(Integer numeroClase) {

        ClaseEntity actual = claseRepo.findByNumero(numeroClase)
            .orElseThrow(() -> new IllegalArgumentException(
                "Clase no encontrada: " + numeroClase));

        ProgresoEntity progreso = progresoRepo.findByClaseId(actual.getId())
            .orElseThrow(() -> new IllegalStateException(
                "No hay progreso registrado para la clase: " + numeroClase));

        // ---- Evaluación de las 3 reglas ----
        boolean regla1 = ProgresoEntity.Estado.COMPLETADO.equals(progreso.getEstado());
        boolean regla2 = progreso.getProgresoPorcentaje() != null
                         && progreso.getProgresoPorcentaje() >= UMBRAL_PROGRESO;
        boolean regla3 = progreso.getCalificacion() != null
                         && progreso.getCalificacion() >= UMBRAL_CALIFICACION;

        if (regla1 && regla2 && regla3) {
            return construirAvanzar(actual, numeroClase);
        } else {
            return construirReforzar(actual, progreso);
        }
    }

    // ---- métodos auxiliares ----

    private Recomendacion construirAvanzar(ClaseEntity actual, Integer numeroActual) {
        Optional<ClaseEntity> siguiente = claseRepo.findByNumero(numeroActual + 1);

        if (siguiente.isEmpty()) {
            return new Recomendacion(
                Recomendacion.Tipo.AVANZAR, actual, null,
                "¡Felicitaciones! Completaste todas las clases del curso.");
        }

        return new Recomendacion(
            Recomendacion.Tipo.AVANZAR,
            actual,
            siguiente.get(),
            "¡Excelente! Puedes avanzar a la clase "
            + siguiente.get().getNumeroclase()
            + ": " + siguiente.get().getTema());
    }

    private Recomendacion construirReforzar(ClaseEntity actual,
                                            ProgresoEntity progreso) {
        StringBuilder msg = new StringBuilder("Necesitas reforzar esta clase. ");

        if (!ProgresoEntity.Estado.COMPLETADO.equals(progreso.getEstado())) {
            msg.append("Estado: ").append(progreso.getEstado()).append(". ");
        }
        if (progreso.getProgresoPorcentaje() == null
                || progreso.getProgresoPorcentaje() < UMBRAL_PROGRESO) {
            msg.append("Progreso actual: ")
               .append(progreso.getProgresoPorcentaje() != null
                       ? progreso.getProgresoPorcentaje() : 0)
               .append("% (mínimo ").append(UMBRAL_PROGRESO).append("%). ");
        }
        if (progreso.getCalificacion() == null
                || progreso.getCalificacion() < UMBRAL_CALIFICACION) {
            msg.append("Calificación: ")
               .append(progreso.getCalificacion() != null
                       ? progreso.getCalificacion() : "sin registrar")
               .append(" (mínimo ").append(UMBRAL_CALIFICACION).append(").");
        }

        return new Recomendacion(
            Recomendacion.Tipo.REFORZAR, actual, actual, msg.toString());
    }
}
