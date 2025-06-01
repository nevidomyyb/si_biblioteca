package com.sis_biblioteca.sis_biblioteca.models;

import java.util.Date;

public class Autor {

    private int id;
    private String nome;
    private Date dataNascimento;
    private String pseudonimo;

    public Autor(String nome, Date dataNascimento, String pseudonimo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.pseudonimo = pseudonimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }
}
