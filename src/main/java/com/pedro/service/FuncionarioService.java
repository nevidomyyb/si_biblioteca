package com.pedro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.dao.FuncionarioDAO;
import com.pedro.models.Funcionario;

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

    public List<Funcionario> listarFuncionarios(){
        List<Funcionario> funcionarioList = null;
        try {
            ResultSet rs = funcionarioDAO.listarFuncionarios();
            funcionarioList = new ArrayList<Funcionario>();
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                func.setTelefone(rs.getString("telefone"));
                func.setEmail(rs.getString("email"));
                func.setCredencial(rs.getString("credencial"));
                func.setEnderecoId(rs.getInt("endereco_id"));
                func.setLogin(rs.getString("login"));
                func.setSenha(rs.getString("senha"));

                funcionarioList.add(func);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarioList;
    }

    public boolean editarFuncionario(int id, Funcionario funcionario) {
        if(funcionario == null){
            System.err.println("[!] Funcionário Inválido");
            return false;
        }

        if(id <= 0){
            System.err.println("[!] ID Inválido");
            return false;
        }

        return funcionarioDAO.editarFuncionario(id, funcionario);
    }

    public boolean registrarFuncionario (Funcionario funcionario) {
        boolean valido = validarFuncionario(funcionario);
        if(!valido){
            return false;
        }
        return funcionarioDAO.cadastrarFuncionario(funcionario);
    }

    public boolean excluirFuncionario(int id) {
        if (id <= 0) {
            return false;
        }
        return funcionarioDAO.excluirFuncionario(id);
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
