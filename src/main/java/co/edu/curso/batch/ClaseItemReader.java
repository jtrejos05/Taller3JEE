package co.edu.curso.batch;

import co.edu.curso.model.ClaseEntity;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Jakarta Batch — ItemReader.
 * Lee las 36 clases del curso desde /META-INF/clases-curso.json
 */
@Named("ClaseItemReader")
@Dependent
public class ClaseItemReader extends AbstractItemReader {

    private List<ClaseEntity> clases;
    private int indice = 0;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        clases = cargarDesdeJson();
        if (checkpoint instanceof Integer idx) {
            indice = idx;   // retomar desde checkpoint
        }
    }

    @Override
    public ClaseEntity readItem() {
        if (indice >= clases.size()) return null;
        return clases.get(indice++);
    }

    @Override
    public Serializable checkpointInfo() {
        return indice;
    }

    // ---- carga JSON ----

    private List<ClaseEntity> cargarDesdeJson() throws Exception {
        List<ClaseEntity> lista = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream(
                "/META-INF/clases-curso.json");

        if (is == null) {
            throw new IllegalStateException(
                "No se encontró /META-INF/clases-curso.json");
        }

        JsonArray arr = Json.createReader(is).readArray();
        for (int i = 0; i < arr.size(); i++) {
            JsonObject obj = arr.getJsonObject(i);
            lista.add(new ClaseEntity(
                obj.getInt("numero"),
                obj.getString("tema"),
                obj.getString("descripcion"),
                obj.getString("material_url"),
                obj.getString("tipo_material")
            ));
        }
        return lista;
    }
}
