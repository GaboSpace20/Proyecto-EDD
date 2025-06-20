/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDDauxiliares;

/** Implemento de la clase Pila para el recorrido (DFS)
 *
 * @author Kelvin Hurtado
 */

class Pila<T> {

    void apilar(Grafo.EstadoBusqueda estadoInicial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Grafo.EstadoBusqueda desapilar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static class Nodo<T> {
        T dato;
        Nodo<T> pNext;

        Nodo(T dato) {
            this.dato = dato;
            this.pNext = null;
        }
        
    private Nodo<T> cima; // Representa el elemento superior de la pila
    private int tamano; // Para llevar un conteo del número de elementos
    
        
    public void Pila() {
        this.cima = null;
        this.tamano = 0;
        }
    
    public void push(T elemento) {    // Para agregar un elemento a la cima de la pila
        Nodo<T> nuevoNodo = new Nodo <> (elemento);
        nuevoNodo.pNext = cima;
        cima = nuevoNodo;
        tamano++;       
    }
    
    public T pop() {    // Para eliminar y retornar el elemento de la cima de la pila
        if (isEmpty()) {
            return  null;
        }
        T dato = cima.dato;
        cima = cima.pNext;
        return dato;
    }
    
    public T cima() {   // Se retorna el elemento de la cima de la pila sin eliminarlo
        if (isEmpty()) {
            return null;
        }
        return cima.dato;
    }
    
    public boolean isEmpty() {
        return cima == null;
    }
    
    // Retorna el número de elementos en la pila
    
    public int tamano() {
        return tamano;
    }
    
    // Para limpiar todos los elementos de la pila
    
    public void limpiar() {
        cima = null;
        tamano = 0;
    }
    }
}
    

