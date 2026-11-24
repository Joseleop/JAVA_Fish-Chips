package es.studium.fishandchips.controlador;

import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.vista.DialogoFin;
import es.studium.fishandchips.vista.PezPanel;

public class GestionFinPartida {

    private final Modelo modelo;
    private final PezPanel panel;
    private final Controlador controlador;
    private final DialogoFin dialogo = new DialogoFin();

    public GestionFinPartida(Modelo modelo, PezPanel panel, Controlador controlador) {
        this.modelo = modelo;
        this.panel = panel;
        this.controlador = controlador;
    }

    public void mostrar() {
        Sonido.pararMusica();

        String mensaje;
        if (modelo.isVictoria()) {
            mensaje = "¡Has ganado!\nPeces comidos: " + modelo.getPecesComidos();
        } else {
            mensaje = "Te han comido\nTamaño alcanzado: " + modelo.getPezJugador().getSize()
                    + "\nPeces comidos: " + modelo.getPecesComidos();
        }

        if (dialogo.preguntarRepetir(panel, "Fin de la partida", mensaje)) {
            controlador.reiniciarPartida();
        } else {
            System.exit(0);
        }
    }
}