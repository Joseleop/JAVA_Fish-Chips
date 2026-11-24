package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;
import es.studium.fishandchips.vista.Vista;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Controlador implements KeyListener {

//    private static final int MOVIMIENTO = 10;
    final int TECLA_D = 68;
    final int TECLA_W = 87;
    final int TECLA_A = 65;
    final int TECLA_S = 83;

    private final Vista vista;
    private final Modelo modelo;

    private final PezJugador pezJugador;
    private final PezPanel panel;

    volatile boolean dirArriba = false;
    volatile boolean dirAbajo = false;
    volatile boolean dirIzquierda = false;
    volatile boolean dirDerecha = false;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.pezJugador = modelo.getPezJugador();

        this.panel = vista.getPezPanel();

        panel.setPezJugador(pezJugador);
        panel.setEnemigos(modelo.getEnemigos());

        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.repaint();

        Thread hiloMovimientoJugador = new Thread(() -> {
            while (true) {
                moverJugador();
                comprobarLimitesVentana();
                comprobarColisiones();
                panel.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    System.err.println("ERROR - Operación interrumpida " + e.getMessage());
                }
            }
        });
        hiloMovimientoJugador.start();

        for (int i = 0; i < 6; i++) {
            PezEnemigo enemigo = modelo.crearEnemigoAleatorio(panel.getWidth(),
                    panel.getHeight());
            modelo.getEnemigos().add(enemigo);
            lanzarHiloEnemigo(enemigo);
            System.out.println("Enemigo " + i + " | tamaño: " + enemigo.getSize() + " | velocidad: " + enemigo.getVelocidad());
        }

    }

    private void lanzarHiloEnemigo(PezEnemigo enemigo) {
        new Thread(new HiloEnemigo(enemigo, modelo, panel)).start();
    }

    private void moverJugador() {
        if (dirDerecha) {
            pezJugador.setPosX(pezJugador.getPosX() + Opciones.JUGADOR_MOVIMIENTO);
        }
        if (dirIzquierda) {
            pezJugador.setPosX(pezJugador.getPosX() - Opciones.JUGADOR_MOVIMIENTO);
        }
        if (dirArriba) {
            pezJugador.setPosY(pezJugador.getPosY() - Opciones.JUGADOR_MOVIMIENTO);
        }
        if (dirAbajo) {
            pezJugador.setPosY(pezJugador.getPosY() + Opciones.JUGADOR_MOVIMIENTO);
        }
    }

    private void comprobarLimitesVentana() {
        final int anchoVentana = panel.getWidth();
        final int altoVentana = panel.getHeight();

        if (pezJugador.getPosX() < 0) {
            pezJugador.setPosX(0);
        } else if ((pezJugador.getPosX() + pezJugador.getLado()) > anchoVentana) {
            pezJugador.setPosX(anchoVentana - pezJugador.getLado());
        }
        if (pezJugador.getPosY() < 0) {
            pezJugador.setPosY(0);
        } else if ((pezJugador.getPosY() + pezJugador.getLado()) > altoVentana) {
            pezJugador.setPosY(altoVentana - pezJugador.getLado());
        }
    }
    private void comprobarColisiones() {
        Rectangle rJugador = new Rectangle(
                pezJugador.getPosX(), pezJugador.getPosY(), pezJugador.getLado(), pezJugador.getLado());

        for (PezEnemigo enemigo : modelo.getEnemigos()) {
            Rectangle rEnemigo = new Rectangle(
                    enemigo.getPosX(), enemigo.getPosY(), enemigo.getLado(), enemigo.getLado());

            if (rJugador.intersects(rEnemigo)) {
                if (enemigo.getSize() < pezJugador.getSize()) {
                    modelo.getEnemigos().remove(enemigo);
                    pezJugador.setSize(pezJugador.getSize() + enemigo.getSize());
                    System.out.println("Comido. Tamaño: " + pezJugador.getSize());
                } else {
                    System.out.println("Te han comido. FIN DE PARTIDA");
                    // aquí  meto el fin de partida
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> {
                dirArriba = true;
            }
            case TECLA_S -> {
                dirAbajo = true;
            }
            case TECLA_A -> {
                dirIzquierda = true;
            }
            case TECLA_D -> {
                dirDerecha = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> {
                dirArriba = false;
            }
            case TECLA_S -> {
                dirAbajo = false;
            }
            case TECLA_A -> {
                dirIzquierda = false;
            }
            case TECLA_D -> {
                dirDerecha = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
