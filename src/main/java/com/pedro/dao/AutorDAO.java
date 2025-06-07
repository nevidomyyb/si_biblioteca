package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.config.Conexao;
import com.pedro.models.Autor;

public class AutorDAO {
    
    private Conexao conexao;
    private PreparedStatement ps;

    public AutorDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try{
            return conexao.getConn().createStatement()
                .executeQuery("SELECT * FROM autor");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean cadastrarAutor(Autor autor){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO autor(nome, data_nascimento, pseudonimo) VALUES (?, ?, ?)"
            );

            ps.setString(1, autor.getNome());
            ps.setDate(2, autor.getDataNascimento());
            ps.setString(3, autor.getPseudonimo());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirAutor(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM autor WHERE id = ?"
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

    public boolean editarAutor(int id, Autor autor){
        try {
            String query = "UPDATE autor SET ";
            List<String> campos = new ArrayList<>();
            List<Object> valores = new ArrayList<>();

            if(validarString(autor.getNome())){
                campos.add("nome = ?");
                valores.add(autor.getNome());
            }

            if(validarString(autor.getPseudonimo())){
                campos.add("pseudonimo = ?");
                valores.add(autor.getPseudonimo());
            }

            if(autor.getDataNascimento() != null){
                campos.add("data_nascimento = ?");
                valores.add(autor.getDataNascimento());
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

    private boolean validarString(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }
    
}
