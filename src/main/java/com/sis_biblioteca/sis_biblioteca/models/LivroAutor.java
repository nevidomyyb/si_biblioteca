package com.sis_biblioteca.sis_biblioteca.models;

public class LivroAutor {

    private int id;
    private int livroId;
    private int autorId;

    public LivroAutor(int autorId, int livroId) {
        this.autorId = autorId;
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

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }
}
