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

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public List<Editora> listar(){
        List<Editora> editoraList = null;
        try {
            ResultSet editoras = editoraDao.listar();
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

        return null;
    }

    public boolean inserir(Editora editora){
        if(isNullOrEmpty(editora.getNome())){
            System.out.println("[!] O nome da Editora é obrigatório");
            return false;
        }

        if(isNullOrEmpty(editora.getCnpj())){
            System.out.println("[!] O cnpj da Editora é obrigatório");
            return false;
        }

        boolean succ = editoraDao.inserir(editora);
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean excluir(int id){
        if(id == 0){
            return false;
        }

        boolean succ = editoraDao.excluir(id);
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean editar(int id, Editora editora){

        if(isNullOrEmpty(editora.getNome())){
            System.out.println("[!] O nome da Editora é obrigatório");
            return false;
        }

        if(isNullOrEmpty(editora.getCnpj())){
            System.out.println("[!] O cnpj da Editora é obrigatório");
            return false;
        }

        boolean succ = editoraDao.editar(id, editora);
        if (!succ) {
            return false;
        }

        return true;
    }

}
