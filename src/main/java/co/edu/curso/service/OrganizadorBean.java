package co.edu.curso.service;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.model.ProgresoEntity;
import co.edu.curso.repository.ClaseRepository;
import co.edu.curso.repository.ProgresoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * EJB Stateless — Organizador.
 * Coordina el acceso a clases y progreso del estudiante.
 * Punto de entrada desde JAX-RS y JSF.
 */
@Stateless
public class OrganizadorBean {

    @Inject
    private ClaseRepository claseRepo;

    @Inject
    private ProgresoRepository progresoRepo;

    // ---- Clases ----

    public List<ClaseEntity> listarTodasLasClases() {
        return claseRepo.findAll();
    }

    public Optional<ClaseEntity> buscarClasePorNumero(Integer numero) {
        return claseRepo.findByNumero(numero);
    }

    // ---- Progreso ----

    public List<ProgresoEntity> listarProgreso() {
        return progresoRepo.findAll();
    }

    public Optional<ProgresoEntity> obtenerProgresoPorClase(Long claseId) {
        return progresoRepo.findByClaseId(claseId);
    }

    /**
     * Inicializa registros de progreso para todas las clases
     * si aún no existen (llamado al arrancar la app).
     */
    public void inicializarProgresoSiNecesario() {
        long totalClases   = claseRepo.contarTotal();
        long totalProgreso = progresoRepo.findAll().size();

        if (totalProgreso < totalClases) {
            List<ClaseEntity> clases = claseRepo.findAll();
            for (ClaseEntity clase : clases) {
                boolean existe = progresoRepo
                        .findByClaseId(clase.getId())
                        .isPresent();
                if (!existe) {
                    progresoRepo.guardar(new ProgresoEntity(clase));
                }
            }
        }
    }

    /**
     * Registra el inicio de una clase por su número.
     */
    public ProgresoEntity iniciarClase(Integer numeroClase) {
        ClaseEntity clase = claseRepo.findByNumero(numeroClase)
            .orElseThrow(() -> new IllegalArgumentException(
                "Clase no encontrada: " + numeroClase));

        ProgresoEntity progreso = progresoRepo.findByClaseId(clase.getId())
            .orElseGet(() -> {
                ProgresoEntity p = new ProgresoEntity(clase);
                progresoRepo.guardar(p);
                return p;
            });

        progreso.iniciar();
        return progresoRepo.actualizar(progreso);
    }

    /**
     * Registra la finalización de una clase con calificación y porcentaje.
     */
    public ProgresoEntity completarClase(Integer numeroClase,
                                         Double calificacion,
                                         Double porcentaje) {
        ClaseEntity clase = claseRepo.findByNumero(numeroClase)
            .orElseThrow(() -> new IllegalArgumentException(
                "Clase no encontrada: " + numeroClase));

        ProgresoEntity progreso = progresoRepo.findByClaseId(clase.getId())
            .orElseThrow(() -> new IllegalStateException(
                "Progreso no iniciado para clase: " + numeroClase));

        progreso.completar(calificacion, porcentaje);
        return progresoRepo.actualizar(progreso);
    }

    // ---- Estadísticas generales ----

    public long totalClases()      { return claseRepo.contarTotal(); }
    public long clasesCompletadas(){ return progresoRepo.contarCompletadas(); }

    public ProgresoEntity actualizarProgreso(ProgresoEntity progreso) {
        return progresoRepo.actualizar(progreso);
    }

    public double porcentajeGeneral() {
        long total     = totalClases();
        long completas = clasesCompletadas();
        return total == 0 ? 0.0 : (completas * 100.0 / total);
    }
}
