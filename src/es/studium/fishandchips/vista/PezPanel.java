package es.studium.fishandchips.vista;

import es.studium.fishandchips.modelo.PezJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class PezPanel extends JPanel {
    private PezJugador pezJugador;
    private BufferedImage imagenPezJ;

    public PezPanel(){
        try {
            URL path = getClass().getResource("/pezjugador01.png");
            imagenPezJ = ImageIO.read(path);
        } catch (Exception e) {
            System.out.println("ERROR EN PEZPANEL: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(pezJugador != null) {
            int posX = this.pezJugador.getPosX();
            int posY = this.pezJugador.getPosY();
            g.drawImage(imagenPezJ, posX, posY, null);
        }
    }

    public void setPezJugador(PezJugador pezJugador){
        this.pezJugador = pezJugador;
    }

    public int getAltoImagen(){
        return imagenPezJ.getHeight();
    }
    public int getAnchoImagen(){
        return imagenPezJ.getWidth();
    }

}
