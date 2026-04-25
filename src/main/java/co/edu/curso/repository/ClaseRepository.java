package co.edu.curso.repository;

import co.edu.curso.model.ClaseEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para ClaseEntity.
 * Accede a la tabla curso_clases via EntityManager.
 */
@Stateless
public class ClaseRepository {

    @PersistenceContext(unitName = "cursoPU")
    private EntityManager em;

    public void guardar(ClaseEntity clase) {
        em.persist(clase);
    }

    public ClaseEntity actualizar(ClaseEntity clase) {
        return em.merge(clase);
    }

    public Optional<ClaseEntity> findById(Long id) {
        return Optional.ofNullable(em.find(ClaseEntity.class, id));
    }

    public Optional<ClaseEntity> findByNumero(Integer numero) {
        return em.createNamedQuery("ClaseEntity.findByNumero", ClaseEntity.class)
                 .setParameter("numero", numero)
                 .getResultStream()
                 .findFirst();
    }

    public List<ClaseEntity> findAll() {
        return em.createNamedQuery("ClaseEntity.findAll", ClaseEntity.class)
                 .getResultList();
    }

    public long contarTotal() {
        return (long) em.createQuery("SELECT COUNT(c) FROM ClaseEntity c")
                        .getSingleResult();
    }
}
