package com.example.pcquispe.apptrivia.entidades;

/**
 * Created by PCQUISPE on 2/22/2018.
 */

public class Respuesta {
    private  long id;
    private  String nombre;
    private  boolean is_correcta;

    public long getId() {
        return id;
    }

    public Respuesta(long id, String nombre, boolean is_correcta) {
        this.id = id;
        this.nombre = nombre;
        this.is_correcta = is_correcta;
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

    public boolean is_correcta() {
        return is_correcta;
    }

    public void setIs_correcta(boolean is_correcta) {
        this.is_correcta = is_correcta;
    }
}
