package com.pedro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.dao.EnderecoDAO;
import com.pedro.models.Endereco;

public class EnderecoService {

    private EnderecoDAO enderecoDAO;

    public EnderecoService(){
        enderecoDAO = new EnderecoDAO();
    }

    public List<Endereco> listarEnderecos(){
        List<Endereco> enderecosList = null;
        try {
            ResultSet enderecos = enderecoDAO.listarEnderecos();
            enderecosList = new ArrayList<>();
            while (enderecos.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(enderecos.getInt("id"));
                endereco.setRua(enderecos.getString("rua"));
                endereco.setBairro(enderecos.getString("bairro"));
                endereco.setNumero(enderecos.getString("numero"));
                endereco.setCep(enderecos.getString("cep"));
                endereco.setCidade(enderecos.getString("cidade"));
                endereco.setEstado(enderecos.getString("estado"));
                enderecosList.add(endereco);
            }
            return enderecosList;
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return enderecosList;
    }

    public Endereco cadastrarEndereco(Endereco endereco){
        boolean valido = validarEndereco(endereco);
        if(!valido){
            System.err.println("[!] Não foi possível cadastrar esse endereço.");
            return null;
        }
        int generatedId = enderecoDAO.cadastrarEndereco(endereco);
        if(generatedId != 0){
            endereco.setId(generatedId);
            System.out.println("[!] Endereço cadastrado com sucesso");
            return endereco;
        }
        System.err.println("[!] Erro ao cadastrar endereço");
        return null;
    }

    public boolean editar(int id, Endereco endereco){
        if(endereco == null){
            System.err.println("[!] Endereço Inválido.");
            return false;
        }

        if(id == 0){
            System.err.println("[!] ID inválido.");
            return false;
        }

        return enderecoDAO.editarEndereco(id, endereco);
    }

    public boolean excluirEndereco(int id){
        if(id == 0){
            System.err.println("[!] ID Inválido.");
            return false;
        }

        return enderecoDAO.excluirEndereco(id);
    }

    public Endereco buscarEnderecoPorId(int id){
        if(id <= 0){
            System.err.println("[!] ID inválido.");
            return null;
        }

        Endereco endereco = enderecoDAO.buscarEndercoPorId(id);
        if (endereco == null){
            System.err.println("[!] Endereço não encontrado.");
        }
        return endereco;
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarEndereco(Endereco endereco){
        if (isNullOrEmpty(endereco.getRua())){
            System.err.println("[!] A rua do endereço é obrigatório.");
            return false;
        }

        if(isNullOrEmpty(endereco.getNumero())){
            System.err.println("[!] O Número do Endereço é obrigatório.");
            return false;
        }

        if(isNullOrEmpty(endereco.getBairro())){
            System.err.println("[!] O Bairro do endereço é obrigatório.");
            return false;
        }

        if(isNullOrEmpty(endereco.getCidade())){
            System.err.println("[!] A Cidade do Endereço é obrigatório.");
            return false;
        }

        if(isNullOrEmpty(endereco.getEstado())){
            System.err.println("[!] O Estado da Endereço é obrigatório.");
            return false;
        }
        return true;
    }


}
