package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pedro.config.Conexao;
import com.pedro.models.Genero;

public class GeneroDAO {
    private Conexao conexao;
    private PreparedStatement ps;

    public GeneroDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try{
            return conexao.getConn().createStatement().executeQuery("SELECT * FROM genero");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean cadastrarGenero(Genero genero){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO genero(genero) VALUES (?)"
            );

            ps.setString(1, genero.getGenero());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirGenero(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM genero WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarGenero(int id, Genero genero){
        try {
            ps = conexao.getConn().prepareStatement(
                """
                UPDATE genero SET genero = ? WHERE id = ? 
                """
            );

            ps.setString(1, genero.getGenero());
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
