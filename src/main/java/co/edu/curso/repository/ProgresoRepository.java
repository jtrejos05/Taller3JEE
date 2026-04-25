package co.edu.curso.repository;

import co.edu.curso.model.ProgresoEntity;
import co.edu.curso.model.ProgresoEntity.Estado;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para ProgresoEntity.
 * Accede a la tabla estudiante_progreso via EntityManager.
 */
@Stateless
public class ProgresoRepository {

    @PersistenceContext(unitName = "cursoPU")
    private EntityManager em;

    public void guardar(ProgresoEntity progreso) {
        em.persist(progreso);
    }

    public ProgresoEntity actualizar(ProgresoEntity progreso) {
        return em.merge(progreso);
    }

    public Optional<ProgresoEntity> findById(Long id) {
        return Optional.ofNullable(em.find(ProgresoEntity.class, id));
    }

    public Optional<ProgresoEntity> findByClaseId(Long claseId) {
        return em.createNamedQuery("ProgresoEntity.findByClase", ProgresoEntity.class)
                 .setParameter("claseId", claseId)
                 .getResultStream()
                 .findFirst();
    }

    public List<ProgresoEntity> findAll() {
        return em.createNamedQuery("ProgresoEntity.findAll", ProgresoEntity.class)
                 .getResultList();
    }

    public List<ProgresoEntity> findByEstado(Estado estado) {
        return em.createNamedQuery("ProgresoEntity.findByEstado", ProgresoEntity.class)
                 .setParameter("estado", estado.name())
                 .getResultList();
    }

    public long contarCompletadas() {
        return (long) em.createNamedQuery("ProgresoEntity.countCompletadas")
                        .getSingleResult();
    }

    /**
     * Retorna la siguiente clase pendiente (la de menor número no completada).
     */
    public Optional<ProgresoEntity> findSiguientePendiente() {
        return em.createQuery(
                "SELECT p FROM ProgresoEntity p " +
                "WHERE p.estado <> 'COMPLETADO' " +
                "ORDER BY p.clase.numeroclase ASC",
                ProgresoEntity.class)
                 .setMaxResults(1)
                 .getResultStream()
                 .findFirst();
    }
}
