package es.studium.fishandchips.modelo;

public class Comida extends Entidad{
    public Comida(int posX, int posY, int size, String sprite) {
        super(posX, posY, size, sprite);
    }

    @Override
    public void setSize(int size) {
        super.setSize(1);
    }

    @Override
    public void setSprite(String sprite) {
        super.setSprite(sprite);
    }
}
