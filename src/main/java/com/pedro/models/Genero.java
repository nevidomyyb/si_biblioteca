package com.pedro.models;

public class Genero {

    private int id;
    private String genero;

    public Genero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
