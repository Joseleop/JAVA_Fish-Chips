package es.studium.fishandchips;

import es.studium.fishandchips.controlador.Controlador;
import es.studium.fishandchips.modelo.Modelo;
import es.studium.fishandchips.vista.Vista;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista vista = new Vista();
            Modelo modelo = new Modelo();

            new Controlador(vista, modelo);
        });
    }
}
