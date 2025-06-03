package com.pedro.service;

import com.pedro.dao.AlunoDAO;
import com.pedro.models.Aluno;

import java.sql.ResultSet;

public class AlunoService {

    private AlunoDAO alunoDAO;

    public AlunoService() {
        alunoDAO = new AlunoDAO();
    }

    public Aluno consultarAluno(int id) {
        Aluno aluno = alunoDAO.consultarAluno(id);
        if (aluno instanceof Aluno) return aluno;
        System.err.println("[!] Nao foi possivel consultar aluno");
        return null;
    }

    public Aluno consultarAluno(String login) {
        Aluno aluno = alunoDAO.consultarAluno(login);
        if (aluno instanceof Aluno) return aluno;
        System.err.println("[!] Nao foi possivel consultar aluno");
        return null;
    }

    public ResultSet listarAlunos(){ return alunoDAO.listarAlunos();
    }

    public boolean editarAluno(int id, Aluno aluno) {
        boolean valido = validarAluno(aluno);
        if(!valido){
            return false;
        }
        alunoDAO.editarAluno(id, aluno);
        System.out.println("[!] Aluno editado com sucesso");
        return true;
    }

    public boolean registrarAluno (Aluno aluno) {
        boolean valido = validarAluno(aluno);
        if(!valido){
            return false;
        }
        alunoDAO.cadastrarAluno(aluno);
        System.out.println("[!] Aluno cadastrado com sucesso");
        return true;
    }

    public boolean excluirAluno(Aluno aluno) {
        if (aluno.getId() == 0) {
            return false;
        }
        boolean sucesso = alunoDAO.excluirAluno(aluno.getId());
        if (!sucesso) {
            System.err.println("[!] Erro ao excluir aluno");
            return false;
        }
        System.out.println("[!] Excluindo aluno");
        return true;
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarAluno(Aluno aluno){
        if (isNullOrEmpty(aluno.getNome())) {
            System.err.println("[!] Nome do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getEmail())) {
            System.err.println("[!] E-mail do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getCurso())) {
            System.err.println("[!] Curso do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getPeriodo())) {
            System.err.println("[!] Período do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getTurno())) {
            System.err.println("[!] Turno do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getMatricula())) {
            System.err.println("[!] Matricula do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getCpf())) {
            System.err.println("[!] CPF do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getLogin())) {
            System.err.println("[!] Login do Aluno é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(aluno.getSenha())) {
            System.err.println("[!] Senha do Aluno é obrigatório.");
            return false;
        }
        return true;
    }


}
