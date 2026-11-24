package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
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
    private final GestionFinPartida gestionFin;



    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.pezJugador = modelo.getPezJugador();
        this.panel = vista.getPezPanel();
        this.gestionFin = new GestionFinPartida(modelo, panel, this);

        panel.setPezJugador(pezJugador);
        panel.setEnemigos(modelo.getEnemigos());
        panel.setComidas(modelo.getComidas());

        panel.addKeyListener(this);
        panel.setFocusable(true);

        Sonido.precargar(Opciones.SONIDO_COMER, Opciones.SONIDO_COMIDO);

        new GestionPantallaBienvenida(vista, this);
    }

    public void iniciarPartida() {
        HiloJugador.dirArriba = false;
        HiloJugador.dirAbajo = false;
        HiloJugador.dirIzquierda = false;
        HiloJugador.dirDerecha = false;

        new Thread(new HiloJugador(modelo, pezJugador, panel,
                () -> SwingUtilities.invokeLater(gestionFin::mostrar))).start();
        new Thread(new HiloGenerador(modelo, panel)).start();

        Sonido.iniciarMusica(Opciones.MUSICA_FONDO);
        panel.requestFocusInWindow();
    }
    public void reiniciarPartida() {
        modelo.reiniciar();
        iniciarPartida();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> HiloJugador.dirArriba = true;
            case TECLA_S -> HiloJugador.dirAbajo = true;
            case TECLA_A -> HiloJugador.dirIzquierda = true;
            case TECLA_D -> HiloJugador.dirDerecha = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case TECLA_W -> HiloJugador.dirArriba = false;
            case TECLA_S -> HiloJugador.dirAbajo = false;
            case TECLA_A -> HiloJugador.dirIzquierda = false;
            case TECLA_D -> HiloJugador.dirDerecha = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}