package co.edu.curso.service;

import co.edu.curso.model.PreguntaDTO;
import jakarta.ejb.Stateless;
import java.util.*;

/**
 * Servicio que genera cuestionarios de 5 preguntas según el número de clase.
 * Las preguntas cubren el tema correspondiente al diagrama del curso.
 */
@Stateless
public class CuestionarioService {

    /**
     * Genera 5 preguntas para la clase indicada.
     * @param numeroClase 1-36
     * @return lista de 5 PreguntaDTO
     */
    public List<PreguntaDTO> generarCuestionario(int numeroClase) {
        return switch (numeroClase) {
            case 1  -> clase01();
            case 2  -> clase02();
            case 3  -> clase03();
            case 4  -> clase04();
            case 5  -> clase05();
            case 6  -> clase06();
            case 7  -> clase07();
            case 8  -> clase08();
            case 9  -> clase09();
            case 10 -> clase10();
            case 11 -> clase11();
            case 12 -> clase12();
            case 13 -> clase13();
            case 14 -> clase14();
            case 15 -> clase15();
            case 16 -> clase16();
            case 17 -> clase17();
            case 18 -> clase18();
            case 19 -> clase19();
            case 20 -> clase20();
            case 21 -> clase21();
            case 22 -> clase22();
            case 23 -> clase23();
            case 24 -> clase24();
            case 25 -> clase25();
            case 26 -> clase26();
            case 27 -> clase27();
            case 28 -> clase28();
            case 29 -> clase29();
            case 30 -> clase30();
            case 31 -> clase31();
            case 32 -> clase32();
            case 33 -> clase33();
            case 34 -> clase34();
            case 35 -> clase35();
            case 36 -> clase36();
            default -> cuestionarioGenerico(numeroClase);
        };
    }

    private static PreguntaDTO p(String e, String a, String b, String c, String d, int ok) {
        return new PreguntaDTO(e, List.of(a, b, c, d), ok);
    }

    // ---- Clase 1: Introducción a Estructuras de Datos ----
    private List<PreguntaDTO> clase01() { return List.of(
        p("¿Qué es una estructura de datos?",
          "Un algoritmo para ordenar elementos",
          "Una forma de organizar y almacenar datos en memoria",
          "Un lenguaje de programación",
          "Un tipo de base de datos relacional", 1),
        p("¿Cuál de las siguientes NO es una estructura de datos lineal?",
          "Array", "Lista enlazada", "Árbol binario", "Cola", 2),
        p("¿Qué ventaja ofrece elegir la estructura de datos correcta?",
          "Reduce el tamaño del código fuente",
          "Elimina todos los bugs del programa",
          "Mejora la eficiencia en tiempo y espacio",
          "Hace el código más legible siempre", 2),
        p("¿Cuál es la diferencia entre estructura de datos estática y dinámica?",
          "Las estáticas son más rápidas siempre",
          "Las estáticas tienen tamaño fijo; las dinámicas crecen en tiempo de ejecución",
          "Las dinámicas solo se usan en lenguajes orientados a objetos",
          "No hay diferencia real entre ellas", 1),
        p("¿Qué estructura usarías para modelar una cola de impresión?",
          "Árbol AVL", "Tabla Hash", "Pila (Stack)", "Cola (Queue)", 3)
    );}

    // ---- Clase 2: Big-O ----
    private List<PreguntaDTO> clase02() { return List.of(
        p("¿Qué mide la notación Big-O?",
          "El tamaño del código en bytes",
          "La velocidad del procesador",
          "El comportamiento del algoritmo en el peor caso respecto al tamaño de entrada",
          "El consumo de energía del algoritmo", 2),
        p("¿Cuál es la complejidad de buscar un elemento en un arreglo no ordenado?",
          "O(1)", "O(log n)", "O(n)", "O(n²)", 2),
        p("Si un algoritmo tiene complejidad O(n²), ¿qué sucede al duplicar la entrada?",
          "El tiempo se duplica", "El tiempo se cuadruplica", "El tiempo permanece igual", "El tiempo se reduce", 1),
        p("¿Qué complejidad tiene acceder a un elemento por índice en un array?",
          "O(n)", "O(log n)", "O(n log n)", "O(1)", 3),
        p("Ordena de menor a mayor eficiencia: O(n²), O(1), O(n log n), O(n)",
          "O(1) < O(n) < O(n log n) < O(n²)",
          "O(n²) < O(n log n) < O(n) < O(1)",
          "O(1) < O(n log n) < O(n) < O(n²)",
          "O(n) < O(1) < O(n²) < O(n log n)", 0)
    );}

    // ---- Clase 3: TDA ----
    private List<PreguntaDTO> clase03() { return List.of(
        p("¿Qué es un Tipo de Dato Abstracto (TDA)?",
          "Un tipo primitivo del lenguaje",
          "Una definición matemática de un tipo de dato con sus operaciones, sin importar la implementación",
          "Una clase abstracta en Java",
          "Un tipo de dato definido en la base de datos", 1),
        p("¿Cuál es la diferencia entre TDA e implementación?",
          "No hay diferencia, son lo mismo",
          "El TDA define el QUÉ; la implementación define el CÓMO",
          "La implementación define el QUÉ; el TDA define el CÓMO",
          "El TDA solo aplica a lenguajes funcionales", 1),
        p("¿Qué operaciones define el TDA Lista?",
          "Solo insertar y eliminar",
          "Insertar, eliminar, buscar y acceder a elementos",
          "Solo ordenar elementos",
          "Solo acceder por índice", 1),
        p("¿Por qué es útil el concepto de TDA en el diseño de software?",
          "Permite ignorar el rendimiento del código",
          "Separa la interfaz de la implementación, facilitando el mantenimiento",
          "Evita el uso de algoritmos",
          "Obliga a usar un único lenguaje de programación", 1),
        p("¿Qué principio SOLID está relacionado con el TDA?",
          "Single Responsibility", "Open/Closed",
          "Liskov Substitution", "Interface Segregation / Dependency Inversion", 3)
    );}

