package com.pedro.dao;

import com.pedro.config.Conexao;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public FuncionarioDAO() {
        conexao = new Conexao();
    }

    public boolean editarFuncionario(int id, Funcionario funcionario) {
        try {
            String query = "UPDATE funcionario SET ";
            List<String> campos = new ArrayList<String>();
            List<Object> valores = new ArrayList<Object>();

            if(validarString(funcionario.getNome())){
                campos.add("nome = ?");
                valores.add(funcionario.getNome());
            }

            if(validarString(funcionario.getTelefone())){
                campos.add("telefone = ?");
                valores.add(funcionario.getTelefone());
            }

            if(validarString(funcionario.getEmail())){
                campos.add("email = ?");
                valores.add(funcionario.getEmail());
            }

            if(validarString(funcionario.getCredencial())){
                campos.add("credencial = ?");
                valores.add(funcionario.getCredencial());
            }

            if(validarString(funcionario.getCpf())){
                campos.add("cpf = ?");
                valores.add(funcionario.getTelefone());
            }

            if(validarString(funcionario.getLogin())){
                campos.add("login = ?");
                valores.add(funcionario.getLogin());
            }

            if(validarString(funcionario.getSenha())){
                campos.add("senha = ?");
                valores.add(funcionario.getSenha());
            }

            query += String.join(", ", campos) + "WHERE id = ?";
            ps = conexao.getConn().prepareStatement(query);
            int index = 1;
            for(Object valor : valores){
                ps.setObject(index++, valor);
            }
            ps.setInt(index, id);
            ps.executeUpdate();
            ps.close();
            return true;            
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
        boolean sucesso = autenticacaoDAO.cadastrarUsuario(funcionario, null, null);

    }

    public ResultSet listarFuncionarios() {
        try {
            return conexao.getConn().createStatement().executeQuery("SELECT * FROM funcionario;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Funcionario consultarFuncionario(String login) {
        try {
            String SQL = "SELECT * FROM funcionario WHERE login = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            Funcionario funcionario = null;
            if (rs.next()) {
                funcionario = new Funcionario(
                        rs.getString("cpf"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("email"),
                        rs.getString("credencial"), rs.getInt("endereco_id"), rs.getString("login"),
                        rs.getString("senha"));
                return funcionario;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Funcionario consultarFuncionario(int id) {
        try {
            String SQL = "SELECT * FROM funcionario WHERE id = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Funcionario funcionario = null;
            if (rs.next()) {
                funcionario = new Funcionario(
                        rs.getString("cpf"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("email"),
                        rs.getString("credencial"), rs.getInt("endereco_id"), rs.getString("login"),
                        rs.getString("senha"));
                return funcionario;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean excluirFuncionario(int id) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "DELETE FROM funcionario WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validarString(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }

}
