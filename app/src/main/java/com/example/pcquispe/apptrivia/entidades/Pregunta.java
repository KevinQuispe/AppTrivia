package com.example.pcquispe.apptrivia.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pregunta implements Serializable {
    private long id;
    private String nombre;
    ArrayList<Respuesta> respuestas = new ArrayList<>();
    private int respuestaCorrecta;
    private String[] respuesta;

    public Pregunta(long id, String nombre, ArrayList<Respuesta> respuestas) {
        this.id = id;
        this.nombre = nombre;
        this.respuestas = respuestas;
    }

    public Pregunta(String pregunta, String respuestaCorrecta,
                    String respuestaIncorrecta1, String respuestaIncorrecta2,
                    String respuestaIncorrecta3) {
        this.nombre = pregunta;
        respuesta = new String[4];
        this.respuestaCorrecta = 0;
        respuesta[0] = respuestaCorrecta;
        respuesta[1] = respuestaIncorrecta1;
        respuesta[2] = respuestaIncorrecta2;
        respuesta[3] = respuestaIncorrecta3;
    }
    public String getRespuesta(int i) {
        return respuesta[i];
    }
    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Pregunta(long id, String nombre) {
        this(id, nombre, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public void shuffle() {
        String[] respuestasAnteriores = new String[4];
        System.arraycopy(respuesta, 0, respuestasAnteriores, 0, respuesta.length);
        int respuestaCorrectaAnterior = respuestaCorrecta;
        respuesta[0] = respuesta[1] = respuesta[2] = respuesta[3] = null;
        Random random = new Random();
        int number;
        for (int i = 0; i < 4; i++) {
            number = random.nextInt(4);
            while (respuesta[number] != null) {
                number = (number + 1) % 4;
            }
            respuesta[number] = respuestasAnteriores[i];
            if (respuestaCorrectaAnterior == i) respuestaCorrecta = number;
        }
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String[] getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String[] respuesta) {
        this.respuesta = respuesta;
    }
}