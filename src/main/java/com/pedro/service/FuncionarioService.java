package com.pedro.service;

import com.pedro.dao.FuncionarioDAO;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        funcionarioDAO.editarFuncionario(id, funcionario);
        System.out.println("[!] Funcionário editado com sucesso.");
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

    public boolean excluirFuncionario(int id) {
        if (id <= 0) {
            return false;
        }
        boolean sucesso = funcionarioDAO.excluirFuncionario(id);
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
