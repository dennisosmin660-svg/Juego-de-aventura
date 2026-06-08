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

    

    static void caminoDragon_bosque() {
        JuegoPrincipal.reproducirSonido("pasos.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("BOSQUE OSCURO", JuegoPrincipal.LINEA_BOSQUE);
        System.out.println("  El viento mueve los arboles. La luz desaparece entre las ramas.");
        System.out.println("  El camino hacia la cueva del Dragon esta bloqueado:");
        System.out.println("  ramas caidas, rocas enormes y pantanos traicioneros.\n");
        System.out.println("  Que haces para avanzar?\n");

        String[] ops = {"Mover ramas y piedras con fuerza", "Rodear todo el bosque por fuera", "Correr sin mirar donde pisas"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("pasos.wav");
            System.out.println("\n  Abres paso lentamente, roca por roca, rama por rama.");
            System.out.println("  Tus manos sangran pero el camino queda libre.");
            System.out.println("  Sigues adelante con determinacion...");
            JuegoPrincipal.pausa();
            caminoDragon_cueva();
        } else {
            JuegoPrincipal.perder(1, "Elegiste mal el camino en el bosque.\nTe perdiste entre los arboles o te hundiste en el pantano.\nNo lograste llegar a la cueva del Dragon.");
        }
    }

    static void caminoDragon_cueva() {
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("LA CUEVA DEL DRAGON", JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  El aire se vuelve caliente. El suelo tiembla bajo tus pies.");
        System.out.println("  Una luz naranja ilumina las paredes de roca.");
        System.out.println("  De las sombras surge una criatura ENORME.\n");
        System.out.println("  Sus escamas rojas brillan como brasas encendidas.");
        System.out.println("  Sus ojos amarillos te fijan con furia.");
        System.out.println("  Abre las fauces y suelta un rugido que sacude la cueva:");
        // 🔊 PURO RUGIDO FUERTE
        JuegoPrincipal.reproducirSonido("rugido_fuerte.wav");
        System.out.println();
        // 🗣️ SU VOZ / OTRO IDIOMA
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"INTRUSO! COMO TE ATREVES A ENTRAR EN MI GUARIDA!\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("\n  El corazon te late a mil. Que haces?\n");
        System.out.println("  [1] Correr hacia atras");
        System.out.println("  [2] Sacar la espada y luchar\n");

        int op = JuegoPrincipal.pedirOpcion(2);
        if (op == 1) {
            JuegoPrincipal.perder(1, "Intentaste huir del Dragon.\nDesplegó sus enormes alas y el viento te tiro al suelo.\nFuiste DEVORADO. Un heroe nunca da la espalda al peligro.");
        } else {
            JuegoPrincipal.reproducirSonido("puñal.wav");
            System.out.println("\n  Sacas tu espada con firmeza. La hoja brilla en la oscuridad.");
            System.out.println("  El Dragon te mira sorprendido. Nadie habia hecho eso antes.");
            JuegoPrincipal.pausa();
            dragon_ronda1();
        }
    }

    static void dragon_ronda1() {
        JuegoPrincipal.reproducirSonido("fuego.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("RONDA 1 — LA PELEA COMIENZA", JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  El Dragon lanza una LLAMARADA DIRECTA hacia ti!");
        System.out.println("  El fuego ilumina toda la cueva. El calor es insoportable.\n");
        System.out.println("  El fuego viene rapido. Que haces?\n");

        String[] ops = {"Saltar al lado y esquivar la llama", "Plantar los pies y aguantar el fuego", "Cerrar los ojos y esperar"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            System.out.println("\n  Te lanzas al lado justo a tiempo!");
            System.out.println("  Las llamas queman el suelo donde estabas parado.");
            System.out.println("  El Dragon ruge furioso:");
            // 🔊 OTRO RUGIDO FUERTE
            JuegoPrincipal.reproducirSonido("rugido_fuerte.wav");
            System.out.println("  La pelea continua.");
            JuegoPrincipal.pausa();
            dragon_ronda2();
        } else {
            JuegoPrincipal.perder(1, "No esquivaste la llamarada del Dragon.\nNingun cuerpo humano puede resistir ese fuego.\nFuiste CARBONIZADO por las llamas.");
        }
    }

    static void dragon_ronda2() {
        JuegoPrincipal.reproducirSonido("golpe.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("RONDA 2 — EL CONTRAATAQUE", JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  El Dragon levanta su garra izquierda y la baja hacia ti");
        System.out.println("  con toda su fuerza. El impacto haria un crater en la roca.\n");
        System.out.println("  La garra cae. Que haces?\n");

        String[] ops = {"Clavar la espada en la garra antes de que llegue", "Correr y esquivar rodando", "Gritar para que se detenga"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("puñal.wav");
            System.out.println("\n  CLAVASTE TU ESPADA EN LA GARRA!");
            System.out.println("  El Dragon ruge de dolor:");
            // 🔊 RUGIDO DE DOLOR
            JuegoPrincipal.reproducirSonido("rugido_fuerte.wav");
            System.out.println("  Sangre oscura cae al suelo.");
            System.out.println("  Por primera vez en mil anos... alguien lo ha lastimado.");
            JuegoPrincipal.pausa();
            dragon_ronda3();
        } else {
            JuegoPrincipal.perder(1, "No lograste detener la garra del Dragon.\nEras demasiado lento o tu accion no tuvo efecto alguno.\nFuiste APLASTADO contra el suelo de la cueva.");
        }
    }

    static void dragon_ronda3() {
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("RONDA 3 — LOS DOS CAEN HERIDOS", JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  El Dragon herido da varios pasos atras, pero tu tampoco");
        System.out.println("  sales ileso. Su garra te rozo el hombro. Tu sangre gotea.");
        System.out.println("  El calor de la cueva quema tus heridas. Cada respiracion duele.\n");
        System.out.println("  El Dragon sacude la cabeza. Sus ojos ya no solo tienen");
        System.out.println("  rabia. Por primera vez en mil anos... tienen respeto.\n");
        System.out.println("  Lentamente se sienta. Sus alas se pliegan.");
        System.out.println("  El fuego de sus fauces se apaga. Te observa en silencio.");
        // 🗣️ SU VOZ AL HABLAR
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("\n  \"...Llevas heridas y sigues de pie. Yo tambien sangro.");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("   Mil anos llevo en esta cueva y nunca nadie habia logrado");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("   lastimarme. Pero TU lo hiciste, pequeno guerrero.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("\n  El Dragon baja su enorme cabeza hasta casi tocarte.");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Voy a hacerte un trato, UNICO e IRREPETIBLE.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Tengo un acertijo que guardo desde el principio de los tiempos.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Si TU lo resuelves... el tesoro es tuyo. Te lo juro.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Pero si fallas... las llamas terminan con todo.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Sin segunda oportunidad. Sin ruegos. Sin piedad.\"");
        System.out.println("\n  Sus ojos te perforan como brasas. Esta esperando tu decision.");
        JuegoPrincipal.linea(JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  Escucha bien el acertijo...");
        JuegoPrincipal.linea(JuegoPrincipal.LINEA_DRAGON);
        JuegoPrincipal.pausa();
        dragon_acertijo();
    }

    static void dragon_acertijo() {
        // 🔊 SOLO AMBIENTE, UNA SOLA VEZ
        JuegoPrincipal.reproducirSonido("misterio.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("EL ACERTIJO DEL DRAGON", JuegoPrincipal.LINEA_DRAGON);
        System.out.println("  El Dragon pronuncia cada palabra con calma mortal.");
        // 🗣️ SU VOZ / OTRO IDIOMA
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Escucha bien. Solo lo dire UNA vez.\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println();
        JuegoPrincipal.linea(JuegoPrincipal.LINEA_DRAGON);
        System.out.println();
        System.out.println("      Los vivos me temen y me evitan.");
        System.out.println("      Los muertos me necesitan y me buscan.");
        System.out.println("      Los ricos me desean para sus seres queridos.");
        System.out.println("      Soy el ultimo hogar de todo el que existe.");
        System.out.println("      Nadie entra en mi por voluntad propia.");
        System.out.println("      Pero todos, sin excepcion, terminan dentro de mi.");
        System.out.println();
        JuegoPrincipal.linea(JuegoPrincipal.LINEA_DRAGON);
        System.out.println();
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("  \"Que soy yo?\"");
        JuegoPrincipal.reproducirSonido("voz_dragon.wav");
        System.out.println("\n  ADVERTENCIA: Solo tienes UN intento.");
        System.out.println("  Si fallas, el fuego termina todo.\n");
        System.out.print("  Tu respuesta: ");
        String respuesta = JuegoPrincipal.sc.nextLine().trim().toLowerCase();
        System.out.println();

        if (respuesta.equals("ataud") || respuesta.equals("ataúd") || respuesta.equals("el ataud") || respuesta.equals("el ataúd")) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            System.out.println("  El Dragon te mira en silencio...");
            JuegoPrincipal.reproducirSonido("voz_dragon.wav");
            System.out.println("  \"...Mil anos. MIL ANOS esperando a alguien capaz.\"");
            JuegoPrincipal.reproducirSonido("voz_dragon.wav");
            System.out.println("  \"El tesoro es tuyo. Has ganado el derecho de llevarlo.\"");
            JuegoPrincipal.reproducirSonido("voz_dragon.wav");
            JuegoPrincipal.reproducirSonido("victoria.wav");
            JuegoPrincipal.ganoSonsonate = true;
            JuegoPrincipal.pausa();
            JuegoPrincipal.ganarSonsonate();
        } else {
            JuegoPrincipal.perder(1, "Respondiste el acertijo del Dragon de forma incorrecta.\nUna llamarada enorme lleno la cueva.\nFuiste CONSUMIDO POR LAS LLAMAS.");
        }
    }


    static void caminoMago_rio() {
        JuegoPrincipal.reproducirSonido("ruido_agua.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("RIO PELIGROSO", JuegoPrincipal.LINEA_MAGO);
        System.out.println("  El camino al mago cruza un rio de corriente brutal.");
        System.out.println("  El puente esta roto. Las rocas son resbaladizas.");
        System.out.println("  El agua brama con furia.\n");
        System.out.println("  Como cruzas?\n");

        String[] ops = {"Reparar el puente con ramas y piedras", "Nadar contra la corriente", "Construir un muro sobre el agua"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            System.out.println("\n  Con paciencia reparas los tablones rotos.");
            System.out.println("  El puente aguanta. Cruzas con cuidado.");
            JuegoPrincipal.pausa();
            mago_misterioso();
        } else {
            JuegoPrincipal.perder(2, "No pudiste cruzar el rio peligroso.\nLa corriente era demasiado fuerte o tu plan era imposible.\nLas aguas te arrastraron sin piedad.");
        }
    }

    static void mago_misterioso() {
        JuegoPrincipal.reproducirSonido("misterio.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("ALGO EN EL AIRE...", JuegoPrincipal.LINEA_MAGO);
        System.out.println("  El cielo se oscurece de golpe. El viento se detiene.");
        System.out.println("  Una figura encapuchada flota frente a ti en el aire.");
        System.out.println("  No tiene rostro. Sus ojos brillan con luz violeta.");
        System.out.println("  Silencio total.");
        // 🗣️ VOZ EXCLUSIVA DEL MAGO
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("\n  \"A que has venido aqui, pequeno humano?\"");
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("  \"Este camino no es para debiles.\"");
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("\n  La figura te bloquea el paso. Que haces?\n");
        System.out.println("  [1] Correr y atacarlo sin miedo");
        System.out.println("  [2] Hablarle para que te deje pasar\n");

        int op = JuegoPrincipal.pedirOpcion(2);
        if (op == 2) {
            JuegoPrincipal.perder(2, "Intentaste hablarle al ser de oscuridad.\nCon seres asi, las palabras no tienen efecto alguno.\nUna energia oscura te envolvio. Fuiste ELIMINADO.");
        } else {
            JuegoPrincipal.reproducirSonido("puñal.wav");
            System.out.println("\n  Corres y atacas sin pensarlo dos veces!");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"No huiste. No suplicaste.\"");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"Te hare 3 preguntas. Un intento por cada una.\"");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"Si fallas... te elimino.\"");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("\n  Aceptas el reto?\n");
            System.out.println("  [1] Aceptar el reto");
            System.out.println("  [2] Negarse");
            System.out.println("  [3] Correr y huir\n");

            int op2 = JuegoPrincipal.pedirOpcion(3);
            if (op2 == 1) { JuegoPrincipal.pausa(); mago_prueba1(); }
            else if (op2 == 2) { JuegoPrincipal.perder(2, "Rechazaste el reto del ser misterioso.\nUn heroe enfrenta los desafios, no los evita.\nUn rayo oscuro termino con todo."); }
            else { JuegoPrincipal.perder(2, "Intentaste huir del ser misterioso.\nUna fuerza invisible te frenó en seco.\nLa oscuridad te consumio por completo."); }
        }
    }

    static void mago_prueba1() {
        JuegoPrincipal.reproducirSonido("misterio.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("PRUEBA 1 — CONOCIMIENTO", JuegoPrincipal.LINEA_MAGO);
        // 🗣️ VOZ DEL MAGO
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("  \"Primera pregunta.\"\n");
        System.out.println("  Cuanto es 7 x 8?\n");

        String[] ops = {"56", "49", "42"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"Correcto. No esperaba eso.\"");
            JuegoPrincipal.pausa(); mago_prueba2();
        } else {
            JuegoPrincipal.perder(2, "Fallaste la primera prueba del ser misterioso.\nUna multiplicacion basica y no acertaste.\n\"No eres tan listo.\" Fuiste ELIMINADO.");
        }
    }

    static void mago_prueba2() {
        JuegoPrincipal.reproducirSonido("misterio.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("PRUEBA 2 — LOGICA", JuegoPrincipal.LINEA_MAGO);
        // 🗣️ VOZ DEL MAGO
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("  \"Segunda pregunta. Esta engana a muchos.\"\n");
        System.out.println("  Que pesa mas: 1 kg de algodon o 1 kg de hierro?\n");

        String[] ops = {"Pesan igual", "Pesa mas el hierro", "Pesa mas el algodon"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"Impresionante. No muchos logran esto.\"");
            JuegoPrincipal.pausa(); mago_prueba3();
        } else {
            JuegoPrincipal.perder(2, "Caiste en la trampa de logica del ser misterioso.\nTe dejo llevar por las apariencias en vez de pensar.\n\"La trampa mas vieja del mundo.\" Fuiste ELIMINADO.");
        }
    }

    static void mago_prueba3() {
        JuegoPrincipal.reproducirSonido("misterio.wav");
        JuegoPrincipal.limpiar();
        JuegoPrincipal.titulo("PRUEBA 3 — CALCULO FINAL", JuegoPrincipal.LINEA_MAGO);
        // 🗣️ VOZ DEL MAGO
        JuegoPrincipal.reproducirSonido("mago_voz.wav");
        System.out.println("  \"La ultima. La mas simple... o eso parece.\"\n");
        System.out.println("  Cuanto es 1 - 1 + 1 - 1?\n");

        String[] ops = {"0", "1", "2"};
        int correcta = JuegoPrincipal.mostrarOpcionesAleatorias(ops);
        int eleccion = JuegoPrincipal.pedirOpcion(3);

        if (eleccion == correcta) {
            JuegoPrincipal.reproducirSonido("acierto.wav");
            JuegoPrincipal.reproducirSonido("mago_voz.wav");
            System.out.println("  \"...Lo has logrado. Eres digno.\"");
            JuegoPrincipal.reproducirSonido("victoria.wav");
            JuegoPrincipal.ganoAhuachapan = true;
            JuegoPrincipal.pausa();
            JuegoPrincipal.ganarAhuachapan();
        } else {
            JuegoPrincipal.perder(2, "Fallaste el calculo final del ser misterioso.\nTan cerca de lograrlo... y aun asi te equivocaste.\nFuiste ELIMINADO sin piedad.");
        }
    }
}