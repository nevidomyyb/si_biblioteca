package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.pedro.config.Conexao;
import com.pedro.models.Editora;

public class EditoraDAO {
    private Conexao conexao;
    private PreparedStatement ps;

    public EditoraDAO() {
        conexao = new Conexao();
    }

    public ResultSet listarEditoras() {
        try {
            return conexao.getConn().createStatement().executeQuery("SELECT * FROM editora");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean cadastrarEditora(Editora editora) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "INSERT INTO editora(nome, cnpj, endereco_id) VALUES (?, ?, ?)");

            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getCnpj());
            
            if (editora.getEnderecoMatrizId() == 0) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, editora.getEnderecoMatrizId());
            }

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirEditora(int id) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "DELETE FROM editora WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarEditora(int id, Editora editora){
        try {
            ps = conexao.getConn().prepareStatement(
                """
                    UPDATE editora SET nome = ?, cnpj = ?, endereco_id = ?
                    WHERE id = ?         
                """
            );

            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getCnpj());

            if(editora.getEnderecoMatrizId() != 0){
                ps.setInt(3, editora.getEnderecoMatrizId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setInt(4, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
