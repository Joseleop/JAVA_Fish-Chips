package es.studium.fishandchips.controlador;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.vista.PezPanel;

public class HiloGenerador implements Runnable {

    private final Modelo modelo;
    private final PezPanel panel;

    public HiloGenerador(Modelo modelo, PezPanel panel) {
        this.modelo = modelo;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (modelo.isEnPartida()) {
            if (modelo.getEnemigos().size() < Opciones.GENERADOR_MAX_ENEMIGOS) {
                PezEnemigo enemigo = modelo.crearEnemigoAleatorio(panel.getWidth(), panel.getHeight());
                modelo.getEnemigos().add(enemigo);
                new Thread(new HiloEnemigo(enemigo, modelo, panel)).start();
            }
            if (modelo.getComidas().size() < Opciones.GENERADOR_MAX_COMIDA) {
                modelo.getComidas().add(
                        modelo.crearComidaAleatoria(panel.getWidth(), panel.getHeight()));
            }
            try {
                Thread.sleep(Opciones.GENERADOR_INTERVALO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
