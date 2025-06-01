package com.sis_biblioteca.sis_biblioteca.models;

public class Aluno extends Pessoa{

    private String curso;
    private String periodo;
    private String turno;
    private String matricula;

    public Aluno(
            String cpf, String nome, String telefone, String email,
            Endereco endereco, String curso, String periodo,
            String turno, String matricula
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.curso = curso;
        this.periodo = periodo;
        this.turno = turno;
        this.matricula = matricula;
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
