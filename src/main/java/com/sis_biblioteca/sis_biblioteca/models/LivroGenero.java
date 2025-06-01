package com.sis_biblioteca.sis_biblioteca.models;

public class LivroGenero {

    private int id;
    private int livroId;
    private int generoId;

    public LivroGenero(int livroId, int generoId) {
        this.livroId = livroId;
        this.generoId = generoId;
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

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }
}
