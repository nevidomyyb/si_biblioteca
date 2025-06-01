package com.sis_biblioteca.sis_biblioteca.models;

public class Editora {

    private int id;
    private String nome;
    private String cnpj;
    private Endereco enderecoMatriz;

    public Editora(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Endereco getEnderecoMatriz() {
        return enderecoMatriz;
    }

    public void setEnderecoMatriz(Endereco enderecoMatriz) {
        this.enderecoMatriz = enderecoMatriz;
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
