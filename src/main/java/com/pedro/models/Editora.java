package com.pedro.models;

public class Editora {

    private int id;
    private String nome;
    private String cnpj;
    private int enderecoMatrizId;

    public Editora(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public int getEnderecoMatrizId() {
        return enderecoMatrizId;
    }

    public void setEnderecoMatrizId(int enderecoMatrizId) {
        this.enderecoMatrizId = enderecoMatrizId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