    // ---- Clase 4: Arrays ----
    private List<PreguntaDTO> clase04() { return List.of(
        p("¿Cómo se almacenan los elementos de un array en memoria?",
          "En posiciones aleatorias del heap",
          "En posiciones contiguas de memoria",
          "En un árbol binario",
          "En una lista enlazada internamente", 1),
        p("¿Cuál es la complejidad de insertar al final de un array dinámico (amortizado)?",
          "O(n)", "O(log n)", "O(1) amortizado", "O(n²)", 2),
        p("¿Qué desventaja tienen los arrays de tamaño fijo?",
          "No permiten acceso aleatorio",
          "Solo pueden almacenar enteros",
          "No pueden crecer dinámicamente si se llena la capacidad inicial",
          "Son más lentos que las listas enlazadas en todo", 2),
        p("¿Cuál es la complejidad de eliminar el primer elemento de un array?",
          "O(1)", "O(log n)", "O(n) por el desplazamiento de elementos", "O(n²)", 2),
        p("¿Qué es un array jagged (irregular)?",
          "Un array con elementos duplicados",
          "Un array multidimensional donde las filas tienen diferente longitud",
          "Un array que solo acepta null",
          "Un array circular", 1)
    );}

    // ---- Clase 5: Arrays Multidimensionales ----
    private List<PreguntaDTO> clase05() { return List.of(
        p("¿Cómo se accede al elemento de fila 2, columna 3 en una matriz Java?",
          "matriz[3][2]", "matriz[2][3]", "matriz(2,3)", "matriz.get(2,3)", 1),
        p("¿Cuántos elementos tiene una matriz de 4×5?",
          "9", "20", "4", "5", 1),
        p("¿Qué es el recorrido row-major en una matriz?",
          "Recorrer columna por columna",
          "Recorrer diagonal a diagonal",
          "Recorrer fila por fila",
          "Recorrer en espiral", 2),
        p("¿Qué representa una matriz de adyacencia en grafos?",
          "El peso de los nodos",
          "Las conexiones entre nodos mediante 0s y 1s",
          "El orden topológico",
          "Los ciclos del grafo", 1),
        p("¿Cuánta memoria ocupa una matriz de NxM de enteros de 4 bytes?",
          "N+M bytes", "N*M*4 bytes", "N*M bytes", "4 bytes siempre", 1)
    );}

    // ---- Clase 6: Strings ----
    private List<PreguntaDTO> clase06() { return List.of(
        p("¿Qué complejidad tiene encontrar si un patrón P existe en texto T con búsqueda ingenua?",
          "O(|T|)", "O(|P|)", "O(|T|*|P|)", "O(1)", 2),
        p("¿Qué hace el algoritmo KMP?",
          "Ordena caracteres alfabéticamente",
          "Busca patrones en O(|T|+|P|) usando una tabla de fallos",
          "Comprime cadenas",
          "Invierte una cadena en O(1)", 1),
        p("En Java, ¿por qué String es inmutable?",
          "Por limitación del hardware",
          "Para ahorrar memoria y seguridad (string pool / caching de hashCode)",
          "Para ser compatible con C",
          "Por convención del compilador", 1),
        p("¿Qué es un palíndromo?",
          "Una cadena con todos los caracteres únicos",
          "Una cadena que se lee igual de izquierda a derecha que de derecha a izquierda",
          "Una cadena ordenada lexicográficamente",
          "Una cadena con longitud par", 1),
        p("¿Cuál es la complejidad de concatenar n strings de longitud k usando '+' en bucle en Java?",
          "O(n)", "O(k)", "O(n*k) porque cada concatenación crea nuevo objeto",
          "O(1)", 2)
    );}

    // ---- Clase 7: Listas Enlazadas Simples ----
    private List<PreguntaDTO> clase07() { return List.of(
        p("¿Qué componentes tiene un nodo de una lista enlazada simple?",
          "Solo el dato", "Dato y un puntero al siguiente nodo",
          "Dato y dos punteros", "Solo el puntero siguiente", 1),
        p("¿Cuál es la complejidad de insertar al inicio de una lista enlazada simple?",
          "O(n)", "O(log n)", "O(1)", "O(n²)", 2),
        p("¿Cuál es la desventaja de la lista enlazada frente al array?",
          "No permite inserción",
          "No permite acceso aleatorio O(1); buscar el elemento i-ésimo es O(n)",
          "No puede almacenar objetos",
          "Ocupa menos memoria", 1),
        p("¿Cómo se detecta si una lista enlazada tiene un ciclo?",
          "Contando nodos", "Con el algoritmo Floyd (dos punteros lento y rápido)",
          "Ordenando la lista", "Usando una tabla hash siempre", 1),
        p("¿Qué apunta el campo 'siguiente' del último nodo de una lista simple?",
          "Al primer nodo", "A sí mismo", "A null", "Al nodo anterior", 2)
    );}

    // ---- Clase 8: Listas Dobles ----
    private List<PreguntaDTO> clase08() { return List.of(
        p("¿Qué ventaja tiene la lista doblemente enlazada sobre la simple?",
          "Ocupa menos memoria",
          "Permite recorrer en ambas direcciones y eliminar nodo conociendo solo su referencia en O(1)",
          "Acceso aleatorio en O(1)",
          "No necesita punteros", 1),
        p("¿Cuántos punteros tiene un nodo de lista doblemente enlazada?",
          "1", "2 (anterior y siguiente)", "3", "0", 1),
        p("¿Qué hace el nodo centinela (dummy node) en una lista doble?",
          "Almacena el tamaño de la lista",
          "Simplifica los casos borde al insertar/eliminar en extremos",
          "Apunta al nodo del medio",
          "Duplica cada elemento", 1),
        p("¿Cuánta memoria extra usa una lista doble vs una simple con n nodos?",
          "O(1) extra total", "O(n) extra por los n punteros anteriores adicionales",
          "O(n²) extra", "La misma memoria", 1),
        p("¿Cuál estructura Java interna usa lista doblemente enlazada?",
          "ArrayList", "HashMap", "LinkedList", "TreeMap", 2)
    );}

