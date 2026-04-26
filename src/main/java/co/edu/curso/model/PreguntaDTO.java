package co.edu.curso.model;

import java.io.Serializable;
import java.util.List;

/**
 * DTO (no entidad JPA) que representa una pregunta del cuestionario.
 * Se genera dinámicamente en CuestionarioService según el tema de la clase.
 */
public class PreguntaDTO implements Serializable {

    private final String       enunciado;
    private final List<String> opciones;    // 4 opciones A-D
    private final int          correcta;   // índice 0-3 de la opción correcta
    private int                respuesta = -1; // índice seleccionado por el estudiante

    public PreguntaDTO(String enunciado, List<String> opciones, int correcta) {
        this.enunciado = enunciado;
        this.opciones  = opciones;
        this.correcta  = correcta;
    }

    public boolean isRespondida()  { return respuesta >= 0; }
    public boolean isCorrecta()    { return respuesta == correcta; }

    public String       getEnunciado() { return enunciado; }
    public List<String> getOpciones()  { return opciones; }
    public int          getCorrecta()  { return correcta; }
    public int          getRespuesta() { return respuesta; }
    public void         setRespuesta(int r) { this.respuesta = r; }

    /** Letra de la respuesta correcta (A, B, C, D) */
    public String getLetraCorrecta() {
        return String.valueOf((char)('A' + correcta));
    }
}
