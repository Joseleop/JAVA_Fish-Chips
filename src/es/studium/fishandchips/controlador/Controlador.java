package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;
import es.studium.fishandchips.vista.Vista;

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

    private final int anchoImagen;
    private final int altoImagen;

    volatile boolean dirArriba = false;
    volatile boolean dirAbajo = false;
    volatile boolean dirIzquierda = false;
    volatile boolean dirDerecha = false;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.pezJugador = modelo.getPezJugador();

        this.panel = vista.getPezPanel();
        this.anchoImagen = panel.getAnchoImagenJugador();
        this.altoImagen = panel.getAltoImagenJugador();

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
        Thread hiloEnemigo = new Thread(() -> {
            boolean enPantalla = true;
            while (enPantalla) {
                enemigo.setPosX(enemigo.getPosX() + enemigo.getDireccion() * enemigo.getVelocidad());
                enPantalla = !haSalido(enemigo);
                panel.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    System.err.println("ERROR - Operación interrumpida " + e.getMessage());
                }
            }
            modelo.getEnemigos().remove(enemigo);
            panel.repaint();
        });
        hiloEnemigo.start();
    }

    private boolean haSalido(PezEnemigo enemigo) {
        if (enemigo.getDireccion() > 0) {
            return enemigo.getPosX() > panel.getWidth();
        } else {
            return enemigo.getPosX() + panel.getAnchoImagenEnemigo() < 0;
        }
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
        } else if ((pezJugador.getPosX() + anchoImagen) > anchoVentana) {
            pezJugador.setPosX(anchoVentana - anchoImagen);
        }
        if (pezJugador.getPosY() < 0) {
            pezJugador.setPosY(0);
        } else if ((pezJugador.getPosY() + altoImagen) > altoVentana) {
            pezJugador.setPosY(altoVentana - altoImagen);
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
