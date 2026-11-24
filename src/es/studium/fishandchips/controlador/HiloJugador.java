package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Comida;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;
import es.studium.fishandchips.vista.PezPanel;

import javax.swing.*;
import java.awt.*;

public class HiloJugador implements Runnable{

    private final Modelo modelo;
    private final PezJugador pezJugador;
    private final PezPanel panel;
    private final Runnable alTerminar;

    public static volatile boolean dirArriba = false;
    public static volatile boolean dirAbajo = false;
    public static volatile boolean dirIzquierda = false;
    public static volatile boolean dirDerecha = false;

    public HiloJugador(Modelo modelo, PezJugador pezJugador,PezPanel panel,
                       Runnable alTerminar){
        this.modelo = modelo;
        this.pezJugador = pezJugador;
        this.panel = panel;
        this.alTerminar = alTerminar;
    }
    @Override
    public void run() {
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
        alTerminar.run();

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

    private void comprobarColisiones(){
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
        for (Comida comida : modelo.getComidas()) {
            Rectangle rComida = new Rectangle(
                    comida.getPosX(), comida.getPosY(), comida.getLado(), comida.getLado());
            if (rJugador.intersects(rComida)) {
                modelo.getComidas().remove(comida);
                pezJugador.setSize(pezJugador.getSize() + comida.getSize());
                Sonido.reproducir(Opciones.SONIDO_COMER);
                if (pezJugador.getSize() >= Opciones.TAMANO_VICTORIA) {
                    modelo.terminarPartida(true);
                    return;
                }
            }
        }
    }
}
