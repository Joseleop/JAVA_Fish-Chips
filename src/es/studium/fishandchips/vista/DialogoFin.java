package es.studium.fishandchips.vista;

import javax.swing.*;
import java.awt.Component;

public class DialogoFin {

    private static final Object[] OPCIONES = {"Jugar otra vez", "Salir"};

    public boolean preguntarRepetir(Component padre, String titulo, String mensaje) {
        int eleccion = JOptionPane.showOptionDialog(padre, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, OPCIONES, OPCIONES[0]);
        return eleccion == 0;
    }
}