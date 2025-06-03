package com.pedro.dao;

import com.pedro.config.Conexao;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public FuncionarioDAO() {
        conexao = new Conexao();
    }

    public boolean editarFuncionario(int id, Funcionario funcionario) {
        try {
            String SQL = """
            UPDATE funcionario SET 
                cpf = ?, nome = ?, telefone = ?, email = ?,
                endereco_id = ?, credencial = ?,
                
            """;
            ps = conexao.getConn().prepareStatement(SQL);
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getEmail());
            if (funcionario.getEnderecoId() != 0) {
                ps.setInt(5, funcionario.getEnderecoId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setString(6, funcionario.getCredencial());
            ps.close();
            return true;

        } catch (SQLException e) {
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
                        rs.getString("credencial"), rs.getInt("endereco_id"),rs.getString("login"),
                        rs.getString("senha")
                );
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
                        rs.getString("credencial"), rs.getInt("endereco_id"),rs.getString("login"),
                        rs.getString("senha")
                );
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
                    "DELETE FROM funcionario WHERE id = ?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}
