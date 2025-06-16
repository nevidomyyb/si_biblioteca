package com.pedro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.dao.EditoraDAO;
import com.pedro.models.Editora;

public class EditoraService {
    
    private EditoraDAO editoraDao;

    public EditoraService(){
        editoraDao = new EditoraDAO();
    }

    public List<Editora> listar(){
        List<Editora> editoraList = null;
        try {
            ResultSet editoras = editoraDao.listarEditoras();
            editoraList = new ArrayList<Editora>();
            while (editoras.next()) {
                Editora editora = new Editora();
                editora.setId(editoras.getInt("id"));
                editora.setNome(editoras.getString("nome"));
                editora.setCnpj(editoras.getString("cnpj"));
                editoraList.add(editora);
            }
            return editoraList;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return editoraList;
    }

    public boolean cadastrarEditora(Editora editora){
        boolean valido = validarEditora(editora);
        if(!valido){
            System.err.println("[!] Não foi possível cadastrar essa editora");
            return false;
        }
        return editoraDao.cadastrarEditora(editora);
    }

    public boolean excluirEditora(int id){
        if(id <= 0){
            System.err.println("[!] ID Inválido");
            return false;
        }

        return editoraDao.excluirEditora(id);
    }

    public boolean editarEditora(int id, Editora editora){

        if(editora == null){
            System.err.println("[!] Editora Inválida");
            return false;
        }

        if(id <= 0){
            System.err.println("[!] ID Inválido");
            return false;
        }

        return editoraDao.editarEditora(id, editora);
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarEditora(Editora editora){
        if(isNullOrEmpty(editora.getNome())){
            System.err.println("[!] O nome da editora é obrigatório");
            return false;
        }

        if(isNullOrEmpty(editora.getCnpj())){
            System.err.println("[!] O CNPJ da editora é obrigatório");
        }

        return true;
    }

}
