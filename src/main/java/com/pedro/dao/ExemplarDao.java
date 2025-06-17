package com.pedro.dao;

import com.pedro.models.Exemplar;
import com.pedro.config.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemplarDao {

    private Conexao conexao;
    private PreparedStatement ps;

    public ExemplarDao() {
        conexao = new Conexao();
    }

    public ResultSet consultarExemplares() {
        try {
            return conexao.getConn()
                    .createStatement()
                    .executeQuery("SELECT * FROM exemplar");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean cadastrarExemplar(Exemplar exemplar) {
        try {
            String SQL = "INSERT INTO exemplar(livro_id) VALUES (?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, exemplar.getLivroId());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean editarExemplar(int id, Exemplar exemplar) {
        try {
            String SQL = "UPDATE exemplar SET livro_id = ? WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, exemplar.getLivroId());
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluirExemplar(int id) {
        try {
            String SQL = "DELETE FROM exemplar WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }
    }

}
