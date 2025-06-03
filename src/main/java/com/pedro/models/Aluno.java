package com.pedro.models;

public class Aluno extends Pessoa{

    private int id;
    private String curso;
    private String periodo;
    private String turno;
    private String matricula;
    public Aluno() {

    }
    public Aluno(
            String cpf, String nome, String telefone, String email,
            int enderecoId, String curso, String periodo,
            String turno, String matricula, String login, String senha
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecoId = enderecoId;
        this.curso = curso;
        this.periodo = periodo;
        this.turno = turno;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
