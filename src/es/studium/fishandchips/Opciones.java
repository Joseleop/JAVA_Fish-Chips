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
    public static final int GENERADOR_INTERVALO_MS = 1500;
    public static final int GENERADOR_MAX_ENEMIGOS = 12;



    public static final int ENEMIGO_DIRECCION_DERECHA = 1;
    public static final int ENEMIGO_DIRECCION_IZQUIERDA = -1;


    // Teclas
    public static final int TECLA_ARRIBA = KeyEvent.VK_W;
    public static final int TECLA_ABAJO = KeyEvent.VK_S;
    public static final int TECLA_IZQUIERDA = KeyEvent.VK_A;
    public static final int TECLA_DERECHA = KeyEvent.VK_D;

    // Sprites
    public static final String SPRITE_JUGADOR = "/pez_jugador.png";
    public static final String[] SPRITES_ENEMIGOS = {
            "/pez_enemigo_01.png", "/pez_enemigo_02.png", "/pez_enemigo_03.png"
    };
    public static final String SPRITE_FONDO = "/bg_img.png";

    // Píxeles por cada punto de tamaño (size 5 = 40 px, size 50 = 400 px)
    public static final int ESCALA_PIXELES = 8;

    // Sonidos
    public static final String SONIDO_COMER = "/sonido_comer.wav";
    public static final String SONIDO_COMIDO = "/sonido_comido.wav";
    public static final String MUSICA_FONDO = "/bg_musica.wav";
}
