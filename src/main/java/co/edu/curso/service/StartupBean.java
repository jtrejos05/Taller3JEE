package co.edu.curso.service;

import co.edu.curso.model.ClaseEntity;
import co.edu.curso.repository.ClaseRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class StartupBean {

    @Inject
    private ClaseRepository claseRepo;

    @PostConstruct
    public void init() {
        try {
            inicializarClases();
        } catch (Exception e) {
            System.err.println("[StartupBean] Error: " + e.getMessage());
        }
    }

    private void inicializarClases() {
        // Videos cortos <10 min por tema
        String[][] datos = {
            {"1",  "Introducción a estructuras de datos",       "Fundamentos y conceptos básicos de estructuras de datos",     "Df-sgxGzyTg"},
            {"2",  "Análisis de algoritmos y notación Big-O",   "Complejidad temporal y espacial de algoritmos",               "v4cd1O4zkGw"},
            {"3",  "Tipos de datos abstractos (TDA)",           "Abstracción de datos y contratos de interfaz",                "oz9cEi_qKNs"},
            {"4",  "Arreglos (arrays)",                         "Estructura de arreglos unidimensionales",                     "NptnmWvkbTw"},
            {"5",  "Arreglos multidimensionales",               "Matrices y arreglos de múltiples dimensiones",                "iqT9tvUs62I"},
            {"6",  "Strings y manejo de cadenas",               "Operaciones y algoritmos sobre cadenas de caracteres",        "AEwzFcrGx5E"},
            {"7",  "Listas enlazadas simples",                  "Implementación y operaciones de listas enlazadas simples",    "njTh_OwMljA"},
            {"8",  "Listas enlazadas dobles",                   "Implementación y operaciones de listas doblemente enlazadas", "ZoG2hONatBo"},
            {"9",  "Listas circulares",                         "Listas enlazadas con nodo final apuntando al inicio",         "GXnLGCod31A"},
            {"10", "Pilas (stacks)",                            "Estructura LIFO y sus aplicaciones",                          "I5lq6sCuABE"},
            {"11", "Colas (queues)",                            "Estructura FIFO y sus aplicaciones",                          "XuCbpw6Bj1U"},
            {"12", "Colas avanzadas",                           "Colas de prioridad y variantes",                              "wptevk0bshY"},
            {"13", "Recursión",                                 "Técnicas de recursión y casos base",                          "kepFmOvxznI"},
            {"14", "Backtracking",                              "Exploración exhaustiva con vuelta atrás",                     "DKCbsiDBN6c"},
            {"15", "Divide y vencerás",                         "Paradigma de diseño de algoritmos",                           "2Rr2tW9zvmg"},
            {"16", "Introducción a árboles",                    "Conceptos básicos de árboles y terminología",                 "oSWTXtMglKE"},
            {"17", "Árboles binarios",                          "Estructura y propiedades de los árboles binarios",            "H5JubkIy_p8"},
            {"18", "Recorridos de árboles",                     "InOrder, PreOrder, PostOrder y BFS",                          "BHB0B1jFKQc"},
            {"19", "Árboles binarios de búsqueda (BST)",        "Propiedad BST e inserción/búsqueda",                          "pYT9F8_LFTM"},
            {"20", "Operaciones en BST",                        "Eliminación, balanceo y casos especiales en BST",             "wcIRPqTR3Kc"},
            {"21", "Árboles balanceados (AVL)",                 "Auto-balanceo y rotaciones AVL",                              "jDM6_TnYIqE"},
            {"22", "Árboles Heap",                              "Min-Heap, Max-Heap y Heapify",                                "t0Cq6tVNRBA"},
            {"23", "Colas de prioridad",                        "Implementación con Heap y aplicaciones",                      "HCEr35qpawQ"},
            {"24", "Tablas hash",                               "Estructura hash y funciones de dispersión",                   "shs0KM3wKv0"},
            {"25", "Funciones hash",                            "Diseño y propiedades de buenas funciones hash",               "knV86FlSXJ8"},
            {"26", "Manejo de colisiones",                      "Encadenamiento, sondeo lineal y cuadrático",                  "T9gct6Dx-jo"},
            {"27", "Introducción a grafos",                     "Definición, tipos y terminología de grafos",                  "gXgEDyodOJU"},
            {"28", "Representación de grafos",                  "Matriz de adyacencia y lista de adyacencia",                  "WQ2NTez5s9w"},
            {"29", "Recorridos en grafos (BFS y DFS)",          "Búsqueda en anchura y en profundidad",                        "pcKY4hjDrxk"},
            {"30", "Grafos dirigidos y no dirigidos",           "Dígrafos, pesos y propiedades",                               "eL-KZMpBNDU"},
            {"31", "Algoritmo de Dijkstra",                     "Camino más corto en grafos con pesos positivos",              "EFg3u_E6eHU"},
            {"32", "Algoritmos de Prim y Kruskal",              "Árbol de expansión mínima",                                   "cplfcGZLZdc"},
            {"33", "Detección de ciclos en grafos",             "Algoritmos para detectar ciclos dirigidos y no dirigidos",    "rKQaZuoUR4M"},
            {"34", "Disjoint-set (Union-Find)",                 "Estructura para conjuntos disjuntos",                         "ayW5B2W9hkc"},
            {"35", "Trie (árbol de prefijos)",                  "Búsqueda eficiente de cadenas",                               "zIjfhVPRZCg"},
            {"36", "Segment Tree y Fenwick Tree",               "Estructuras para consultas de rango",                         "ZBHKZF5w4YU"},
        };

        for (String[] d : datos) {
            int    numero    = Integer.parseInt(d[0]);
            String youtubeId = d[3];
            String embedUrl  = "https://www.youtube.com/embed/" + youtubeId + "?rel=0&modestbranding=1";
            ClaseEntity c = claseRepo.findByNumero(numero).orElse(null);
            if (c == null) {
                c = new ClaseEntity(numero, d[1], d[2], embedUrl, "VIDEO");
                c.setYoutubeId(youtubeId);
                claseRepo.guardar(c);
            } else {
                c.setYoutubeId(youtubeId);
                c.setMaterialUrl(embedUrl);
                c.setTipoMaterial("VIDEO");
                c.setTema(d[1]);
                c.setDescripcion(d[2]);
                claseRepo.actualizar(c);
            }
        }
        System.out.println("[StartupBean] 36 clases actualizadas con videos cortos (<10 min).");
    }
}
