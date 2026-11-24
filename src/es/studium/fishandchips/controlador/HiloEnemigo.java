package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.vista.PezPanel;

public class HiloEnemigo implements Runnable {

    private final PezEnemigo enemigo;
    private final Modelo modelo;
    private final PezPanel panel;

    public HiloEnemigo(PezEnemigo enemigo, Modelo modelo, PezPanel panel) {
        this.enemigo = enemigo;
        this.modelo = modelo;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (modelo.isEnPartida() && !haSalido()) {
            enemigo.setPosX(enemigo.getPosX() + enemigo.getDireccion() * enemigo.getVelocidad());
            panel.repaint();
            try {
                Thread.sleep(Opciones.PAUSA_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        modelo.getEnemigos().remove(enemigo);
        panel.repaint();
    }

    private boolean haSalido() {
        if (enemigo.getDireccion() > 0) {
            return enemigo.getPosX() > panel.getWidth();
        } else {
            return enemigo.getPosX() + enemigo.getLado() < 0;
        }
    }
}