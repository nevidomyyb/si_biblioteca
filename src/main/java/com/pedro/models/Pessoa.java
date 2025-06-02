package com.pedro.models;

public abstract class Pessoa {

    protected int id;
    protected String nome;
    protected String telefone;
    protected String email;
    protected String cpf;
    protected int enderecoId;

    public int getId() {
        return id;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int id) {
        this.enderecoId = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
