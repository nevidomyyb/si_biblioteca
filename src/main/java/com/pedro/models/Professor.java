package com.pedro.models;

public class Professor extends Pessoa{

    private String disciplina;
    private String credencial;

    public Professor() {

    }

    public Professor(
            String cpf, String nome, String telefone, String email,
            int endereco, String disciplina, String credencial,
            String login, String senha
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecoId = endereco;
        this.disciplina = disciplina;
        this.credencial = credencial;
        this.login = login;
        this.senha = senha;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
}
