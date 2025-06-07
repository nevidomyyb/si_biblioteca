package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String query = "UPDATE genero SET ";
            List<String> campos = new ArrayList<String>();
            List<Object> valores = new ArrayList<Object>();

            if(validarString(genero.getGenero())){
                campos.add("genero = ?");
                valores.add(genero.getGenero());
            }

            if(campos.isEmpty()){
                return false;
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

    private boolean validarString(String str){
        if(str != null && !str.isEmpty()){
            return true;
        }
        return false;
    }

}
