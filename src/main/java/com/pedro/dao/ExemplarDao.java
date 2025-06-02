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

    public void cadastrarExemplar(Exemplar exemplar) {
        try {
            String SQL = "INSERT INTO exemplar(livroId) VALUES (?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, exemplar.getLivroId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao inserir exemplar");
        }
    }

    public void editarExemplar(Exemplar exemplar) {
        try {
            String SQL = "UPDATE exemplar SET livroId = ? WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, exemplar.getLivroId());
            ps.setInt(2, exemplar.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao editar exemplar");
        }
    }

    public void excluirExemplar(Exemplar exemplar) {
        try {
            String SQL = "DELETE FROM exemplar WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, exemplar.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao excluir exemplar");
        }
    }

}
