package com.example.pcquispe.apptrivia.entidades;

import java.io.Serializable;

public class Ranking implements Serializable {
    private int id;
    private String nombre;
    private int tiempo;
    private int fallos;
    private int aciertos;
    private int puntos;

    public Ranking(int id, String nombre, int tiempo, int fallos, int aciertos, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.fallos = fallos;
        this.aciertos = aciertos;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}