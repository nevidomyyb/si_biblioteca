package com.pedro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Genero> listar(){
        List<Genero> generoList = null;
        try {
            ResultSet generos = generoDao.listar();
            generoList = new ArrayList<Genero>();
            while(generos.next()) {
                Genero genero = new Genero();
                genero.setId(generos.getInt("id"));
                genero.setGenero(generos.getString("genero"));

                generoList.add(genero);
            }
            return generoList;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return generoList;
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

    public boolean excluir(int id){
        if(id == 0){
            return false;
        }

        boolean succ = generoDao.excluir(id);
        if (!succ) {
            return false;
        }

        return true;
    }

    public boolean editar(int id, Genero genero) {
        if(isNullOrEmpty(genero.getGenero())){
            System.out.println("[!] O gênero é obrigatório");
            return false;
        }

        boolean succ = generoDao.editar(id, genero);
        if (!succ) {
            return false;
        }

        return true;
    }

}
