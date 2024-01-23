package com.example.clubesvcannabicos.data;

public class Valoracion {
    String nombre;
    String valoracion;

    public Valoracion() {
    }

    public Valoracion(String nombre, String valoracion) {
        this.nombre = nombre;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
