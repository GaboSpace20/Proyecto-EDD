/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDDauxiliares;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementar algoritmo de (DFS) y (BDS) en el grafo
 * @author Kelvin Hurtado
 * @version: 19/06/25
 */
public class Grafo {


/**
 * Clase Grafo para representar el tablero de la sopa de letras y realizar búsquedas.
 */


    private char[][] tablero;
    private int filas;
    private int columnas;

    // Direcciones para los 8 vecinos (horizontal, vertical, diagonal)
    private final int[] dFila = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dColumna = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * Constructor del Grafo.
     * @param tablero El tablero de caracteres (4x4 para este proyecto).
     */
    public Grafo(char[][] tablero) {
        if (tablero == null || tablero.length == 0 || tablero[0].length == 0) {
            throw new IllegalArgumentException("El tablero no puede ser nulo o vacío.");
        }
        this.filas = tablero.length;
        this.columnas = tablero[0].length;
        this.tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(tablero[i], 0, this.tablero[i], 0, columnas);
        }
    }

    /**
     * Valida si una celda (fila, columna) está dentro de los límites del tablero.
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     * @return true si la celda es válida, false en caso contrario.
     */
    private boolean esValido(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    /**
     *  Aqui puedo obtener las adyacencias de las celdas
     * @param celda La celda de la que se quieren obtener los vecinos.
     * @return Una lista de objetos Celda que representan los vecinos válidos.
     */
    public List<Celda> obtenerVecinos(Celda celda) {
        List<Celda> vecinos = new ArrayList<>();
        for (int i = 0; i < 8; i++) { // Pueden haber 8 posibles direcciones
            int nuevaFila = celda.fila + dFila[i];
            int nuevaColumna = celda.columna + dColumna[i];
            if (esValido(nuevaFila, nuevaColumna)) {
                vecinos.add(new Celda(nuevaFila, nuevaColumna));
            }
        }
        return vecinos;
    }

    /**
     * Obtiene la letra en una celda específica.
     * @param celda La celda de la que se quiere la letra.
     * @return La letra en la celda.
     */
    public char getLetra(Celda celda) {
        if (!esValido(celda.fila, celda.columna)) {
            throw new IllegalArgumentException("Celda fuera de los límites del tablero.");
        }
        return tablero[celda.fila][celda.columna];
    }

    // --- Implementación de los Algoritmos de Búsqueda ---

    /**
     * Esta clase es para representar un estado en la búsqueda (necesario para DFS/BFS)
     * para mantener un seguimiento de la palabra actual y las celdas usadas.
     */
    private static class EstadoBusqueda {
        Celda celdaActual;
        String palabraActual;
        Set<Celda> celdasUsadas; // Para el requisito de "usar cada letra una vez"

        EstadoBusqueda(Celda celdaActual, String palabraActual, Set<Celda> celdasUsadas) {
            this.celdaActual = celdaActual;
            this.palabraActual = palabraActual;
            this.celdasUsadas = new HashSet<>(celdasUsadas); // Copia para no modificar el set original
            this.celdasUsadas.add(celdaActual); // Agrega la celda actual al set de usadas
        }
    }


    /**
     * Esto es para buscar la palabraa en el tablero (BFS)
     * @param palabra La palabra a buscar (en mayúsculas).
     * @return Una lista de Celdas que forman la palabra si se encuentra, o null si no.
     */
    public List<Celda> buscarPalabraBFS(String palabra) {
        if (palabra == null || palabra.length() < 3) {
            return null; // Las palabras válidas deben tener al menos 3 letras.
        }
        String palabraUpper = palabra.toUpperCase(); // Esto asegura que sean mayuscula en la búsqueda

        // Intentar encontrar la palabra desde cada celda del tablero como punto de inicio
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                Celda inicio = new Celda(r, c);
                // Si la primera letra coincide, iniciar BFS desde aquí
                if (getLetra(inicio) == palabraUpper.charAt(0)) {
                    List<Celda> resultado = bfsDesdeCelda(palabraUpper, inicio);
                    if (resultado != null) {
                        return resultado; // Palabra encontrada
                    }
                }
            }
        }
        return null; // No se encontro la palabra en ningun punto
    }

    private List<Celda> bfsDesdeCelda(String palabraBuscada, Celda inicio) {
        Cola<EstadoBusqueda> cola = new Cola<>();
        Set<Celda> celdasInicialesUsadas = new HashSet<>();
        EstadoBusqueda estadoInicial = new EstadoBusqueda(inicio, String.valueOf(getLetra(inicio)), celdasInicialesUsadas);
        cola.encolar(estadoInicial);

        while (!cola.isEmpty()) {
            EstadoBusqueda actual = cola.desencolar();

            
            if (actual.palabraActual.equals(palabraBuscada)) {      // Si la palabra actual coincide con la palabra buscada
               
                List<Celda> caminoReconstruido = new ArrayList<>();
                caminoReconstruido.add(inicio); // Solo si es el inicio

                 return new ArrayList<>(actual.celdasUsadas); // Esto es una aproximación
                                                            
            }

           
            if (actual.palabraActual.length() >= palabraBuscada.length() ||
                !palabraBuscada.startsWith(actual.palabraActual)) {
                continue; // No puede ser la palabra, o ya es muy larga
            }

            // Explora las letras vecinas de la posible palabra
            List<Celda> vecinos = obtenerVecinos(actual.celdaActual);
            for (Celda vecino : vecinos) {
                // Solo si el vecino no ha sido usado en la palabra actual y la letra coincide con la siguiente ---(importante esto)---
                if (!actual.celdasUsadas.contains(vecino) &&
                    actual.palabraActual.length() < palabraBuscada.length() &&
                    getLetra(vecino) == palabraBuscada.charAt(actual.palabraActual.length())) {

                    EstadoBusqueda nuevoEstado = new EstadoBusqueda(
                        vecino,
                        actual.palabraActual + getLetra(vecino),
                        actual.celdasUsadas // El constructor de EstadoBusqueda se encarga de copiar y añadir
                    );
                    cola.encolar(nuevoEstado);
                }
            }
        }
        return null; // No se encontro la palabra en ningun punto
    }


    /**
     * Esto es para buscar la palabraa en el tablero (DFS)
     * @param palabra La palabra a buscar (en mayúsculas).
     * @return Una lista de Celdas que forman la palabra si se encuentra, o null si no.
     */
    public List<Celda> buscarPalabraDFS(String palabra) {
        if (palabra == null || palabra.length() < 3) {
            return null;
        }
        String palabraUpper = palabra.toUpperCase();

        // Intentar encontrar la palabra desde cada celda del tablero como punto de inicio
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                Celda inicio = new Celda(r, c);
                // Si la primera letra coincide, iniciar DFS desde aquí
                if (getLetra(inicio) == palabraUpper.charAt(0)) {
                    List<Celda> resultado = dfsDesdeCelda(palabraUpper, inicio);
                    if (resultado != null) {
                        return resultado; // Palabra encontrada
                    }
                }
            }
        }
        return null; // Palabra no encontrada en ningún punto de inicio
    }

    private List<Celda> dfsDesdeCelda(String palabraBuscada, Celda inicio) {
        Pila<EstadoBusqueda> pila = new Pila<>();
        Set<Celda> celdasInicialesUsadas = new HashSet<>(); // Conjunto para la ruta actual

        List<Celda> caminoInicial = new ArrayList<>();
        // caminoInicial.add(inicio); // Esto se agregaría dentro del constructor o antes de crear el estado ---(posibilidad)---

        EstadoBusqueda estadoInicial = new EstadoBusqueda(
            inicio,
            String.valueOf(getLetra(inicio)),
            celdasInicialesUsadas // Aquí el constructor se encarga de copiar y añadir 'inicio'
        );
        pila.apilar(estadoInicial);


        while (!pila.isEmpty()) {
            EstadoBusqueda actual = pila.desapilar();

            // Si la palabra actual coincide con la palabra buscada
            if (actual.palabraActual.equals(palabraBuscada)) {
                return new ArrayList<>(actual.celdasUsadas); // Retorna las celdas usadas, que representan la palabra según sea el caso
            }

            // Si la palabra actual es más larga que la buscada, o que no coincida
            if (actual.palabraActual.length() >= palabraBuscada.length() ||
                !palabraBuscada.startsWith(actual.palabraActual)) {
                continue;
            }

            // Explora las letras vecinas de las posibles palabras
            List<Celda> vecinos = obtenerVecinos(actual.celdaActual);
            
            for (int i = vecinos.size() - 1; i >= 0; i--) { 
                Celda vecino = vecinos.get(i);
             
                if (!actual.celdasUsadas.contains(vecino) &&
                    actual.palabraActual.length() < palabraBuscada.length() &&
                    getLetra(vecino) == palabraBuscada.charAt(actual.palabraActual.length())) {

                    EstadoBusqueda nuevoEstado = new EstadoBusqueda(
                        vecino,
                        actual.palabraActual + getLetra(vecino),
                        actual.celdasUsadas
                    );
                    pila.apilar(nuevoEstado);
                }
            }
        }
        return null; // Si no se encuentra la palabra desde este inicio
    }


    /**
     * Esto es para cargar el tablero
     * @param tableroStr La cadena de texto del tablero (ej. "A,B,C,D\nE,F,G,H...")
     * @throws IllegalArgumentException Si el formato del tablero es incorrecto.
     */
    public void cargarTableroDesdeString(String tableroStr) {
        String[] filasArray = tableroStr.split(System.lineSeparator());
        if (filasArray.length != 4) { // 4x4
            throw new IllegalArgumentException("El tablero debe tener 4 filas.");
        }
        char[][] nuevoTablero = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String[] letras = filasArray[i].split(",");
            if (letras.length != 4) { // 4x4
                throw new IllegalArgumentException("Cada fila del tablero debe tener 4 letras separadas por comas.");
            }
            for (int j = 0; j < 4; j++) {
                if (letras[j].trim().length() != 1) {
                    throw new IllegalArgumentException("Cada elemento del tablero debe ser una sola letra.");
                }
                nuevoTablero[i][j] = letras[j].trim().toUpperCase().charAt(0); // Vuelvo asegurar las mayúsculas
            }
        }
        this.tablero = nuevoTablero;
        this.filas = 4;
        this.columnas = 4;
    }

    /**
     * Y aqui se debería mostrar el tablero
     */
    public void mostrarTablero() {
        System.out.println("--- Tablero ---");
        if (tablero == null || filas == 0) {
            System.out.println("El tablero está vacío.");
            return;
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    static class EstadoBusqueda {

        public EstadoBusqueda() {
        }
    }

    static class EstadoBusqueda {

        public EstadoBusqueda() {
        }
    }
}

