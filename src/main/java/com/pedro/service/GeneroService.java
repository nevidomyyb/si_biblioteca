package com.pedro.service;

import java.sql.ResultSet;

import com.pedro.dao.GeneroDAO;
import com.pedro.models.Genero;

public class GeneroService {
    
    private GeneroDAO generoDao;

    public GeneroService(){
        generoDao = new GeneroDAO();
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public ResultSet listar(){
        return generoDao.listar();
    }

    public boolean inserir(Genero genero){

        if(isNullOrEmpty(genero.getGenero())){
            System.out.println("[!] O gênero é obrigatório");
            return false;
        }

        boolean succ = generoDao.inserir(genero);
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean excluir(Genero genero){
        if(genero.getId() == 0){
            return false;
        }

        boolean succ = generoDao.excluir(genero.getId());
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean editar(Genero genero) {
        if(isNullOrEmpty(genero.getGenero())){
            System.out.println("[!] O gênero é obrigatório");
            return false;
        }

        boolean succ = generoDao.editar(genero.getId(), genero);
        if (!succ) {
            return false;
        }

        return true;
    }

}
