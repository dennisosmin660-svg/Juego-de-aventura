package juegodeaventura;

public class CaminoDragon {
    public static void iniciar() {
        caminoDragon_bosque();
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
}