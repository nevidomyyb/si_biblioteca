package com.pedro.service;

import com.pedro.dao.FuncionarioDAO;
import com.pedro.dao.ProfessorDAO;
import com.pedro.models.Funcionario;
import com.pedro.models.Professor;

import java.sql.ResultSet;

public class ProfessorService {

    private ProfessorDAO professorDAO;

    public ProfessorService() {
        professorDAO = new ProfessorDAO();
    }

    public Professor consultarProfessor(int id) {
        Professor professor = professorDAO.consultarProfessor(id);
        if (professor != null) return professor;
        System.err.println("[!] Nao foi possivel consultar professor");
        return null;
    }

    public Professor consultarProfessor(String login) {
        Professor professor = professorDAO.consultarProfessor(login);
        if (professor != null) return professor;
        System.err.println("[!] Nao foi possivel consultar professor");
        return null;
    }

    public ResultSet listarProfessors(){ return professorDAO.listarProfessores();
    }

    public boolean editarProfessor(int id, Professor professor) {
        boolean valido = validarProfessor(professor);
        if(!valido){
            return false;
        }
        professorDAO.editarProfessor(id, professor);
        System.out.println("[!] Professor editado com sucesso");
        return true;
    }

    public boolean registrarProfessor (Professor professor) {
        boolean valido = validarProfessor(professor);
        if(!valido){
            return false;
        }
        professorDAO.cadastrarProfessor(professor);
        System.out.println("[!] Professor cadastrado com sucesso");
        return true;
    }

    public boolean excluirProfessor(Professor professor) {
        if (professor.getId() == 0) {
            return false;
        }
        boolean sucesso = professorDAO.excluirProfessor(professor.getId());
        if (!sucesso) {
            System.err.println("[!] Erro ao excluir Professor");
            return false;
        }
        System.out.println("[!] Excluindo professor");
        return true;
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarProfessor(Professor professor){
        if (isNullOrEmpty(professor.getNome())) {
            System.err.println("[!] Nome do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getEmail())) {
            System.err.println("[!] E-mail do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getDisciplina())) {
            System.err.println("[!] Disciplina do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getCredencial())) {
            System.err.println("[!] Credencial do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getCpf())) {
            System.err.println("[!] CPF do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getLogin())) {
            System.err.println("[!] Login do Professor é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(professor.getSenha())) {
            System.err.println("[!] Senha do Professor é obrigatório.");
            return false;
        }
        return true;
    }


}
