package juegodeaventura;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GestionRecursos {
    private static Clip clipSonido;

    // CAMBIAR IMAGEN DE FONDO
    public static void cambiarImagen(String nombreArchivo) {
        try {
            ImageIcon imagen = new ImageIcon("imagenes/" + nombreArchivo);
            Image imgEscalada = imagen.getImage().getScaledInstance(
                    900, 460, Image.SCALE_SMOOTH
            );
            JuegoPrincipal.etiquetaFondo.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.err.println("❌ No se pudo cargar: " + nombreArchivo);
            JuegoPrincipal.etiquetaFondo.setIcon(null);
        }
    }

    // REPRODUCIR SONIDO
    public static void reproducirSonido(String nombreArchivo) {
        new Thread(() -> {
            try {
                if (clipSonido != null && clipSonido.isRunning()) {
                    clipSonido.stop();
                    clipSonido.close();
                }
                File archivoSonido = new File("sonidos/" + nombreArchivo);
                AudioInputStream flujo = AudioSystem.getAudioInputStream(archivoSonido);
                clipSonido = AudioSystem.getClip();
                clipSonido.open(flujo);
                clipSonido.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("🔊 Error al reproducir: " + nombreArchivo);
            }
        }).start();
    }

    // DETENER SONIDO
    public static void detenerSonido() {
        if (clipSonido != null && clipSonido.isRunning()) {
            clipSonido.stop();
            clipSonido.close();
        }
    }
}