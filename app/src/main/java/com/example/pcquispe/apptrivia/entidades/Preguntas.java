package com.example.pcquispe.apptrivia.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PCQUISPE on 3/13/2018.
 */

public class Preguntas {
    private static List<Pregunta> vectorPreguntas;

    public Preguntas() { }

    public static void setPreguntas(ArrayList<Pregunta> preguntas) {
        vectorPreguntas = preguntas;
    }

    public static void shuffle() {
        Collections.shuffle(vectorPreguntas);
    }

    public static Pregunta getPregunta(int id) {
        return vectorPreguntas.get(id);
    }

    public static int size() {
        return vectorPreguntas.size();
    }
}