    // ---- Clase 9: Listas Circulares ----
    private List<PreguntaDTO> clase09() { return List.of(
        p("¿Qué caracteriza a una lista circular?",
          "Todos los nodos apuntan a null",
          "El último nodo apunta al primer nodo",
          "Solo puede recorrerse una vez",
          "Los nodos no tienen dato", 1),
        p("¿Cuándo es útil una lista circular?",
          "Nunca, es solo teórica",
          "Cuando necesitas acceso aleatorio",
          "En round-robin scheduling y buffers circulares",
          "Solo en recursión", 2),
        p("¿Cómo se detecta el fin de recorrido en una lista circular?",
          "Cuando next == null",
          "Cuando el nodo actual == cabeza (o nodo inicial)",
          "Cuando el dato es 0",
          "Con un contador de tamaño siempre", 1),
        p("¿Qué diferencia hay entre lista circular simple y doble circular?",
          "No hay diferencia",
          "La doble circular tiene punteros anterior y siguiente con ambos extremos enlazados",
          "Solo la doble circular puede ser vacía",
          "La simple circular tiene más memoria", 1),
        p("¿Qué problema puede ocurrir al recorrer una lista circular sin condición de parada?",
          "Acceso fuera de límites", "Bucle infinito", "NullPointerException", "Stack overflow", 1)
    );}

    // ---- Clase 10: Pilas ----
    private List<PreguntaDTO> clase10() { return List.of(
        p("¿Qué política de acceso sigue una pila (Stack)?",
          "FIFO", "LIFO", "FILO es lo mismo que FIFO", "Aleatorio", 1),
        p("¿Cuál de las siguientes operaciones NO pertenece al TDA Pila?",
          "push", "pop", "peek/top", "enqueue", 3),
        p("¿Para qué se usa una pila en la evaluación de expresiones aritméticas?",
          "Para ordenar operandos",
          "Para gestionar la precedencia de operadores y paréntesis",
          "Para almacenar resultados intermedios en un array",
          "Para calcular Big-O de la expresión", 1),
        p("¿Qué ocurre al hacer pop en una pila vacía?",
          "Devuelve null siempre", "Devuelve 0",
          "Genera underflow / excepción", "Inserta un elemento", 2),
        p("¿Cómo implementa la JVM el call stack?",
          "Como una cola FIFO",
          "Como una pila donde cada llamada a método empuja un frame",
          "Como un árbol binario",
          "Como una tabla hash", 1)
    );}

    // ---- Clase 11: Colas ----
    private List<PreguntaDTO> clase11() { return List.of(
        p("¿Qué política de acceso sigue una cola (Queue)?",
          "LIFO", "FIFO", "Aleatoria", "Por prioridad siempre", 1),
        p("¿Cuáles son las operaciones básicas del TDA Cola?",
          "push y pop", "enqueue y dequeue", "insert y delete", "add y remove siempre en O(1)", 1),
        p("¿Qué es una cola circular?",
          "Una cola que solo almacena números",
          "Una implementación de cola sobre array que reutiliza posiciones libres al inicio",
          "Una cola que no tiene límite de tamaño",
          "Una cola donde el frente y el fin son el mismo nodo", 1),
        p("¿En qué algoritmo clásico se usa una cola?",
          "Búsqueda en profundidad (DFS)", "Ordenamiento rápido",
          "Búsqueda en anchura (BFS)", "Evaluación de expresiones", 2),
        p("¿Cuál es la complejidad de enqueue y dequeue en una cola bien implementada?",
          "O(n)", "O(log n)", "O(n²)", "O(1)", 3)
    );}

    // ---- Clase 12: Colas Avanzadas ----
    private List<PreguntaDTO> clase12() { return List.of(
        p("¿Qué es una cola de prioridad?",
          "Una cola donde los elementos se ordenan alfabéticamente",
          "Una cola donde cada elemento tiene una prioridad y el mayor/menor sale primero",
          "Una cola con dos entradas",
          "Una cola que solo acepta enteros", 1),
        p("¿Cuál es la estructura más eficiente para implementar una cola de prioridad?",
          "Array ordenado", "Lista enlazada simple", "Heap binario", "Árbol AVL", 2),
        p("¿Qué es un Deque (Double-Ended Queue)?",
          "Una cola con prioridad doble",
          "Una estructura donde se puede insertar y eliminar por ambos extremos",
          "Una cola que acepta solo pares de elementos",
          "Una cola circular", 1),
        p("¿Cuál es la complejidad de insertar en una cola de prioridad basada en heap?",
          "O(1)", "O(log n)", "O(n)", "O(n log n)", 1),
        p("¿Para qué sirve el algoritmo de Dijkstra junto con una cola de prioridad?",
          "Ordenar grafos", "Detectar ciclos",
          "Encontrar el camino más corto en grafos ponderados",
          "Balancear árboles", 2)
    );}

    // ---- Clase 13: Recursión ----
    private List<PreguntaDTO> clase13() { return List.of(
        p("¿Qué dos partes debe tener toda función recursiva correcta?",
          "Caso recursivo y caso de error",
          "Caso base y caso recursivo",
          "Iteración y llamada a sí misma",
          "Parámetro y valor de retorno", 1),
        p("¿Qué pasa si una función recursiva no tiene caso base?",
          "Retorna null", "Funciona más rápido",
          "Produce recursión infinita y Stack Overflow",
          "El compilador la optimiza", 2),
        p("¿Cuál es la complejidad de calcular fibonacci(n) con recursión simple?",
          "O(n)", "O(n log n)", "O(2^n) exponencial por recalcular subproblemas",
          "O(log n)", 2),
        p("¿Qué es la recursión de cola (tail recursion)?",
          "Recursión donde el caso base es la cola de una lista",
          "Recursión donde la llamada recursiva es la última operación de la función",
          "Recursión que usa una cola como estructura auxiliar",
          "Recursión que siempre retorna void", 1),
        p("¿Qué estructura implícita usa la recursión en memoria?",
          "Heap", "Cola", "Pila de llamadas (call stack)", "Tabla hash", 2)
    );}

