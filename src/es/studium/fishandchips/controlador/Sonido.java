package es.studium.fishandchips.controlador;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {

    private static final java.util.Map<String, Clip> clips = new java.util.HashMap<>();
    private static Clip musica;

    public static void precargar(String... rutas) {
        for (String ruta : rutas) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(
                        Sonido.class.getResource(ruta));
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clips.put(ruta, clip);
            } catch (Exception e) {
                System.err.println("ERROR sonido " + ruta + ": " + e.getMessage());
            }
        }
    }

    public static void reproducir(String ruta) {
        Clip clip = clips.get(ruta);
        if (clip == null) {
            return;
        }
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }
    public static void iniciarMusica(String ruta) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    Sonido.class.getResource(ruta));
            musica = AudioSystem.getClip();
            musica.open(audio);
            musica.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("ERROR música " + ruta + ": " + e.getMessage());
        }
    }

    public static void pararMusica() {
        if (musica != null) {
            musica.stop();
            musica.close();
        }
    }
}
