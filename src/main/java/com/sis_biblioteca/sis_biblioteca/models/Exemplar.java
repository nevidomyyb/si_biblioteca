package com.sis_biblioteca.sis_biblioteca.models;

public class Exemplar {

    private int id;
    private int livroId;

    public Exemplar(int livroId) {
        this.livroId = livroId;
    }

    public int getId() {
        return id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }
}
