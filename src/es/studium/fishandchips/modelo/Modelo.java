package es.studium.fishandchips.modelo;

import es.studium.fishandchips.Opciones;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Modelo {
    private PezJugador pezJugador;

    private final Random random = new Random();

    private List<PezEnemigo> enemigos = new CopyOnWriteArrayList<>();

    public Modelo(){
        pezJugador = new PezJugador(Opciones.JUGADOR_POS_X_INICIAL, Opciones.JUGADOR_POS_Y_INICIAL,
                Opciones.JUGADOR_TAMANO_INICIAL,
                Opciones.SPRITE_JUGADOR);
        }

    public PezJugador getPezJugador(){
        return this.pezJugador;
    }

    public List<PezEnemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(List<PezEnemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public PezEnemigo crearEnemigoAleatorio(int anchoPanel, int altoPanel){
        boolean entraPorIzquierda = random.nextBoolean();

        int posX;
        int direccion;

        if (entraPorIzquierda) {
            posX = -Opciones.ENEMIGO_ANCHO_APROX;
            direccion = Opciones.ENEMIGO_DIRECCION_DERECHA;
        } else {
            posX = anchoPanel;
            direccion = Opciones.ENEMIGO_DIRECCION_IZQUIERDA;
        }
        int posY = random.nextInt(altoPanel - Opciones.ENEMIGO_ANCHO_APROX);
        int size =
                random.nextInt(Opciones.ENEMIGO_TAMANO_MAX - Opciones.ENEMIGO_TAMANO_MIN + 1) + Opciones.ENEMIGO_TAMANO_MIN;
        int velocidad = (int) Math.max(1, Math.round(size * Opciones.VELOCIDAD_GENERAL));

        return new PezEnemigo(posX, posY, size, Opciones.SPRITE_ENEMIGO, direccion, velocidad);    }
}
