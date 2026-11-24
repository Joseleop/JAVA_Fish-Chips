package es.studium.fishandchips;

import java.awt.event.KeyEvent;

public final class Opciones {

    private Opciones() {}

    // ===== VENTANA =====
    public static final int VENTANA_ANCHO = 1280;
    public static final int VENTANA_ALTO = 720;

    // ===== JUEGO =====
    public static final int PAUSA_MS = 16;               // ~60 fotogramas por segundo
    public static final int TAMANO_VICTORIA = 50;

    // ===== ESCALA VISUAL =====
    public static final double ESCALA_GENERAL = 1.5;     // multiplica el tamaño de TODOS los sprites
    public static final int LADO_MIN = 24;               // lado en px con size 0
    public static final int LADO_MAX = 120;              // lado en px con size de victoria

    // ===== JUGADOR =====
    public static final int JUGADOR_POS_X_INICIAL = 600;
    public static final int JUGADOR_POS_Y_INICIAL = 300;
    public static final int JUGADOR_TAMANO_INICIAL = 5;
    public static final int JUGADOR_MOVIMIENTO = 10;     // px por fotograma

    // ===== ENEMIGOS =====
    public static final int ENEMIGO_TAMANO_MIN = 2;      // tamaño mínimo absoluto
    public static final int ENEMIGO_TAMANO_MAX = 8;      // tamaño máximo mínimo (al inicio)
    public static final double ENEMIGO_RANGO_MIN = 0.5;  // tamaño mínimo respecto al jugador
    public static final double ENEMIGO_RANGO_MAX = 1.25; // tamaño máximo respecto al jugador
    public static final int ENEMIGO_VELOCIDAD_MIN = 2;   // velocidad con size 0
    public static final int ENEMIGO_VELOCIDAD_MAX = 8;   // velocidad con size de victoria
    public static final double VELOCIDAD_GENERAL = 1.5;  // multiplicador de la velocidad de todos
    public static final int ENEMIGO_DIRECCION_DERECHA = 1;
    public static final int ENEMIGO_DIRECCION_IZQUIERDA = -1;

    // ===== GENERADOR =====
    public static final int GENERADOR_INTERVALO_MS = 1500;
    public static final int GENERADOR_MAX_ENEMIGOS = 12;
    public static final int GENERADOR_MAX_COMIDA = 8;

    // ===== COMIDA =====
    public static final int COMIDA_LADO = 32;            // px fijos (antes de ESCALA_GENERAL)

    // ===== TECLAS =====
    public static final int TECLA_ARRIBA = KeyEvent.VK_W;
    public static final int TECLA_ABAJO = KeyEvent.VK_S;
    public static final int TECLA_IZQUIERDA = KeyEvent.VK_A;
    public static final int TECLA_DERECHA = KeyEvent.VK_D;

    // ===== SPRITES =====
    public static final String SPRITE_JUGADOR = "/pez_jugador.png";
    public static final String[] SPRITES_ENEMIGOS = {
            "/pez_enemigo_01.png", "/pez_enemigo_02.png", "/pez_enemigo_03.png"
    };
    public static final String SPRITE_COMIDA = "/comida_chip.png";
    public static final String SPRITE_FONDO = "/bg_img.png";

    // ===== SONIDOS =====
    public static final String SONIDO_COMER = "/sonido_comer.wav";
    public static final String SONIDO_COMIDO = "/sonido_comido.wav";
    public static final String MUSICA_FONDO = "/bg_musica.wav";

    // El mayor enemigo posible = tamaño del jugador + margen
    public static final int ENEMIGO_MARGEN_MAX = 5;
    // 1.0 = tamaños uniformes; más alto = más peces pequeños y menos grandes
    public static final double ENEMIGO_SESGO = 1.5;
}