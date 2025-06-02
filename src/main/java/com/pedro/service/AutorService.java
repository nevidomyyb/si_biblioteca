package com.pedro.service;

import java.sql.ResultSet;

import com.pedro.dao.AutorDAO;
import com.pedro.models.Autor;

public class AutorService {
    
    private AutorDAO autorDao;

    public AutorService(){
        autorDao = new AutorDAO();
    }

    public ResultSet listar(){
        return autorDao.listar();
    }


    public boolean inserir(Autor autor) {

        if (isNullOrEmpty(autor.getNome())) {
            System.err.println("[!] Nome do autor é obrigatório.");
            return false;
        }

        if (isNullOrEmpty(autor.getPseudonimo())) {
            System.err.println("[!] Pseudônimo do autor é obrigatório.");
            return false;
        }

        if (autor.getDataNascimento() == null) {
            System.err.println("[!] Data de nascimento do autor é obrigatória.");
            return false;
        }


        boolean succ = autorDao.inserir(autor);
        if (!succ) {
            return false;
        }

        return true;

    }


    public boolean excluir(Autor autor){
        if (autor.getId() == 0){
            return false;
        }

        boolean succ = autorDao.excluir(autor.getId());
        if (!succ) {
            return false;
        }

        return true;

    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public boolean editar(Autor autor){
        
        if (isNullOrEmpty(autor.getNome())) {
            System.err.println("[!] Nome do autor é obrigatório.");
            return false;
        }

        if (isNullOrEmpty(autor.getPseudonimo())) {
            System.err.println("[!] Pseudônimo do autor é obrigatório.");
            return false;
        }

        if (autor.getDataNascimento() == null) {
            System.err.println("[!] Data de nascimento do autor é obrigatória.");
            return false;
        }

        boolean succ = autorDao.editar(autor.getId(), autor);
        if(!succ){
            return false;
        }

        return true;
    }

}
