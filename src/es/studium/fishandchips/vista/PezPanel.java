package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.Entidad;
import es.studium.fishandchips.modelo.PezEnemigo;
import es.studium.fishandchips.modelo.PezJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PezPanel extends JPanel {
    private PezJugador pezJugador;
    private List<PezEnemigo> enemigos;

    private final Map<String, BufferedImage> imagenes = new HashMap<>();
    private final BufferedImage fondo;

    public PezPanel() {
        imagenes.put(Opciones.SPRITE_JUGADOR, leerImagen(Opciones.SPRITE_JUGADOR));
        for (String sprite : Opciones.SPRITES_ENEMIGOS) {
            imagenes.put(sprite, leerImagen(sprite));
        }
        fondo = leerImagen(Opciones.SPRITE_FONDO);
    }

    private BufferedImage leerImagen(String ruta) {
        try {
            return ImageIO.read(getClass().getResource(ruta));
        } catch (Exception e) {
            System.out.println("ERROR cargando " + ruta + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
        if (enemigos != null) {
            for (PezEnemigo enemigo : enemigos) {
                dibujarPez(g, enemigo, enemigo.getDireccion() < 0);
            }
        }
        if (pezJugador != null) {
            dibujarPez(g, pezJugador, false);
        }
    }

    private void dibujarPez(Graphics g, Entidad pez, boolean volteado) {
        BufferedImage imagen = imagenes.get(pez.getSprite());
        if (imagen == null) {
            return;
        }
        int x = pez.getPosX();
        int y = pez.getPosY();
        int lado = pez.getLado();
        if (volteado) {
            g.drawImage(imagen, x + lado, y, -lado, lado, null);   // ancho negativo = espejo
        } else {
            g.drawImage(imagen, x, y, lado, lado, null);
        }
    }

    public void setPezJugador(PezJugador pezJugador) {
        this.pezJugador = pezJugador;
    }

    public void setEnemigos(List<PezEnemigo> enemigos) {
        this.enemigos = enemigos;
    }
}