package es.studium.fishandchips.modelo;

public class PezJugador extends Entidad {
    public PezJugador(int posX, int posY, int size, String sprite) {
        super(posX, posY, size, sprite);
    }

    @Override
    public void setSprite(String sprite) {
        super.setSprite(sprite);
    }
}
