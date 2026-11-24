package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;
import es.studium.fishandchips.vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener {

    final int TECLA_D = 68;
    final int TECLA_W = 87;
    final int TECLA_A = 65;
    final int TECLA_S = 83;

    private final Modelo modelo;

    private final PezJugador pezJugador;
    private final PezPanel panel;

    volatile boolean dirArriba = false;
    volatile boolean dirAbajo = false;
    volatile boolean dirIzquierda = false;
    volatile boolean dirDerecha = false;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;

        this.pezJugador = modelo.getPezJugador();

        this.panel = vista.getPezPanel();

        panel.setPezJugador(pezJugador);
        panel.setEnemigos(modelo.getEnemigos());

        panel.addKeyListener(this);
        panel.setFocusable(true);

        Sonido.precargar(Opciones.SONIDO_COMER, Opciones.SONIDO_COMIDO);

        new GestionPantallaBienvenida(vista, this);
    }

    public void iniciarPartida() {
        Thread hiloMovimientoJugador = new Thread(() -> {
            while (modelo.isEnPartida()) {
                moverJugador();
                comprobarLimitesVentana();
                comprobarColisiones();
                panel.repaint();
                try {
                    Thread.sleep(Opciones.PAUSA_MS);
                } catch (InterruptedException e) {
                    System.err.println("ERROR - Operación interrumpida " + e.getMessage());
                    break;
                }
            }

            SwingUtilities.invokeLater(() -> {
                String mensaje;
                if (modelo.isVictoria()) {
                    mensaje = "¡Has ganado!\nPeces comidos: " + modelo.getPecesComidos();
                } else {
                    mensaje = "Te han comido\nTamaño alcanzado: " + pezJugador.getSize()
                            + "\nPeces comidos: " + modelo.getPecesComidos();
                }
                Sonido.pararMusica();
                JOptionPane.showMessageDialog(panel, mensaje, "Fin de la partida", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            });
        });
        hiloMovimientoJugador.start();

        new Thread(new HiloGenerador(modelo, panel)).start();
        Sonido.iniciarMusica(Opciones.MUSICA_FONDO);
        panel.requestFocusInWindow();
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
                    modelo.sumarPezComido();
                    Sonido.reproducir(Opciones.SONIDO_COMER);
                    if (pezJugador.getSize() >= Opciones.TAMANO_VICTORIA) {
                        modelo.terminarPartida(true);
                        return;
                    }
                } else {
                    Sonido.reproducir(Opciones.SONIDO_COMIDO);
                    modelo.terminarPartida(false);
                    return;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> dirArriba = true;
            case TECLA_S -> dirAbajo = true;
            case TECLA_A -> dirIzquierda = true;
            case TECLA_D -> dirDerecha = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> dirArriba = false;
            case TECLA_S -> dirAbajo = false;
            case TECLA_A -> dirIzquierda = false;
            case TECLA_D -> dirDerecha = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}