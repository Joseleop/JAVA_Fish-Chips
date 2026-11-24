package es.studium.fishandchips.vista;

import javax.swing.*;

public class Vista extends JFrame{
    PezPanel pezPanel = new PezPanel();
    public Vista(){
        setTitle("Fish & Chips");
        setSize(1280,720);
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
