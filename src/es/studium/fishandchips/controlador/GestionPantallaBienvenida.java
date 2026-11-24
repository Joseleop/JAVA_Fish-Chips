package es.studium.fishandchips.controlador;

import es.studium.fishandchips.vista.Vista;

public class GestionPantallaBienvenida {

    public GestionPantallaBienvenida(Vista vista, Controlador controlador) {
        vista.getPantallaBienvenida().getBotonJugar().addActionListener(e -> {
            vista.mostrarJuego();
            vista.getPezPanel().requestFocusInWindow();
            controlador.iniciarPartida();
        });
    }
}