package co.edu.curso.rest;

import co.edu.curso.service.OrganizadorBean;
import co.edu.curso.service.SimuladorBean;
import co.edu.curso.service.SolucionadorBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

/**
 * Endpoint REST para progreso del estudiante y recomendaciones.
 * Base: /api/progreso
 */
@Path("/progreso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgresoResource {

    @Inject
    private OrganizadorBean organizador;

    @Inject
    private SimuladorBean simulador;

    @Inject
    private SolucionadorBean solucionador;

    /** GET /api/progreso — lista completa de progreso */
    @GET
    public Response listarProgreso() {
        return Response.ok(organizador.listarProgreso()).build();
    }

    /** GET /api/progreso/resumen — estadísticas generales */
    @GET
    @Path("/resumen")
    public Response resumen() {
        Map<String, Object> resumen = Map.of(
            "totalClases",        organizador.totalClases(),
            "clasesCompletadas",  organizador.clasesCompletadas(),
            "porcentajeGeneral",  organizador.porcentajeGeneral()
        );
        return Response.ok(resumen).build();
    }

    /** POST /api/progreso/{numero}/iniciar — inicia una clase */
    @POST
    @Path("/{numero}/iniciar")
    public Response iniciarClase(@PathParam("numero") Integer numero) {
        try {
            var progreso = simulador.iniciarSesion(numero);
            return Response.ok(progreso).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(Map.of("error", e.getMessage())).build();
        }
    }

    /**
     * POST /api/progreso/{numero}/completar
     * Body: { "calificacion": 4.5, "porcentaje": 92.0 }
     */
    @POST
    @Path("/{numero}/completar")
    public Response completarClase(@PathParam("numero") Integer numero,
                                   Map<String, Double> body) {
        try {
            Double calificacion = body.get("calificacion");
            Double porcentaje   = body.get("porcentaje");

            SolucionadorBean.Recomendacion rec =
                simulador.completarYEvaluar(calificacion, porcentaje);

            Map<String, Object> resp = Map.of(
                "tipo",          rec.getTipo().name(),
                "mensaje",       rec.getMensaje(),
                "claseActual",   rec.getClaseActual().getNumeroclase(),
                "claseSugerida", rec.getClaseSugerida() != null
                                 ? rec.getClaseSugerida().getNumeroclase() : -1
            );
            return Response.ok(resp).build();

        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(Map.of("error", e.getMessage())).build();
        }
    }

    /** GET /api/progreso/{numero}/recomendar — evalúa sin completar */
    @GET
    @Path("/{numero}/recomendar")
    public Response recomendar(@PathParam("numero") Integer numero) {
        try {
            SolucionadorBean.Recomendacion rec = solucionador.evaluar(numero);
            return Response.ok(Map.of(
                "tipo",    rec.getTipo().name(),
                "mensaje", rec.getMensaje()
            )).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(Map.of("error", e.getMessage())).build();
        }
    }
}
