package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;

import javax.swing.*;

public class Vista extends JFrame{
    private PezPanel pezPanel = new PezPanel();
    public Vista(){
        setTitle("Fish & Chips");
        setSize(Opciones.VENTANA_ANCHO,Opciones.VENTANA_ALTO);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pezPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public PezPanel getPezPanel(){
        return this.pezPanel;
    }
}
