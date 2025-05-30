package com.pedro.models;

public class Funcionario extends Pessoa{

    private String credencial;

    public Funcionario(
            String cpf, String nome, String telefone, String email,
            Endereco endereco, String credencial
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.credencial = credencial;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
}
