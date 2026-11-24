package es.studium.fishandchips.modelo;

import es.studium.fishandchips.Opciones;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Modelo {
    private final Random random = new Random();
    private int pecesComidos = 0;
    private volatile boolean enPartida = true;
    private volatile boolean victoria = false;
    private PezJugador pezJugador;
    private List<PezEnemigo> enemigos = new CopyOnWriteArrayList<>();

    public Modelo() {
        pezJugador = new PezJugador(Opciones.JUGADOR_POS_X_INICIAL, Opciones.JUGADOR_POS_Y_INICIAL,
                Opciones.JUGADOR_TAMANO_INICIAL,
                Opciones.SPRITE_JUGADOR);
    }

    public PezJugador getPezJugador() {
        return this.pezJugador;
    }

    public List<PezEnemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(List<PezEnemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public PezEnemigo crearEnemigoAleatorio(int anchoPanel, int altoPanel) {
        int size = random.nextInt(Opciones.ENEMIGO_TAMANO_MAX - Opciones.ENEMIGO_TAMANO_MIN + 1)
                + Opciones.ENEMIGO_TAMANO_MIN;
        int lado = size * Opciones.ESCALA_PIXELES;

        int posX;
        int direccion;
        if (random.nextBoolean()) {
            posX = -lado;
            direccion = Opciones.ENEMIGO_DIRECCION_DERECHA;
        } else {
            posX = anchoPanel;
            direccion = Opciones.ENEMIGO_DIRECCION_IZQUIERDA;
        }

        int posY = random.nextInt(Math.max(1, altoPanel - lado));
        int velocidad = (int) Math.max(1, Math.round(size * Opciones.VELOCIDAD_GENERAL));
        String sprite = Opciones.SPRITES_ENEMIGOS[random.nextInt(Opciones.SPRITES_ENEMIGOS.length)];

        return new PezEnemigo(posX, posY, size, sprite, direccion, velocidad);
    }

    public synchronized int getPecesComidos() {
        return pecesComidos;
    }

    public synchronized void sumarPezComido() {
        pecesComidos++;
    }

    public boolean isEnPartida() {
        return enPartida;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public void terminarPartida(boolean victoria) {
        this.victoria = victoria;
        this.enPartida = false;
    }

    public synchronized void reiniciar() {
        enemigos.clear();
        pezJugador.setPosX(Opciones.JUGADOR_POS_X_INICIAL);
        pezJugador.setPosY(Opciones.JUGADOR_POS_Y_INICIAL);
        pezJugador.setSize(Opciones.JUGADOR_TAMANO_INICIAL);
        pecesComidos = 0;
        victoria = false;
        enPartida = true;
    }
}
