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

    public EditoraDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try{
            return conexao.getConn().createStatement().executeQuery("SELECT * FROM editora");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean inserir(Editora editora){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO editora(nome, cnpj, endereco_id) VALUES (?, ?, ?)"
            );

            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getCnpj());
            if (editora.getEnderecoMatrizId() == 0) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, editora.getEnderecoMatrizId());
            }

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Editora inserida com sucesso!");
            return true;
        } catch (SQLException e){
            System.out.println("[!] Falha ao inserir editora");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM editora WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Editora excluída com sucesso!");
            return true;
        } catch (SQLException e){
            System.out.println("[!] Falha ao tentar excluir editora");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(int id, Editora editora){
        try{
            ps = conexao.getConn().prepareStatement(
                "UPDATE editora SET nome = ?, cnpj = ?, endereco_id = ? WHERE id = ?"
            );
            
            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getCnpj());
            ps.setInt(3, editora.getEnderecoMatrizId());
            ps.setInt(4, id);

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Editora atualizada com sucesso!");
            return true;
        } catch(SQLException e){
            System.out.println("[!] Falha ao atualizar editora!");
            e.printStackTrace();
            return false;
        }

    }
}
