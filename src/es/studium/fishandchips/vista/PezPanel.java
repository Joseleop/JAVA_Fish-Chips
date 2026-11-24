package es.studium.fishandchips.vista;

import es.studium.fishandchips.Opciones;
import es.studium.fishandchips.modelo.*;

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
    private List<Comida> comidas;
    private Modelo modelo;

    private final Map<String, BufferedImage> imagenes = new HashMap<>();
    private final BufferedImage fondo;

    public PezPanel() {
        imagenes.put(Opciones.SPRITE_JUGADOR, leerImagen(Opciones.SPRITE_JUGADOR));
        imagenes.put(Opciones.SPRITE_COMIDA, leerImagen(Opciones.SPRITE_COMIDA));
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
        if (comidas != null) {
            for (Comida c : comidas) {
                dibujarPez(g, c, false);
            }
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
        if (modelo != null) {
            dibujarMarcador(g);
        }
    }

    private void dibujarMarcador(Graphics g) {
        String texto = "Peces: " + modelo.getPecesComidos()
                + "   Tamaño: " + modelo.getPezJugador().getSize()
                + " / " + Opciones.TAMANO_VICTORIA;
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString(texto, 22, 37);    // sombra, para que se lea sobre cualquier fondo
        g.setColor(Color.WHITE);
        g.drawString(texto, 20, 35);
    }

    public void setPezJugador(PezJugador pezJugador) {
        this.pezJugador = pezJugador;
    }

    public void setEnemigos(List<PezEnemigo> enemigos) {
        this.enemigos = enemigos;
    }
    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}