    // ---- Clase 14: Backtracking ----
    private List<PreguntaDTO> clase14() { return List.of(
        p("¿En qué consiste el paradigma de Backtracking?",
          "Explorar todas las posibilidades y retroceder cuando se llega a un callejón sin salida",
          "Dividir el problema en subproblemas independientes",
          "Memorizar resultados de subproblemas",
          "Ordenar el espacio de búsqueda antes de explorar", 0),
        p("¿Cuál de los siguientes problemas se resuelve típicamente con backtracking?",
          "Ordenamiento merge sort", "Cálculo de fibonacci",
          "El problema de las N-Reinas", "Búsqueda binaria", 2),
        p("¿Qué es la 'poda' en backtracking?",
          "Eliminar nodos del árbol de búsqueda",
          "Reducir el número de recursiones cortando ramas que no pueden llevar a solución",
          "Ordenar el árbol de decisiones",
          "Fusionar ramas del árbol", 1),
        p("¿Cómo se diferencia backtracking de búsqueda exhaustiva (brute force)?",
          "No hay diferencia",
          "Backtracking poda ramas inválidas temprano, evitando explorar todo",
          "Brute force es siempre más eficiente",
          "Backtracking no usa recursión", 1),
        p("¿Cuál es la complejidad en el peor caso del backtracking?",
          "O(n)", "O(n log n)",
          "Exponencial O(k^n) donde k es el factor de ramificación",
          "O(1)", 2)
    );}

    // ---- Clase 15: Divide y Vencerás ----
    private List<PreguntaDTO> clase15() { return List.of(
        p("¿En qué consiste el paradigma Divide y Vencerás?",
          "Dividir el problema en subproblemas superpuestos y memorizarlos",
          "Dividir en subproblemas independientes, resolver recursivamente y combinar",
          "Resolver el problema de forma iterativa siempre",
          "Dividir datos en partes iguales sin recursión", 1),
        p("¿Cuál es la complejidad de MergeSort?",
          "O(n²)", "O(n)", "O(n log n)", "O(log n)", 2),
        p("¿Qué garantiza MergeSort frente a QuickSort?",
          "Siempre usa menos memoria",
          "Es siempre más rápido",
          "Tiene complejidad O(n log n) garantizada incluso en el peor caso",
          "No necesita memoria auxiliar", 2),
        p("¿Qué resuelve el algoritmo de Karatsuba con Divide y Vencerás?",
          "Ordenamiento de arrays",
          "Multiplicación de enteros grandes más eficientemente que el método clásico",
          "Búsqueda en grafos",
          "Compresión de datos", 1),
        p("¿Cuál es la complejidad de la búsqueda binaria (un caso de D&V)?",
          "O(n)", "O(n²)", "O(log n)", "O(1)", 2)
    );}

    // ---- Clase 16: Árboles ----
    private List<PreguntaDTO> clase16() { return List.of(
        p("¿Cómo se llama el nodo sin padre en un árbol?",
          "Hoja", "Raíz", "Nivel 0", "Padre nulo", 1),
        p("¿Cómo se llama un nodo sin hijos?",
          "Raíz", "Interno", "Hoja", "Padre", 2),
        p("¿Cuántos padres puede tener un nodo en un árbol (no raíz)?",
          "0", "1", "2", "Ilimitados", 1),
        p("¿Qué es la altura de un árbol?",
          "El número de nodos totales",
          "La longitud del camino más largo desde la raíz hasta una hoja",
          "El número de hojas",
          "El nivel de la raíz", 1),
        p("¿Qué es un árbol degenerado?",
          "Un árbol perfectamente balanceado",
          "Un árbol donde cada nodo tiene exactamente 2 hijos",
          "Un árbol donde cada nodo tiene a lo sumo 1 hijo (como una lista enlazada)",
          "Un árbol vacío", 2)
    );}

    // ---- Clase 17: Árboles Binarios ----
    private List<PreguntaDTO> clase17() { return List.of(
        p("¿Cuántos hijos puede tener como máximo un nodo en un árbol binario?",
          "1", "2", "3", "Ilimitados", 1),
        p("¿Cuántos nodos tiene un árbol binario lleno de altura h?",
          "h", "2h", "2^(h+1) - 1", "h²", 2),
        p("¿Qué es un árbol binario completo?",
          "Todos los nodos tienen 0 o 2 hijos y las hojas están al mismo nivel",
          "Un árbol donde todos los niveles están completamente llenos excepto el último, llenado de izquierda a derecha",
          "Un árbol donde todos los nodos tienen 2 hijos",
          "Un árbol con exactamente n hojas", 1),
        p("¿Qué operación necesitas para construir un árbol binario de expresiones aritméticas?",
          "BFS", "Recorrido inorder", "Inserción con precedencia de operadores", "MergeSort", 2),
        p("¿Cuál es la relación entre árboles binarios y el algoritmo Huffman?",
          "Huffman usa árboles AVL",
          "Huffman construye un árbol binario donde los caracteres más frecuentes tienen caminos más cortos",
          "Huffman no usa árboles",
          "Solo aplica a árboles de búsqueda", 1)
    );}

    // ---- Clase 18: Recorridos ----
    private List<PreguntaDTO> clase18() { return List.of(
        p("¿En qué orden visita los nodos el recorrido InOrder?",
          "Raíz → Izquierdo → Derecho",
          "Izquierdo → Derecho → Raíz",
          "Izquierdo → Raíz → Derecho",
          "Derecho → Raíz → Izquierdo", 2),
        p("¿Qué recorrido de un BST produce los elementos en orden ascendente?",
          "PreOrder", "PostOrder", "InOrder", "BFS", 2),
        p("¿Qué estructura de datos usa BFS (Breadth-First Search)?",
          "Pila", "Cola", "Árbol AVL", "HashMap", 1),
        p("¿Qué estructura de datos usa DFS iterativo?",
          "Cola", "Tabla Hash", "Pila", "Array ordenado", 2),
        p("¿En qué recorrido se visita la raíz primero?",
          "InOrder", "PostOrder", "PreOrder", "LevelOrder", 2)
    );}

