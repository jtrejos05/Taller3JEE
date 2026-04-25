package co.edu.curso.rest;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.service.OrganizadorBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Endpoint REST para consulta de clases del curso.
 * Base: /api/clases
 */
@Path("/clases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClaseResource {

    @Inject
    private OrganizadorBean organizador;

    /** GET /api/clases — lista las 36 clases */
    @GET
    public Response listarClases() {
        List<ClaseEntity> clases = organizador.listarTodasLasClases();
        return Response.ok(clases).build();
    }

    /** GET /api/clases/{numero} — detalle de una clase */
    @GET
    @Path("/{numero}")
    public Response obtenerClase(@PathParam("numero") Integer numero) {
        return organizador.buscarClasePorNumero(numero)
                .map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"Clase no encontrada\"}")
                        .build());
    }
}
