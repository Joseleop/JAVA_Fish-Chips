package es.studium.fishandchips.controlador;

import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;
import es.studium.fishandchips.vista.Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener {

    private final Vista vista;
    private final Modelo modelo;

    final int TECLA_D = 68;
    final int TECLA_W = 87;
    final int TECLA_A = 65;
    final int TECLA_S = 83;

    volatile boolean dirArriba = false;
    volatile boolean dirAbajo = false;
    volatile boolean dirIzquierda = false;
    volatile boolean dirDerecha = false;

    private static final int MOVIMIENTO = 10;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        PezJugador pezJugador = modelo.getPezJugador();
        PezPanel panel = vista.getPezPanel();


        final int anchoImagen = panel.getAnchoImagen();
        final int altoImagen = panel.getAltoImagen();

        panel.setPezJugador(modelo.getPezJugador());
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.repaint();

        Thread hiloMovimientoJugador = new Thread(()->{
            while (true) {
                final int anchoVentana = panel.getWidth();
                final int altoVentana = panel.getHeight();
                if (dirDerecha) {
                    pezJugador.setPosX(pezJugador.getPosX() + MOVIMIENTO);
                }
                if (dirIzquierda) {
                    pezJugador.setPosX(pezJugador.getPosX() - MOVIMIENTO);
                }
                if (dirArriba) {
                    pezJugador.setPosY(pezJugador.getPosY() - MOVIMIENTO);
                }
                if (dirAbajo) {
                    pezJugador.setPosY(pezJugador.getPosY() + MOVIMIENTO);
                }

                if (pezJugador.getPosX() < 0) {
                    pezJugador.setPosX(0);
                } else if ((pezJugador.getPosX() + anchoImagen) > anchoVentana) {
                    pezJugador.setPosX(anchoVentana - anchoImagen);
                }
                if (pezJugador.getPosY() < 0) {
                    pezJugador.setPosY(0);
                } else if ((pezJugador.getPosY() + altoImagen) > altoVentana) {
                    pezJugador.setPosY(altoVentana - altoImagen);
                }

                panel.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    System.err.println("ERROR - Operación interrumpida " + e.getMessage());
                }
            }
        });
        hiloMovimientoJugador.start();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
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
