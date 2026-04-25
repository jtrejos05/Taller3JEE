package co.edu.curso.model;

import co.edu.curso.model.ClaseEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa el progreso de un estudiante en cada clase.
 * Mapeada a la tabla estudiante_progreso en PostgreSQL.
 */
@Entity
@Table(name = "estudiante_progreso")
@NamedQueries({
    @NamedQuery(
        name  = "ProgresoEntity.findAll",
        query = "SELECT p FROM ProgresoEntity p ORDER BY p.clase.numeroclase ASC"
    ),
    @NamedQuery(
        name  = "ProgresoEntity.findByClase",
        query = "SELECT p FROM ProgresoEntity p WHERE p.clase.id = :claseId"
    ),
    @NamedQuery(
        name  = "ProgresoEntity.findByEstado",
        query = "SELECT p FROM ProgresoEntity p WHERE p.estado = :estado"
    ),
    @NamedQuery(
        name  = "ProgresoEntity.countCompletadas",
        query = "SELECT COUNT(p) FROM ProgresoEntity p WHERE p.estado = 'COMPLETADO'"
    )
})
public class ProgresoEntity implements Serializable {

    public enum Estado { PENDIENTE, EN_PROGRESO, COMPLETADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clase_id", nullable = false)
    private ClaseEntity clase;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "progreso_porcentaje", nullable = false)
    private Double progresoPorcentaje = 0.0;   // 0.0 – 100.0

    @Column(name = "calificacion")
    private Double calificacion;               // 0.0 – 5.0

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    // ---- constructores ----

    public ProgresoEntity() {}

    public ProgresoEntity(ClaseEntity clase) {
        this.clase  = clase;
        this.estado = Estado.PENDIENTE;
        this.progresoPorcentaje = 0.0;
    }

    // ---- lógica de negocio básica ----

    public boolean estaAprobada() {
        return Estado.COMPLETADO.equals(this.estado)
            && this.progresoPorcentaje != null && this.progresoPorcentaje >= 80.0
            && this.calificacion != null && this.calificacion >= 3.0;
    }

    public void iniciar() {
        if (this.estado == Estado.PENDIENTE) {
            this.estado      = Estado.EN_PROGRESO;
            this.fechaInicio = LocalDateTime.now();
        }
    }

    public void completar(Double calificacion, Double porcentaje) {
        this.calificacion       = calificacion;
        this.progresoPorcentaje = porcentaje;
        this.estado             = Estado.COMPLETADO;
        this.fechaFin           = LocalDateTime.now();
    }

    // ---- getters / setters ----

    public Long getId()                              { return id; }
    public void setId(Long id)                       { this.id = id; }

    public ClaseEntity getClase()                    { return clase; }
    public void setClase(ClaseEntity clase)          { this.clase = clase; }

    public Estado getEstado()                        { return estado; }
    public void setEstado(Estado estado)             { this.estado = estado; }

    public Double getProgresoPorcentaje()            { return progresoPorcentaje; }
    public void setProgresoPorcentaje(Double p)      { this.progresoPorcentaje = p; }

    public Double getCalificacion()                  { return calificacion; }
    public void setCalificacion(Double c)            { this.calificacion = c; }

    public LocalDateTime getFechaInicio()            { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fi)     { this.fechaInicio = fi; }

    public LocalDateTime getFechaFin()               { return fechaFin; }
    public void setFechaFin(LocalDateTime ff)        { this.fechaFin = ff; }
}
