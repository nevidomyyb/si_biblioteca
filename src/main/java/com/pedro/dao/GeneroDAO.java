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

    public boolean inserir(Genero genero){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO genero(genero) VALUES (?)"
            );

            ps.setString(1, genero.getGenero());
            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Gênero inserido com sucesso!");
            return true;
        } catch(SQLException e){
            System.out.println("[!] Falha ao inserir gênero!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM genero WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Genero excluído com sucesso");
            return true;
        } catch(SQLException e){
            System.out.println("[!] Falha ao excluir gênero");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(int id, Genero genero){
        try{
            ps = conexao.getConn().prepareStatement(
                "UPDATE genero SET genero = ? WHERE id = ?"
            );

            ps.setString(1, genero.getGenero());
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Gênero atualizado com sucesso!");
            return true;
        } catch(SQLException e){
            System.out.println("[!] Falha ao atualizar gênero");
            e.printStackTrace();
            return false;
        }
    }

}
