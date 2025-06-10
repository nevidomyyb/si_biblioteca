package com.pedro.models;

public class Professor extends Pessoa{

    private String disciplina;
    private String credencial;

    public Professor() {

    }

    public Professor(
            String cpf, String nome, String email,
             String disciplina, String credencial,
            String login, String senha
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
        this.credencial = credencial;
        this.login = login;
        this.senha = senha;
    }
    public void setId(int id) {this.id = id;}

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
