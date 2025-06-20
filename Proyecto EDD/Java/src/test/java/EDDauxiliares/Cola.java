/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDDauxiliares;

/** Clase
 * Implementoto la clase cola para el recorrido de (BFS)
 * @author Kelvin Hurtado
 * @version: 18/06/25
 * 
 */

class Cola<T> {
    private Nodo<T> pInicio;
    private Nodo<T> pFinal;
    private int tamano;

    void encolar(Grafo.EstadoBusqueda estadoInicial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static class Nodo<T> {
       T dato;
       Nodo<T> pNext;
       
       Nodo(T dato) {
           this.dato= dato;
           this.pNext= null;
       }
    }

    public Cola() {
        this.pInicio = null;
        this.pFinal = null;
        this.tamano = 0;   
    }
     
// Aqui agregue un elemento al final de la cola
   
    public void Encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo <>(elemento);
        if (pFinal != null) {
            pFinal.pNext = nuevoNodo;
        } 
        pFinal = nuevoNodo;
        if (pInicio == null) {
            pInicio = pFinal;
        }
        tamano++;
    }
    
// Esto es para eliminar y retornar el elemento del inicio de la cola
    
    public T desencolar() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");   // Excepcion si la cola está vacía
        }
        T dato = pInicio.dato;
        pInicio = pInicio.pNext;
        
        if (pInicio == null) { // Si la cola está vacía despues de desencolar
            pFinal = null;
        } 
        tamano--;
        return dato;
    }
     
// Para retornar el elemento del inicio de la cola sin eliminarlo
    
    public T pInicio() {
        if(isEmpty()) {
            throw new  IllegalStateException("La cola está vacía");   
        }
        return pInicio.dato;
    }
    
// Esto verifica si la cola está vacía
    
    public boolean isEmpty() {
        return pInicio == null;

// Retorna el número de elementos en la cola
    }
    public int tamano() {
        return tamano;
    }
    
// Limpia todos los elementos de la cola
    
    public void limpiar() {
        pInicio = null;
        pFinal = null;
        tamano = 0;         
    }
}
    

