package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

public class PezPanel extends JPanel {
    private PezJugador pezJugador;
    private BufferedImage imagenPezJ;

    private List<PezEnemigo> enemigos;
    private BufferedImage imagenPezE;

    public PezPanel() {
        try {
            URL pathJugador = getClass().getResource(Opciones.SPRITE_JUGADOR);
            URL pathEnemigo = getClass().getResource(Opciones.SPRITE_ENEMIGO);
            imagenPezJ = ImageIO.read(pathJugador);
            imagenPezE = ImageIO.read(pathEnemigo);
        } catch (Exception e) {
            System.out.println("ERROR EN PEZPANEL: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pezJugador != null) {
            int posXJugador = this.pezJugador.getPosX();
            int posYJugador = this.pezJugador.getPosY();
            g.drawImage(imagenPezJ, posXJugador, posYJugador, null);
        }
        if(enemigos != null) {
            for (PezEnemigo enemigo : enemigos) {
                int posXEnemigo = enemigo.getPosX();
                int posYEnemigo = enemigo.getPosY();
                g.drawImage(imagenPezE, posXEnemigo, posYEnemigo, null);
            }
        }
    }

    public void setPezJugador(PezJugador pezJugador) {
        this.pezJugador = pezJugador;
    }

    public void setEnemigos(List<PezEnemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public int getAltoImagenJugador() {
        return imagenPezJ.getHeight();
    }

    public int getAnchoImagenJugador() {
        return imagenPezJ.getWidth();
    }

    public int getAltoImagenEnemigo() {
        return imagenPezE.getHeight();
    }

    public int getAnchoImagenEnemigo() {
        return imagenPezE.getWidth();
    }

}
