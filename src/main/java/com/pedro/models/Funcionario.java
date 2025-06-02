package com.pedro.models;

public class Funcionario extends Pessoa{

    private String credencial;

    public Funcionario(
            String cpf, String nome, String telefone, String email,
            int enderecoId, String credencial
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecoId = enderecoId;
        this.credencial = credencial;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
}
