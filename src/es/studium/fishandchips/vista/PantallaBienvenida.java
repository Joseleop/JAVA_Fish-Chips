package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PantallaBienvenida extends JPanel {

    private final JButton botonJugar = new JButton("Jugar");
    private final BufferedImage fondo;

    public PantallaBienvenida() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(Opciones.SPRITE_FONDO));
        } catch (Exception e) {
            System.err.println("ERROR cargando fondo: " + e.getMessage());
        }
        fondo = img;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel titulo = new JLabel("Fish & Chips", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 40));

        JTextArea reglas = new JTextArea(
                "Eres un pez de tamaño " + Opciones.JUGADOR_TAMANO_INICIAL
                        + ". Muévete con W, A, S y D.\n\n"
                        + "• Si tocas un pez MENOR que tú, te lo comes y creces.\n"
                        + "• Si tocas uno IGUAL o MAYOR, te come él y se acaba la partida.\n"
                        + "• Los peces más grandes nadan más rápido.\n\n"
                        + "Ganas al llegar a tamaño " + Opciones.TAMANO_VICTORIA + ".");
        reglas.setEditable(false);
        reglas.setFocusable(false);
        reglas.setOpaque(false);
        reglas.setLineWrap(true);
        reglas.setWrapStyleWord(true);
        reglas.setFont(new Font("SansSerif", Font.PLAIN, 20));

        botonJugar.setFont(new Font("SansSerif", Font.BOLD, 24));
        JPanel abajo = new JPanel();
        abajo.add(botonJugar);

        add(titulo, BorderLayout.NORTH);
        add(reglas, BorderLayout.CENTER);
        abajo.setOpaque(false);
        add(abajo, BorderLayout.SOUTH);
    }

    public JButton getBotonJugar() {
        return botonJugar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
