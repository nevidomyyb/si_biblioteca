package com.pedro.service;

import com.pedro.dao.FuncionarioDAO;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;

import java.sql.ResultSet;

public class FuncionarioService {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioService() {
        funcionarioDAO = new FuncionarioDAO();
    }

    public Funcionario consultarFuncionario(int id) {
        Funcionario funcionario = funcionarioDAO.consultarFuncionario(id);
        if (funcionario != null) return funcionario;
        System.err.println("[!] Nao foi possivel consultar funcionario");
        return null;
    }

    public Funcionario consultarFuncionario(String login) {
        Funcionario funcionario = funcionarioDAO.consultarFuncionario(login);
        if (funcionario != null) return funcionario;
        System.err.println("[!] Nao foi possivel consultar funcionario");
        return null;
    }

    public ResultSet listarFuncionarios(){ return funcionarioDAO.listarFuncionarios();
    }

    public boolean editarFuncionario(int id, Funcionario funcionario) {
        boolean valido = validarFuncionario(funcionario);
        if(!valido){
            return false;
        }
        funcionarioDAO.editarFuncionario(id, funcionario);
        System.out.println("[!] Funcionário editado com sucesso");
        return true;
    }

    public boolean registrarFuncionario (Funcionario funcionario) {
        boolean valido = validarFuncionario(funcionario);
        if(!valido){
            return false;
        }
        funcionarioDAO.cadastrarFuncionario(funcionario);
        System.out.println("[!] Funcionário cadastrado com sucesso");
        return true;
    }

    public boolean excluirFuncionario(Funcionario funcionario) {
        if (funcionario.getId() == 0) {
            return false;
        }
        boolean sucesso = funcionarioDAO.excluirFuncionario(funcionario.getId());
        if (!sucesso) {
            System.err.println("[!] Erro ao excluir funcionário");
            return false;
        }
        System.out.println("[!] Excluindo funcionário");
        return true;
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarFuncionario(Funcionario funcionario){
        if (isNullOrEmpty(funcionario.getNome())) {
            System.err.println("[!] Nome do Funcionário é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(funcionario.getEmail())) {
            System.err.println("[!] E-mail do Funcionário é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(funcionario.getCredencial())) {
            System.err.println("[!] Credencial do Funcionário é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(funcionario.getCpf())) {
            System.err.println("[!] CPF do Funcionário é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(funcionario.getLogin())) {
            System.err.println("[!] Login do Funcionário é obrigatório.");
            return false;
        }
        if (isNullOrEmpty(funcionario.getSenha())) {
            System.err.println("[!] Senha do Funcionário é obrigatório.");
            return false;
        }
        return true;
    }


}