    // ---- Clase 19: BST ----
    private List<PreguntaDTO> clase19() { return List.of(
        p("¿Qué propiedad define a un Árbol Binario de Búsqueda (BST)?",
          "Todos los nodos tienen exactamente 2 hijos",
          "El nodo izquierdo es mayor que la raíz",
          "Para cada nodo, todos los valores del subárbol izquierdo son menores y los del derecho son mayores",
          "La altura es siempre log n", 2),
        p("¿Cuál es la complejidad promedio de búsqueda en un BST balanceado?",
          "O(1)", "O(n)", "O(log n)", "O(n²)", 2),
        p("¿Cuál es la complejidad de búsqueda en un BST degenerado (lista)?",
          "O(1)", "O(log n)", "O(n)", "O(n log n)", 2),
        p("¿Cómo se inserta un elemento en un BST?",
          "Siempre en la raíz",
          "Comparando con cada nodo y siguiendo izquierda o derecha hasta encontrar posición libre",
          "Al final de la lista de nodos",
          "Ordenando todos los elementos y reconstruyendo el árbol", 1),
        p("¿Cuál es el sucesor inorder de un nodo en un BST?",
          "Su hijo derecho directo",
          "El nodo más pequeño del subárbol derecho",
          "Su padre",
          "El nodo más grande del subárbol izquierdo", 1)
    );}

    // ---- Clase 20: Operaciones BST ----
    private List<PreguntaDTO> clase20() { return List.of(
        p("¿Cuántos casos tiene la eliminación en un BST?",
          "1", "2", "3 (hoja, un hijo, dos hijos)", "4", 2),
        p("¿Qué se hace al eliminar un nodo con dos hijos en un BST?",
          "Se elimina directamente el nodo",
          "Se reemplaza con su predecesor o sucesor inorder y se elimina ese nodo",
          "Se elimina todo el subárbol",
          "Se reconecta el hijo izquierdo al padre", 1),
        p("¿Qué es el factor de balanceo en un árbol?",
          "El número de hojas menos el número de nodos internos",
          "La diferencia de alturas entre el subárbol izquierdo y el derecho",
          "El número de rotaciones realizadas",
          "La profundidad del nodo más hondo", 1),
        p("¿Por qué un BST puede degradarse a O(n) en búsqueda?",
          "Siempre se degrada con n > 100",
          "Cuando los elementos se insertan en orden ascendente o descendente, formando una lista",
          "Cuando tiene hojas",
          "Cuando la raíz tiene dos hijos", 1),
        p("¿Qué estructura corrige el problema de desbalanceo del BST?",
          "Lista enlazada", "Array ordenado", "Árbol AVL o Rojo-Negro", "Tabla hash", 2)
    );}

    // ---- Clase 21: AVL ----
    private List<PreguntaDTO> clase21() { return List.of(
        p("¿Cuál es la condición de balance en un árbol AVL?",
          "Todos los nodos deben tener 2 hijos",
          "La diferencia de altura entre subárbol izquierdo y derecho de cada nodo es a lo sumo 1",
          "Todos los niveles deben estar completamente llenos",
          "La raíz siempre tiene el mayor valor", 1),
        p("¿Cuántos tipos de rotaciones existen en AVL?",
          "1", "2 (simple y doble)", "4 (LL, RR, LR, RL)", "6", 2),
        p("¿Cuándo se aplica una rotación doble izquierda-derecha (LR) en AVL?",
          "Cuando el desbalance es en el hijo izquierdo del subárbol izquierdo",
          "Cuando el desbalance es en el hijo derecho del subárbol izquierdo",
          "Cuando el desbalance es en el hijo derecho del subárbol derecho",
          "Cuando la raíz tiene factor de balanceo 0", 1),
        p("¿Cuál es la complejidad garantizada de búsqueda, inserción y eliminación en AVL?",
          "O(1)", "O(n)", "O(n²)", "O(log n)", 3),
        p("¿Qué ventaja tiene un árbol Rojo-Negro sobre AVL?",
          "Mejor complejidad de búsqueda",
          "Menos rotaciones en promedio durante inserciones y eliminaciones frecuentes",
          "Menor altura garantizada",
          "Permite duplicados", 1)
    );}

    // ---- Clase 22: Heap ----
    private List<PreguntaDTO> clase22() { return List.of(
        p("¿Qué propiedad define a un Max-Heap?",
          "Cada nodo es menor que sus hijos",
          "El árbol está perfectamente balanceado",
          "Cada nodo es mayor o igual que sus hijos",
          "Solo puede tener hojas en el último nivel", 2),
        p("¿Cómo se representa típicamente un heap en memoria?",
          "Como lista enlazada doble", "Como array con fórmulas para padre e hijos",
          "Como árbol AVL", "Como tabla hash", 1),
        p("¿Qué es la operación 'heapify'?",
          "Crear un heap desde cero",
          "Eliminar la raíz del heap",
          "Restaurar la propiedad de heap después de una modificación",
          "Ordenar el heap completamente", 2),
        p("¿Cuál es la complejidad de construir un heap desde un array de n elementos?",
          "O(n log n)", "O(n²)", "O(n) usando heapify bottom-up", "O(log n)", 2),
        p("¿Qué algoritmo de ordenamiento usa un heap?",
          "MergeSort", "QuickSort", "HeapSort", "InsertionSort", 2)
    );}

    // ---- Clase 23: Colas de Prioridad ----
    private List<PreguntaDTO> clase23() { return List.of(
        p("¿Cuál es la complejidad de extraer el máximo de una cola de prioridad basada en Max-Heap?",
          "O(1)", "O(log n)", "O(n)", "O(n log n)", 1),
        p("¿En qué se diferencia una cola de prioridad de una cola FIFO?",
          "La cola de prioridad no tiene límite de tamaño",
          "En la cola de prioridad, el elemento con mayor prioridad sale primero independientemente de la llegada",
          "La cola FIFO es más eficiente siempre",
          "No hay diferencia", 1),
        p("¿Qué clase de Java implementa la cola de prioridad?",
          "java.util.Stack", "java.util.LinkedList",
          "java.util.PriorityQueue", "java.util.TreeMap", 2),
        p("¿Cómo se implementa un sistema de atención médica de urgencias con esta estructura?",
          "Los pacientes más antiguos salen primero (FIFO)",
          "Los pacientes con mayor urgencia tienen mayor prioridad y salen antes",
          "Se atienden por orden alfabético",
          "Se atienden aleatoriamente", 1),
        p("¿Cuál es la complejidad de insertar en una PriorityQueue de Java (Min-Heap)?",
          "O(1)", "O(log n)", "O(n)", "O(n log n)", 1)
    );}

