package es.studium.fishandchips.modelo;

import es.studium.fishandchips.Opciones;

public class Comida extends Entidad {

    public Comida(int posX, int posY, String sprite) {
        super(posX, posY, 1, sprite);   // siempre vale 1
    }

    @Override
    public int getLado() {
        return Opciones.COMIDA_LADO;   // la comida no escala con el size
    }
}