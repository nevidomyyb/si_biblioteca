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

    public boolean cadastrarGenero(Genero genero){
        boolean valido = validarGenero(genero);
        if(!valido){
            System.err.println("[!] Não foi possível cadastrar esse gênero");
            return false;
        }
        return generoDao.cadastrarGenero(genero);
    }

    public boolean excluirGenero(int id){
        if(id <= 0){
            System.err.println("[!] ID Inválido");
            return false;
        }
        return generoDao.excluirGenero(id);
    }

    public boolean editarGenero(int id, Genero genero) {
        if(genero == null){
            System.err.println("[!] Gênero Inválido");
            return false;
        }

        if(id <= 0){
            System.err.println("ID Inválido");
            return false;
        }

        return generoDao.editarGenero(id, genero);
    }
    
    private static boolean validarGenero(Genero genero){
        if(isNullOrEmpty(genero.getGenero())){
            System.err.println("[!] A descrição do gênero é obrigatória");
            return false;
        }
        return true;
    }

}
