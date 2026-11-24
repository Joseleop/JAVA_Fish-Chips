package es.studium.fishandchips.modelo;

import es.studium.fishandchips.Opciones;

abstract public class Entidad {
    private volatile int posX;
    private volatile int posY;
    private int size;
    private String sprite;

    public Entidad(int posX, int posY, int size, String sprite){
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.sprite = sprite;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }

    public int getLado() {
        return size * Opciones.ESCALA_PIXELES;
    }
}

