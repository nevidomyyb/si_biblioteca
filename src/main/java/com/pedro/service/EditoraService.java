package com.pedro.service;

import java.sql.ResultSet;

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

    public ResultSet listar(){
        return editoraDao.listar();
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

    public boolean excluir(Editora editora){
        if(editora.getId() == 0){
            return false;
        }

        boolean succ = editoraDao.excluir(editora.getId());
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean editar(Editora editora){

        if(isNullOrEmpty(editora.getNome())){
            System.out.println("[!] O nome da Editora é obrigatório");
            return false;
        }

        if(isNullOrEmpty(editora.getCnpj())){
            System.out.println("[!] O cnpj da Editora é obrigatório");
            return false;
        }

        boolean succ = editoraDao.editar(editora.getId(), editora);
        if (!succ) {
            return false;
        }

        return true;
    }

}
