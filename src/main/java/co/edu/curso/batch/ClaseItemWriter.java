package co.edu.curso.batch;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.repository.ClaseRepository;
import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

/**
 * Jakarta Batch — ItemWriter.
 * Persiste cada chunk de ClaseEntity en la tabla curso_clases.
 * Omite clases que ya existen en BD (idempotente).
 */
@Named("ClaseItemWriter")
@Dependent
public class ClaseItemWriter extends AbstractItemWriter {

    @Inject
    private ClaseRepository claseRepo;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object obj : items) {
            ClaseEntity clase = (ClaseEntity) obj;
            boolean existe = claseRepo
                    .findByNumero(clase.getNumeroclase())
                    .isPresent();
            if (!existe) {
                claseRepo.guardar(clase);
                System.out.println("[BATCH] Insertada clase "
                                   + clase.getNumeroclase());
            } else {
                System.out.println("[BATCH] Clase "
                                   + clase.getNumeroclase() + " ya existe, omitida.");
            }
        }
    }
}
