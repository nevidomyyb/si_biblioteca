package com.pedro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.config.Conexao;
import com.pedro.models.Endereco;

public class EnderecoDAO {
    
    private Conexao conexao;
    private PreparedStatement ps;

    public EnderecoDAO(){
        conexao = new Conexao();
    }

    public int cadastrarEndereco(Endereco endereco){
        try {
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO endereco(rua, numero, bairro, cep, cidade, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            
            if(validarString(endereco.getCep())){
                ps.setString(4, endereco.getCep());
            } else {
                ps.setNull(4, java.sql.Types.NULL);
            }

            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                ps.close();
                return generatedId;
            }

            return 0;
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet listarEnderecos(){
        try{
            return conexao.getConn().createStatement().executeQuery("SELECT * FROM endereco");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean editarEndereco(int id, Endereco endereco){
        try {
            ps = conexao.getConn().prepareStatement(
                """
                    UPDATE endereco SET rua = ?, numero = ?,
                    bairro = ?, cep  = ?, cidade = ?,
                    estado = ? WHERE id = ?
                """
            );

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            
            if(validarString(endereco.getCep())){
                ps.setString(4, endereco.getCep());
            } else {
                ps.setNull(4, java.sql.Types.NULL);
            }

            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setInt(7, id);

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

    public boolean excluirEndereco(int id){
        try {
            ps = conexao.getConn().prepareStatement("DELETE FROM endereco WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Endereco buscarEndercoPorId(int id){
        try {
            ps = conexao.getConn().prepareStatement(
                "SELECT * FROM endereco WHERE id = ?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Endereco endereco = new Endereco(
                    rs.getString("rua"),
                    rs.getString("bairro"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado")
                );
                endereco.setId(id);
                if(validarString(rs.getString("cep"))){
                    endereco.setCep(rs.getString("cep"));
                }
                return endereco;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
