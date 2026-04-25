package co.edu.curso.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Punto de entrada JAX-RS.
 * Todos los endpoints quedan bajo /api/
 */
@ApplicationPath("/api")
public class RestApplication extends Application {}
