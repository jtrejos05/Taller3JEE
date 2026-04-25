package co.edu.curso.batch;

import co.edu.curso.model.ClaseEntity;
import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

/**
 * Jakarta Batch — ItemProcessor.
 * Valida y normaliza cada ClaseEntity antes de persistir.
 * Retorna null para descartar ítems inválidos.
 */
@Named("ClaseItemProcessor")
@Dependent
public class ClaseItemProcessor implements ItemProcessor {

    @Override
    public ClaseEntity processItem(Object item) throws Exception {
        ClaseEntity clase = (ClaseEntity) item;

        if (clase.getNumeroclase() == null
                || clase.getNumeroclase() < 1
                || clase.getNumeroclase() > 36) {
            System.err.println("[BATCH] Clase con número inválido descartada: "
                               + clase.getNumeroclase());
            return null;  // descartada
        }

        if (clase.getTema() == null || clase.getTema().isBlank()) {
            System.err.println("[BATCH] Clase sin tema descartada: "
                               + clase.getNumeroclase());
            return null;
        }

        // Normalizar tipo material
        if (clase.getTipoMaterial() == null) {
            clase.setTipoMaterial("URL");
        } else {
            clase.setTipoMaterial(clase.getTipoMaterial().toUpperCase());
        }

        clase.setActivo(true);
        System.out.println("[BATCH] Procesada clase "
                           + clase.getNumeroclase() + ": " + clase.getTema());
        return clase;
    }
}
