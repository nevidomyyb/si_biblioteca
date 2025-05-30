package com.pedro.models;

public class Livro {

    private int id;
    private String titulo;
    private int editorId;
    private String edicao;
    private String sinopse;
    private int generoId;
    private int autorId;

    public Livro(String titulo, int editorId, String edicao, String sinopse, int generoId, int autorId) {
        this.titulo = titulo;
        this.editorId = editorId;
        this.edicao = edicao;
        this.sinopse = sinopse;
        this.generoId = generoId;
        this.autorId = autorId;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }
}
