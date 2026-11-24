package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private final PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();
    private final PezPanel pezPanel = new PezPanel();
    private final CardLayout cartas = new CardLayout();
    private final JPanel contenedor = new JPanel(cartas);

    public Vista() {
        setTitle("Fish & Chips");
        setSize(Opciones.VENTANA_ANCHO, Opciones.VENTANA_ALTO);
        setResizable(false);
        setLocationRelativeTo(null);

        contenedor.add(pantallaBienvenida, "bienvenida");
        contenedor.add(pezPanel, "juego");
        add(contenedor);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void mostrarJuego() {
        cartas.show(contenedor, "juego");
    }

    public PantallaBienvenida getPantallaBienvenida() {
        return pantallaBienvenida;
    }

    public PezPanel getPezPanel() {
        return pezPanel;
    }
}