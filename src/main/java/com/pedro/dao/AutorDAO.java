package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

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

            if(autor.getDataNascimento() != null){
                ps.setDate(2, autor.getDataNascimento());
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if(validarString(autor.getPseudonimo())){
                ps.setString(3, autor.getPseudonimo());
            } else {
                ps.setNull(3, java.sql.Types.NULL);
            }
            
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
            ps = conexao.getConn().prepareStatement(
                """
                   UPDATE autor SET nome = ?, data_nascimento = ?,
                   pseudonimo = ? WHERE id = ?  
                """
            );
            ps.setString(1, autor.getNome());
            
            if(autor.getDataNascimento() != null){
                ps.setDate(2, autor.getDataNascimento());
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if(validarString(autor.getPseudonimo())){
                ps.setString(3, autor.getPseudonimo());
            } else {
                ps.setNull(3, java.sql.Types.NULL);
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

    public Autor buscarAutorPorId(int id){
        try {
            ps = conexao.getConn().prepareStatement(
                "SELECT * FROM autor WHERE id = ?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Autor autor = new Autor();
                autor.setNome(rs.getString("nome"));
                autor.setDataNascimento(rs.getDate("data_nascimento"));
                autor.setPseudonimo(rs.getString("pseudonimo"));
                autor.setId(id);
                return autor;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean validarString(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }
}
