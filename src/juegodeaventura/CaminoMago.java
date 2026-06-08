package juegodeaventura;

public class CaminoMago {
    public static void iniciar() {
        caminoMago_rio();
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