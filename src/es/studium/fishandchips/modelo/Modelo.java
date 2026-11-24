package es.studium.fishandchips.modelo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Modelo {
    private PezJugador pezJugador;

    private static final int POS_X_INICIAL_J = 247;
    private static final int POS_Y_INICIAL_J = 235;
    private static final int TAMANO_INICIAL_J = 5;

    private static final int POS_X_INICIAL_E = 400;
    private static final int POS_Y_INICIAL_E = 500;
    private static final int TAMANO_INICIAL_E = 3;

    private static final int TAMANO_MIN = 2;
    private static final int TAMANO_MAX = 8;
    private static final int VELOCIDAD_BASE = 4;
    private static final int ANCHO_PEZ = 80;

    private final Random random = new Random();

    private List<PezEnemigo> enemigos = new CopyOnWriteArrayList<>();

    public Modelo(){
        pezJugador = new PezJugador(POS_X_INICIAL_J, POS_Y_INICIAL_J, TAMANO_INICIAL_J,
                "/pez_jugador_01.png");

        enemigos.add(new PezEnemigo(POS_X_INICIAL_E, POS_Y_INICIAL_E, TAMANO_INICIAL_E,
                "/pez_enemigo_01.png",2,3));
        enemigos.add(new PezEnemigo(100, 80,  3, "/pez_enemigo_01.png",  1, 4));   // entra desde la izquierda, lento
        enemigos.add(new PezEnemigo(400, 180, 3, "/pez_enemigo_01.png", -1, 6));   // va hacia la izquierda, medio
        enemigos.add(new PezEnemigo(50,  300, 3, "/pez_enemigo_01.png",  1, 9));   // hacia la derecha, rápido
        enemigos.add(new PezEnemigo(500, 420, 3, "/pez_enemigo_01.png", -1, 3));
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
            posX = -ANCHO_PEZ;
            direccion = 1;
        } else {
            posX = anchoPanel;
            direccion = -1;
        }
        int posY = random.nextInt(altoPanel - ANCHO_PEZ);
        int size = random.nextInt(TAMANO_MAX - TAMANO_MIN + 1) + TAMANO_MIN;

        return new PezEnemigo(posX, posY, size, "/pez_enemigo_01.png", direccion, VELOCIDAD_BASE);
    }
}
