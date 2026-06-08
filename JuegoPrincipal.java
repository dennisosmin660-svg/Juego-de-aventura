package juegodeaventura;

import java.util.Scanner;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class JuegoPrincipal {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static Clip clipActual;

    static int perdidasDragon = 0;
    static int perdidasMago   = 0;
    static int perdidasTotal  = 0;

    static boolean ganoSonsonate   = false;
    static boolean ganoAhuachapan  = false;

    // COLORES
    static final String RESET        = "\u001B[0m";
    static final String LINEA_INTRO    = "\u001B[95m";
    static final String LINEA_MENU     = "\u001B[96m";
    static final String LINEA_BOSQUE   = "\u001B[32m";
    static final String LINEA_DRAGON   = "\u001B[91m";
    static final String LINEA_MAGO     = "\u001B[34m";
    static final String LINEA_VICTORIA = "\u001B[93m";
    static final String LINEA_CAMINOS  = "\u001B[33m";
    static final String LINEA_DERROTA  = "\u001B[31m";

    // REPRODUCTOR: BUSCA EN CARPETA DEL COMPAÑERO 2
    static void reproducirSonido(String archivo) {
        try {
            if (clipActual != null && clipActual.isRunning()) {
                clipActual.stop();
                clipActual.close();
            }
            File sonidoArchivo = new File("sonidos/" + archivo);
            if (sonidoArchivo.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonidoArchivo);
                clipActual = AudioSystem.getClip();
                clipActual.open(audioStream);
                clipActual.start();
            } else {
                System.out.println("  [!] Falta: sonidos/" + archivo);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("  [!] No se pudo reproducir: " + archivo);
        }
    }

    static void pausa() {
        System.out.print("\n  Presiona ENTER para continuar...");
        sc.nextLine();
        System.out.println();
    }

    static void limpiar() {
        for (int i = 0; i < 60; i++) System.out.println();
    }

    static void linea(String color) {
        System.out.println(color + "  ══════════════════════════════════════════════════════" + RESET);
    }

    static void titulo(String t, String colorLinea) {
        linea(colorLinea);
        System.out.println("  >>> " + t);
        linea(colorLinea);
        System.out.println();
    }

    static int pedirOpcion(int max) {
        while (true) {
            System.out.print("  Tu eleccion: ");
            String entrada = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(entrada);
                if (n >= 1 && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            System.out.println("  [!] Escribe un numero entre 1 y " + max + ".");
        }
    }

    static void perder(int tipo, String motivo) {
        reproducirSonido("fallo.wav"); // 🔊 SONIDO DE CASTIGO
        if (tipo == 1) { perdidasDragon++; }
        else           { perdidasMago++;   }
        perdidasTotal++;

        limpiar();
        linea(LINEA_DERROTA);
        System.out.println("  >>> DERROTA <<<");
        linea(LINEA_DERROTA);
        System.out.println("\n  " + motivo + "\n");
        System.out.println("  Errores acumulados: " + perdidasTotal + "\n");
        linea(LINEA_DERROTA);
        pausa();
        elegirCamino();
    }

    static int mostrarOpcionesAleatorias(String[] opts) {
        String correcta = opts[0];
        for (int i = opts.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String tmp = opts[i]; opts[i] = opts[j]; opts[j] = tmp;
        }
        int posCorrecta = 1;
        for (int i = 0; i < opts.length; i++) {
            System.out.println("  [" + (i+1) + "] " + opts[i]);
            if (opts[i].equals(correcta)) posCorrecta = i + 1;
        }
        System.out.println();
        return posCorrecta;
    }

    public static void main(String[] args) {
        reproducirSonido("intro.wav");
        historiaIntroduccion();
        reproducirSonido("menu.wav");
        menuPrincipal();
    }

    static void historiaIntroduccion() {
        limpiar();
        linea(LINEA_INTRO);
        System.out.println("\n         A V E N T U R A   E N   L O S   R E I N O S\n");
        linea(LINEA_INTRO);
        System.out.println("  Hace mucho tiempo, en una tierra llamada El Salvador,");
        System.out.println("  existian dos reinos poderosos: Sonsonate y Ahuachapan.");
        System.out.println("\n  Durante siglos vivieron en paz, prosperidad y armonia.");
        System.out.println("  Sus campos daban frutos, sus rios cantaban y su gente");
        System.out.println("  era feliz bajo la luz de dos lunas gemelas.");
        System.out.println("\n  Pero un dia oscuro llego sin aviso.");
        System.out.println("\n  Un hechicero sin nombre lanzo una maldicion sobre ambos");
        System.out.println("  reinos. El cielo se volvio gris. Las cosechas murieron.");
        System.out.println("  Y los dos grandes tesoros desaparecieron:");
        System.out.println("\n     * El TESORO DE ORO de Sonsonate... guardado por un");
        System.out.println("       Dragon Rojo en las profundidades de una cueva oscura.");
        System.out.println("\n     * El ANILLO MAGICO de Ahuachapan... custodiado por un");
        System.out.println("       ser misterioso en ruinas mas alla de un rio peligroso.");
        System.out.println("\n  Los ancianos dijeron que solo un heroe con valor,");
        System.out.println("  inteligencia y corazon fuerte podria romper la maldicion.");
        System.out.println("\n  Muchos intentaron. Ninguno regreso.");
        System.out.println("\n  Hoy... ese heroe eres TU.\n");
        linea(LINEA_INTRO);
        pausa();
    }

    static void menuPrincipal() {
        while (true) {
            reproducirSonido("menu.wav");
            limpiar();
            titulo("MENU PRINCIPAL", LINEA_MENU);
            System.out.println("  ESTADISTICAS:");
            System.out.println("    Errores Dragon : " + perdidasDragon);
            System.out.println("    Errores Mago   : " + perdidasMago);
            System.out.println("    Total errores  : " + perdidasTotal + "\n");
            System.out.println("  [1] COMENZAR JUEGO");
            System.out.println("  [2] REINICIAR CONTADORES");
            System.out.println("  [3] SALIR\n");

            int op = pedirOpcion(3);
            if (op == 1) elegirCamino();
            else if (op == 2) { 
                perdidasDragon = 0; perdidasMago = 0; perdidasTotal = 0; 
                ganoSonsonate = false; ganoAhuachapan = false; 
            }
            else { 
                if (clipActual != null) clipActual.stop();
                System.out.println("\n  Hasta pronto, heroe.\n"); break; 
            }
        }
    }

    static void elegirCamino() {
        reproducirSonido("pasos.wav");
        limpiar();
        titulo("ELIGE TU CAMINO", LINEA_CAMINOS);
        System.out.println("  Dos caminos se abren frente a ti:\n");
        System.out.println("  [1] CAMINO DEL DRAGON");
        System.out.println("      Bosque Oscuro -> Cueva -> TESORO DE ORO de Sonsonate\n");
        System.out.println("  [2] CAMINO DEL MAGO");
        System.out.println("      Rio peligroso -> Ruinas -> ANILLO MAGICO de Ahuachapan\n");
        int op = pedirOpcion(2);
        if (op == 1) CaminoDragon.iniciar();
        else         CaminoMago.iniciar();
    }

    static void ganarSonsonate() {
        reproducirSonido("victoria.wav");
        limpiar();
        linea(LINEA_VICTORIA);
        System.out.println("  >>>  VICTORIA!  TESORO DE ORO CONQUISTADO!  <<<");
        linea(LINEA_VICTORIA);
        System.out.println("\n  Abres el cofre. Miles de monedas de oro estallan en luz.");
        System.out.println("  El brillo lo llena todo. La cueva misma parece despertar.\n");
        System.out.println("  LA MALDICION DE SONSONATE HA SIDO ROTA.\n");

        if (ganoAhuachapan) { reproducirSonido("victoria_total.wav"); victoriaTotal(); }
        else {
            System.out.println("  Pero AHUACHAPAN sigue sufriendo...");
            linea(LINEA_VICTORIA);
            System.out.println("\n  [1] IR POR EL ANILLO MAGICO DE AHUACHAPAN");
            System.out.println("  [2] VOLVER AL MENU PRINCIPAL\n");
            int op = pedirOpcion(2);
            if (op == 1) CaminoMago.iniciar();
            else menuPrincipal();
        }
    }

    static void ganarAhuachapan() {
        reproducirSonido("victoria.wav");
        limpiar();
        linea(LINEA_VICTORIA);
        System.out.println("  >>>  VICTORIA!  ANILLO MAGICO CONQUISTADO!  <<<");
        linea(LINEA_VICTORIA);
        System.out.println("\n  El ser misterioso desaparece en luz blanca.");
        System.out.println("  El ANILLO MAGICO brilla con energia pura.\n");
        System.out.println("  LA MALDICION DE AHUACHAPAN HA SIDO ROTA.\n");

        if (ganoSonsonate) { reproducirSonido("victoria_total.wav"); victoriaTotal(); }
        else {
            System.out.println("  Pero SONSONATE sigue maldita...");
            linea(LINEA_VICTORIA);
            System.out.println("\n  [1] IR POR EL TESORO DE ORO DE SONSONATE");
            System.out.println("  [2] VOLVER AL MENU PRINCIPAL\n");
            int op = pedirOpcion(2);
            if (op == 1) CaminoDragon.iniciar();
            else menuPrincipal();
        }
    }

    static void victoriaTotal() {
        reproducirSonido("victoria_total.wav");
        limpiar();
        linea(LINEA_VICTORIA);
        System.out.println("  🎉 ¡LO LOGRASTE! 🎉");
        System.out.println("  YA CONSEGUISTE EL TESORO DE ORO Y EL ANILLO MAGICO");
        System.out.println("  HAS ROTO LA MALDICION DE LOS DOS REINOS PARA SIEMPRE");
        linea(LINEA_VICTORIA);
        System.out.println("\n  📊 CONTADOR FINAL:");
        System.out.println("    ❌ Veces perdidas Dragon: " + perdidasDragon);
        System.out.println("    ❌ Veces perdidas Mago:   " + perdidasMago);
        System.out.println("    🔢 TOTAL DE ERRORES:      " + perdidasTotal);
        linea(LINEA_VICTORIA);
        pausa();
        menuPrincipal();
    }
}