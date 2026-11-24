package es.studium.fishandchips.controlador;

import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;
import es.studium.fishandchips.vista.Vista;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener {

    private final Vista vista;
    private final Modelo modelo;

    private static final int MOVIMIENTO = 10;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        PezPanel panel = vista.getPezPanel();
        panel.setPezJugador(modelo.getPezJugador());
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int TECLA_D = 68;
        final int TECLA_W = 87;
        final int TECLA_A = 65;
        final int TECLA_S = 83;

        PezJugador pezJugador = modelo.getPezJugador();
        PezPanel panelPez = vista.getPezPanel();

        final int anchoVentana = panelPez.getWidth();
        final int altoVentana = panelPez.getHeight();

        final int anchoImagen = panelPez.getAnchoImagen();
        final int altoImagen = panelPez.getAltoImagen();

        switch (e.getKeyCode()){
            case TECLA_W -> {
                pezJugador.setPosY(pezJugador.getPosY() - MOVIMIENTO);
            }
            case TECLA_S -> {
                pezJugador.setPosY(pezJugador.getPosY() + MOVIMIENTO);
            }
            case TECLA_A -> {
                pezJugador.setPosX(pezJugador.getPosX() - MOVIMIENTO);
            }
            case TECLA_D -> {
                pezJugador.setPosX(pezJugador.getPosX() + MOVIMIENTO);

            }
        }

        if(pezJugador.getPosX() < 0) {
            pezJugador.setPosX(0);
        } else if ((pezJugador.getPosX() + anchoImagen) > anchoVentana ) {
            pezJugador.setPosX(anchoVentana - anchoImagen);
        }
        if (pezJugador.getPosY() < 0) {
            pezJugador.setPosY(0);
        } else if ((pezJugador.getPosY() + altoImagen) > altoVentana ) {
            pezJugador.setPosY(altoVentana - altoImagen);
        }


        panelPez.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
