package co.edu.curso.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Entidad que representa una clase del curso de Estructuras de Datos.
 * Mapeada a la tabla curso_clases en PostgreSQL.
 */
@Entity
@Table(name = "curso_clases")
@NamedQueries({
    @NamedQuery(
        name  = "ClaseEntity.findAll",
        query = "SELECT c FROM ClaseEntity c ORDER BY c.numeroclase ASC"
    ),
    @NamedQuery(
        name  = "ClaseEntity.findByNumero",
        query = "SELECT c FROM ClaseEntity c WHERE c.numeroclase = :numero"
    )
})
public class ClaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_clase", nullable = false, unique = true)
    private Integer numeroclase;

    @Column(name = "tema", nullable = false, length = 200)
    private String tema;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "material_url", length = 500)
    private String materialUrl;

    @Column(name = "tipo_material", length = 20)
    private String tipoMaterial;   // PDF | VIDEO | URL

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    // ---- constructores ----

    public ClaseEntity() {}

    public ClaseEntity(Integer numeroclase, String tema,
                       String descripcion, String materialUrl,
                       String tipoMaterial) {
        this.numeroclase  = numeroclase;
        this.tema         = tema;
        this.descripcion  = descripcion;
        this.materialUrl  = materialUrl;
        this.tipoMaterial = tipoMaterial;
        this.activo       = true;
    }

    // ---- getters / setters ----

    public Long getId()                        { return id; }
    public void setId(Long id)                 { this.id = id; }

    public Integer getNumeroclase()            { return numeroclase; }
    public void setNumeroclase(Integer n)      { this.numeroclase = n; }

    public String getTema()                    { return tema; }
    public void setTema(String tema)           { this.tema = tema; }

    public String getDescripcion()             { return descripcion; }
    public void setDescripcion(String d)       { this.descripcion = d; }

    public String getMaterialUrl()             { return materialUrl; }
    public void setMaterialUrl(String url)     { this.materialUrl = url; }

    public String getTipoMaterial()            { return tipoMaterial; }
    public void setTipoMaterial(String tipo)   { this.tipoMaterial = tipo; }

    public Boolean getActivo()                 { return activo; }
    public void setActivo(Boolean activo)      { this.activo = activo; }

    @Override
    public String toString() {
        return "ClaseEntity{id=" + id + ", numero=" + numeroclase
               + ", tema='" + tema + "'}";
    }
}