    // ---- Clase 24: Tablas Hash ----
    private List<PreguntaDTO> clase24() { return List.of(
        p("¿Qué hace una función hash?",
          "Ordena elementos",
          "Mapea una clave a un índice en un array",
          "Comprime datos",
          "Busca en un árbol", 1),
        p("¿Cuál es la complejidad promedio de búsqueda en una tabla hash bien diseñada?",
          "O(n)", "O(log n)", "O(n²)", "O(1)", 3),
        p("¿Qué es una colisión en una tabla hash?",
          "Cuando la tabla está vacía",
          "Cuando la función hash arroja el mismo índice para dos claves distintas",
          "Cuando se elimina un elemento",
          "Cuando el factor de carga es 0", 1),
        p("¿Qué es el factor de carga de una tabla hash?",
          "El número de colisiones total",
          "La velocidad de la función hash",
          "n/m donde n es número de elementos y m es el tamaño del array",
          "El número de buckets vacíos", 2),
        p("¿Cuándo se debe redimensionar una tabla hash?",
          "Nunca, el tamaño es fijo",
          "Cuando el factor de carga supera un umbral (típicamente 0.75)",
          "Solo cuando se llena completamente",
          "Cada vez que se inserta un elemento", 1)
    );}

    // ---- Clase 25: Funciones Hash ----
    private List<PreguntaDTO> clase25() { return List.of(
        p("¿Cuál de las siguientes es una propiedad deseable de una función hash?",
          "Lenta para ser segura",
          "Distribución uniforme de claves en el array",
          "Generar muchas colisiones para reutilizar espacio",
          "Ser irreversible siempre", 1),
        p("¿Qué hace el método de división para calcular un hash?",
          "Divide el array en partes",
          "Calcula hash = clave mod m (tamaño de la tabla)",
          "Multiplica la clave por una constante",
          "Usa XOR de los bytes", 1),
        p("¿Por qué se prefiere que m (tamaño de tabla) sea un número primo?",
          "Para ser más fácil de recordar",
          "Para reducir colisiones al distribuir mejor las claves con división",
          "Para ahorrar memoria",
          "Para que el redimensionamiento sea exacto", 1),
        p("¿Qué es el método de multiplicación para calcular hash?",
          "Multiplicar todos los caracteres de una cadena",
          "hash = floor(m * frac(clave * A)) donde A es una constante entre 0 y 1",
          "Multiplicar el tamaño de la tabla por la clave",
          "Multiplicar el hash anterior por el nuevo", 1),
        p("¿Qué función hash usa Java para objetos por defecto?",
          "MD5 del objeto", "Dirección de memoria transformada por hashCode()",
          "CRC32 de los bytes", "La posición en el array", 1)
    );}

    // ---- Clase 26: Colisiones ----
    private List<PreguntaDTO> clase26() { return List.of(
        p("¿En qué consiste el encadenamiento (chaining) para resolver colisiones?",
          "Buscar la siguiente posición libre en el array",
          "Almacenar los elementos que colisionan en una lista enlazada en el mismo bucket",
          "Usar dos tablas hash",
          "Calcular un segundo hash", 1),
        p("¿En qué consiste el sondeo lineal (linear probing)?",
          "Usar dos funciones hash",
          "Al colisionar, buscar el siguiente slot libre avanzando de 1 en 1",
          "Almacenar en una lista externa",
          "Redimensionar inmediatamente al colisionar", 1),
        p("¿Qué problema tiene el sondeo lineal?",
          "Siempre genera más colisiones que chaining",
          "Clustering primario: grupos de celdas ocupadas que hacen las búsquedas más lentas",
          "No permite eliminación",
          "Solo funciona con claves enteras", 1),
        p("¿Cómo evita el sondeo cuadrático el clustering primario?",
          "No lo evita",
          "Avanzando i² posiciones en el intento i, dispersando más las colisiones",
          "Usando listas enlazadas",
          "Redimensionando al 75%", 1),
        p("¿Qué es el double hashing?",
          "Usar un array de dos dimensiones",
          "Usar dos funciones hash; si hay colisión, el paso es h2(clave)",
          "Calcular hash dos veces con la misma función",
          "Almacenar en dos tablas distintas", 1)
    );}

    // ---- Clase 27: Grafos ----
    private List<PreguntaDTO> clase27() { return List.of(
        p("¿Qué es un grafo?",
          "Un árbol con más de 2 hijos por nodo",
          "Una estructura de datos con nodos (vértices) y conexiones (aristas) entre ellos",
          "Una tabla bidimensional",
          "Una lista de listas", 1),
        p("¿Qué diferencia a un grafo de un árbol?",
          "Los grafos tienen nodos y los árboles no",
          "En un grafo pueden existir ciclos; en un árbol no",
          "Los árboles son más rápidos siempre",
          "Los grafos solo pueden ser no dirigidos", 1),
        p("¿Qué es el grado de un vértice en un grafo no dirigido?",
          "Su posición en el array",
          "El número de aristas que inciden en él",
          "Su distancia desde la raíz",
          "El número de vértices adyacentes dirigidos", 1),
        p("¿Cuál es la suma de grados de todos los vértices en un grafo no dirigido con E aristas?",
          "E", "2E (cada arista contribuye 2 grados)", "E/2", "E²", 1),
        p("¿Qué es un grafo conexo?",
          "Un grafo sin ciclos",
          "Un grafo donde existe camino entre todo par de vértices",
          "Un grafo con aristas pesadas",
          "Un grafo completamente dirigido", 1)
    );}

    // ---- Clase 28: Representación de Grafos ----
    private List<PreguntaDTO> clase28() { return List.of(
        p("¿Cuánta memoria ocupa una matriz de adyacencia para un grafo de V vértices?",
          "O(V)", "O(E)", "O(V²)", "O(V+E)", 2),
        p("¿Cuándo conviene usar lista de adyacencia sobre matriz de adyacencia?",
          "Cuando el grafo es denso (muchas aristas)",
          "Cuando el grafo es disperso (pocas aristas relativas a V²)",
          "Cuando V es pequeño",
          "Siempre es mejor la lista", 1),
        p("¿Cuánta memoria ocupa una lista de adyacencia?",
          "O(V²)", "O(V+E)", "O(E²)", "O(V*E)", 1),
        p("¿Cuál es la complejidad de verificar si existe arista (u,v) con matriz de adyacencia?",
          "O(V)", "O(E)", "O(1)", "O(log V)", 2),
        p("¿Cuál es la complejidad de listar todos los vecinos de un vértice con lista de adyacencia?",
          "O(V)", "O(grado del vértice)", "O(V²)", "O(1)", 1)
    );}

