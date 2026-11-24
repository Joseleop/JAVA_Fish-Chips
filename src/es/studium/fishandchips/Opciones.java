package es.studium.fishandchips;

import java.awt.event.KeyEvent;

public final class Opciones {

    private Opciones() {}

    // Ventana
    public static final int VENTANA_ANCHO = 1280;
    public static final int VENTANA_ALTO = 720;

    // Juego
    public static final int PAUSA_MS = 16;              // ~60 fotogramas por segundo
    public static final int TAMANO_VICTORIA = 50;

    // Jugador
    public static final int JUGADOR_POS_X_INICIAL = 247;
    public static final int JUGADOR_POS_Y_INICIAL = 235;
    public static final int JUGADOR_TAMANO_INICIAL = 5;
    public static final int JUGADOR_MOVIMIENTO = 10;

    // Enemigos
    public static final int ENEMIGO_TAMANO_MIN = 2;
    public static final int ENEMIGO_TAMANO_MAX = 8;
    public static final int ENEMIGO_ANCHO_APROX = 80;   // para colocarlo fuera al nacer
    public static final int ENEMIGO_VELOCIDAD_BASE = 1;
    public static final double VELOCIDAD_GENERAL = 1.5;// si usas la fórmula base +


    public static final int ENEMIGO_DIRECCION_DERECHA = 1;
    public static final int ENEMIGO_DIRECCION_IZQUIERDA = -1;


    // Teclas
    public static final int TECLA_ARRIBA = KeyEvent.VK_W;
    public static final int TECLA_ABAJO = KeyEvent.VK_S;
    public static final int TECLA_IZQUIERDA = KeyEvent.VK_A;
    public static final int TECLA_DERECHA = KeyEvent.VK_D;

    // Sprites
    public static final String SPRITE_JUGADOR = "/pez_jugador_01.png";
    public static final String SPRITE_ENEMIGO = "/pez_enemigo_01.png";
}
