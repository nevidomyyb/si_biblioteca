package com.pedro.models;

public class Funcionario extends Pessoa{

    private String credencial;

    public Funcionario(
            String cpf, String nome, String telefone, String email,
            String credencial, String login, String senha
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.credencial = credencial;
        this.login = login;
        this.senha = senha;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
}