    // ---- Clase 29: BFS y DFS ----
    private List<PreguntaDTO> clase29() { return List.of(
        p("¿Qué estructura usa BFS internamente?",
          "Pila", "Cola", "Heap", "Árbol AVL", 1),
        p("¿Qué estructura usa DFS iterativo internamente?",
          "Cola", "Pila", "Deque", "Array", 1),
        p("¿Qué garantiza BFS que DFS no garantiza en grafos no ponderados?",
          "Encontrar todos los nodos",
          "Encontrar el camino más corto (en número de aristas)",
          "Menor uso de memoria",
          "Detección de ciclos", 1),
        p("¿Cuál es la complejidad de BFS y DFS con lista de adyacencia?",
          "O(V²)", "O(V*E)", "O(V+E)", "O(E²)", 2),
        p("¿Para qué sirve marcar nodos como 'visitados' en BFS/DFS?",
          "Para ordenarlos",
          "Para evitar procesar el mismo nodo múltiples veces y prevenir bucles infinitos",
          "Para calcular su grado",
          "Para guardar el camino", 1)
    );}

    // ---- Clase 30: Grafos Dirigidos ----
    private List<PreguntaDTO> clase30() { return List.of(
        p("¿Qué es un dígrafo (grafo dirigido)?",
          "Un grafo sin ciclos",
          "Un grafo donde las aristas tienen una dirección (de u a v no implica de v a u)",
          "Un grafo con pesos en los vértices",
          "Un grafo bipartito", 1),
        p("¿Qué diferencia hay entre in-grado y out-grado en un dígrafo?",
          "No hay diferencia",
          "In-grado: aristas que entran; out-grado: aristas que salen del vértice",
          "In-grado es siempre mayor",
          "Out-grado es el grado total", 1),
        p("¿Qué es un DAG?",
          "Grafo dirigido y acíclico (Directed Acyclic Graph)",
          "Grafo con aristas de doble sentido",
          "Grafo con aristas pesadas",
          "Grafo bipartito dirigido", 0),
        p("¿Para qué sirve el ordenamiento topológico?",
          "Para ordenar vértices por grado",
          "Para encontrar el camino más corto",
          "Para ordenar vértices de un DAG respetando la dirección de todas las aristas",
          "Para detectar componentes fuertemente conexas", 2),
        p("¿Qué algoritmo encuentra componentes fuertemente conexas en un dígrafo?",
          "Dijkstra", "BFS simple",
          "Kosaraju o Tarjan", "MergeSort", 2)
    );}

    // ---- Clase 31: Dijkstra ----
    private List<PreguntaDTO> clase31() { return List.of(
        p("¿Para qué tipo de grafos funciona correctamente Dijkstra?",
          "Solo grafos no dirigidos",
          "Grafos con aristas de peso negativo",
          "Grafos con pesos no negativos en las aristas",
          "Solo árboles", 2),
        p("¿Qué estructura de datos mejora la complejidad de Dijkstra?",
          "Array simple", "Lista enlazada",
          "Cola de prioridad (Min-Heap)", "Pila", 2),
        p("¿Cuál es la complejidad de Dijkstra con cola de prioridad y lista de adyacencia?",
          "O(V²)", "O(V+E)", "O((V+E) log V)", "O(E²)", 2),
        p("¿Qué garantiza Dijkstra al finalizar?",
          "El árbol de expansión mínima",
          "El camino más corto desde el vértice origen a todos los demás vértices",
          "El ciclo de menor costo",
          "El ordenamiento topológico", 1),
        p("¿Qué algoritmo usar si hay aristas con peso negativo?",
          "Dijkstra con valores absolutos",
          "BFS", "Bellman-Ford", "Kruskal", 2)
    );}

    // ---- Clase 32: Prim y Kruskal ----
    private List<PreguntaDTO> clase32() { return List.of(
        p("¿Qué es un Árbol de Expansión Mínima (MST)?",
          "El árbol más alto del grafo",
          "Un subgrafo árbol que conecta todos los vértices con la mínima suma de pesos",
          "Un árbol AVL del grafo",
          "El camino más corto entre dos vértices", 1),
        p("¿Qué diferencia el enfoque de Prim vs Kruskal?",
          "Prim encuentra MST; Kruskal caminos mínimos",
          "Prim crece el MST desde un vértice; Kruskal ordena aristas y las añade sin ciclos",
          "Kruskal es para grafos dirigidos; Prim para no dirigidos",
          "No hay diferencia de enfoque", 1),
        p("¿Qué estructura usa Kruskal para detectar ciclos eficientemente?",
          "Árbol AVL", "Tabla hash",
          "Union-Find (Disjoint Set)", "Cola de prioridad", 2),
        p("¿Cuántas aristas tiene un MST de un grafo con V vértices?",
          "V", "V+1", "V-1", "V²", 2),
        p("¿Cuál es la complejidad de Kruskal?",
          "O(V²)", "O(E log E) por el ordenamiento de aristas",
          "O(V+E)", "O(E²)", 1)
    );}

