/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDDauxiliares;

/** Implementacion de la clase celda para agrupar filas y columnas
 *
 * @author Kelvin 
 * 
 */
public class Celda {
    
    public int fila;
    public int columna;

    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    // Opcional: Para facilitar la depuración y comparación en Set
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return fila == celda.fila && columna == celda.columna;
    }

    @Override
    public int hashCode() {
        // Una forma simple de generar un hash único para cada celda
        return 31 * fila + columna;
    }

    @Override
    public String toString() {
        return "(" + fila + "," + columna + ")";
    }
}
    
