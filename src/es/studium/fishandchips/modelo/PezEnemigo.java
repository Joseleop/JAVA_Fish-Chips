package es.studium.fishandchips.modelo;

public class PezEnemigo extends Entidad {
    private int direccion;
    private int velocidad;

    public PezEnemigo(int posX, int posY, int size, String sprite, int direccion,
                      int velocidad) {
        super(posX, posY, size, sprite);
        this.direccion = direccion;
        this.velocidad = velocidad;
    }

    @Override
    public void setSprite(String sprite) {
        super.setSprite(sprite);
    }

    public void setDireccion(int direccion) {
        if (direccion != 0) {
            if (direccion > 0) {
                System.out.println("dirección positiva - hacia derecha");
                this.direccion = direccion;
            } else {
                System.out.println("dirección negativa - hacia izquierda");
                this.direccion = direccion;
            }
        }
    }

    public int getDireccion() {
        return this.direccion;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVelocidad() {
        return this.velocidad;
    }
}