    // ---- Clase 33: Detección de Ciclos ----
    private List<PreguntaDTO> clase33() { return List.of(
        p("¿Cómo se detecta un ciclo en un grafo no dirigido con DFS?",
          "Si se encuentra un nodo con grado 0",
          "Si durante DFS se visita un nodo adyacente ya visitado (distinto al padre inmediato)",
          "Si el número de aristas es mayor que el de vértices",
          "Si hay un nodo con in-grado = 0", 1),
        p("¿Cómo se detecta un ciclo en un grafo dirigido con DFS?",
          "Si se visita cualquier nodo ya visitado",
          "Si se encuentra un back-edge (arista a un ancestro en la pila de recursión actual)",
          "Si hay nodos con out-grado = 0",
          "Si la suma de pesos es negativa", 1),
        p("¿Qué es un back-edge en DFS de grafo dirigido?",
          "Una arista que va de hijo a padre directo",
          "Una arista que conecta un nodo con un ancestro activo en el stack de recursión",
          "Una arista con peso negativo",
          "La última arista recorrida", 1),
        p("¿Qué relación hay entre ciclos y ordenamiento topológico?",
          "Los grafos con ciclos se ordenan topológicamente con facilidad",
          "El ordenamiento topológico solo existe en DAGs (sin ciclos)",
          "Los ciclos ayudan al ordenamiento topológico",
          "No hay relación", 1),
        p("¿Cuál es la complejidad de detectar ciclos en un grafo con V vértices y E aristas?",
          "O(V²)", "O(V*E)", "O(V+E)", "O(E log E)", 2)
    );}

    // ---- Clase 34: Union-Find ----
    private List<PreguntaDTO> clase34() { return List.of(
        p("¿Para qué sirve la estructura Union-Find?",
          "Para ordenar grafos",
          "Para gestionar eficientemente conjuntos disjuntos con operaciones union y find",
          "Para buscar el camino más corto",
          "Para balancear árboles", 1),
        p("¿Qué hace la operación 'find' en Union-Find?",
          "Busca el elemento de menor valor",
          "Encuentra el representante (raíz) del conjunto al que pertenece un elemento",
          "Elimina un elemento del conjunto",
          "Fusiona dos conjuntos", 1),
        p("¿Qué optimización es la 'compresión de caminos' (path compression)?",
          "Reducir el número de conjuntos",
          "Hacer que todos los nodos en el camino de find apunten directamente a la raíz",
          "Comprimir los datos almacenados",
          "Reducir el tamaño del array", 1),
        p("¿Qué es la unión por rango (union by rank)?",
          "Unir conjuntos según el tamaño de los datos",
          "Unir el árbol de menor rango bajo el de mayor rango para mantener árboles planos",
          "Ordenar los elementos antes de unir",
          "Unir solo si los rangos son iguales", 1),
        p("¿Cuál es la complejidad amortizada de find con path compression y union by rank?",
          "O(log n)", "O(n)", "O(α(n)) casi constante (función inversa de Ackermann)",
          "O(n log n)", 2)
    );}

    // ---- Clase 35: Trie ----
    private List<PreguntaDTO> clase35() { return List.of(
        p("¿Qué tipo de datos almacena típicamente un Trie?",
          "Números enteros ordenados",
          "Cadenas de caracteres usando prefijos compartidos",
          "Pares clave-valor como un mapa",
          "Grafos de palabras", 1),
        p("¿Cuál es la complejidad de buscar una cadena de longitud L en un Trie?",
          "O(n) donde n es el número de cadenas",
          "O(L) donde L es la longitud de la cadena",
          "O(n*L)",
          "O(log n)", 1),
        p("¿Qué ventaja tiene el Trie sobre un BST para búsqueda de strings?",
          "Menor uso de memoria siempre",
          "Búsqueda en O(L) independientemente del número de cadenas almacenadas",
          "Soporte de claves numéricas",
          "Menor número de nodos", 1),
        p("¿Para qué se usa un Trie en aplicaciones reales?",
          "Ordenamiento de arrays",
          "Autocompletado, corrección ortográfica y búsqueda de prefijos",
          "Compresión de imágenes",
          "Cálculo de rutas en grafos", 1),
        p("¿Cuántos hijos puede tener cada nodo de un Trie sobre el alfabeto inglés?",
          "2", "10", "26 (una por cada letra)",
          "Depende del nivel del nodo", 2)
    );}

    // ---- Clase 36: Segment Tree y Fenwick Tree ----
    private List<PreguntaDTO> clase36() { return List.of(
        p("¿Para qué tipo de problema es ideal un Segment Tree?",
          "Búsqueda de prefijos en strings",
          "Consultas de rango (sum, min, max) y actualizaciones puntuales en O(log n)",
          "Detección de ciclos en grafos",
          "Ordenamiento de arrays", 1),
        p("¿Cuánta memoria usa un Segment Tree para un array de n elementos?",
          "O(n)", "O(n log n)", "O(4n) ≈ O(n)", "O(n²)", 2),
        p("¿Qué ventaja tiene el Fenwick Tree (Binary Indexed Tree) sobre el Segment Tree?",
          "Soporta más tipos de consultas",
          "Menor uso de memoria y código más simple, aunque con operaciones más limitadas",
          "Menor complejidad de consulta",
          "Permite actualizaciones de rango", 1),
        p("¿Cuál es la complejidad de consulta de suma de rango en un Fenwick Tree?",
          "O(1)", "O(n)", "O(log n)", "O(n log n)", 2),
        p("¿Qué operación matemática aprovecha el Fenwick Tree usando bits?",
          "Multiplicación de matrices",
          "La representación binaria del índice para saltar sobre rangos (lowbit: i & (-i))",
          "La división entre potencias de 2",
          "XOR para calcular paridad", 1)
    );}

    // ---- Cuestionario genérico ----
    private List<PreguntaDTO> cuestionarioGenerico(int n) {
        return List.of(
            p("¿Qué es una estructura de datos?",
              "Un tipo de base de datos",
              "Una forma de organizar datos en memoria para acceso eficiente",
              "Un lenguaje de programación",
              "Un patrón de diseño", 1),
            p("¿Qué mide Big-O?",
              "El tamaño del programa", "La velocidad del CPU",
              "La complejidad del algoritmo en función del tamaño de entrada", "El consumo de RAM", 2),
            p("¿Qué es la recursión?",
              "Un bucle for", "Una función que se llama a sí misma con casos base y recursivos",
              "Un tipo de array", "Un patrón de diseño", 1),
            p("¿Cuál estructura usa LIFO?",
              "Cola", "Lista enlazada", "Pila (Stack)", "Árbol", 2),
            p("¿Cuál estructura usa FIFO?",
              "Pila", "Cola (Queue)", "Árbol binario", "Tabla hash", 1)
        );
    }
}
