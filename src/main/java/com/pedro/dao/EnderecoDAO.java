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

    public boolean cadastrarEndereco(Endereco endereco){
        try {
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO endereco(rua, numero, bairro, cep, cidade, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCep());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());

            ps.executeUpdate();
            ps.close();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
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
            String query = "UPDATE endereco SET ";
            List<String> campos = new ArrayList<>();
            List<Object> valores = new ArrayList<>();

            if (validarString(endereco.getRua())) {
                campos.add("rua = ?");
                valores.add(endereco.getRua());
            }
            if (validarString(endereco.getNumero())) {
                campos.add("numero = ?");
                valores.add(endereco.getNumero());
            }
            if (validarString(endereco.getBairro())) {
                campos.add("bairro = ?");
                valores.add(endereco.getBairro());
            }
            if (validarString(endereco.getCep())) {
                campos.add("cep = ?");
                valores.add(endereco.getCep());
            }
            if (validarString(endereco.getCidade())) {
                campos.add("cidade = ?");
                valores.add(endereco.getCidade());
            }
            if (validarString(endereco.getEstado())) {
                campos.add("estado = ?");
                valores.add(endereco.getEstado());
            }

            if (campos.isEmpty()) {
                return false; // Nenhum campo válido para atualização
            }

            query += String.join(", ", campos) + " WHERE id = ?";
            
            ps = conexao.getConn().prepareStatement(query);
            int index = 1;
            for (Object valor : valores){
                ps.setObject(index++, valor);
            }
            ps.setInt(index, id);
                
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch(SQLException e) {
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

}
