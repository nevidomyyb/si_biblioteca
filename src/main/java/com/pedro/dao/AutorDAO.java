package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pedro.config.Conexao;
import com.pedro.models.Autor;
import java.sql.Date;

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

    public boolean inserir(Autor autor){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO autor(nome, data_nascimento, pseudonimo) VALUES (?, ?, ?)"
            );

            ps.setString(1, autor.getNome());
            ps.setDate(2, autor.getDataNascimento());
            ps.setString(3, autor.getPseudonimo());

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Autor inserido com sucesso!");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("[!] Falha ao inserir autor");
            return false;
        }
    }

    public boolean excluir(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM autor WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Autor excluído com sucesso!");
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Falha ao excluir autor!");
            return false;
        }
    }

    public boolean editar(int id, Autor autor){
        try{
            ps = conexao.getConn().prepareStatement(
                "UPDATE autor SET nome = ?, data_nascimento = ?, pseudonimo = ? WHERE id = ?"
            );

            ps.setString(1, autor.getNome());
            ps.setDate(2, new Date(autor.getDataNascimento().getTime()));
            ps.setString(3, autor.getPseudonimo());
            ps.setInt(4, id);

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Autor atualizado com sucesso!");
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("[!] Falha ao atualizar autor!");
            return false;
        }
    }
    
}
