package model;

import java.util.ArrayList;
import java.util.List;

public class LetraNodo {
    public char letra;
    public int fila;
    public int columna;
    public List<LetraNodo> adyacentes;

    public LetraNodo(char letra, int fila, int columna) {
        this.letra = letra;
        this.fila = fila;
        this.columna = columna;
        this.adyacentes = new ArrayList<>();
    }

    public void agregarAdyacente(LetraNodo nodo) {
        adyacentes.add(nodo);
    }

    @Override
    public String toString() {
        return letra + "(" + fila + "," + columna + ")";
    }
}