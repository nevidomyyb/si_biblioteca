package com.pedro.models;

public class Professor extends Pessoa{

    private String disciplina;
    private String credencial;

    public Professor(
            String cpf, String nome, String telefone, String email,
            Endereco endereco, String disciplina, String credencial
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.disciplina = disciplina;
        this.credencial = credencial;
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
