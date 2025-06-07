package com.pedro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.dao.AutorDAO;
import com.pedro.models.Autor;

public class AutorService {
    
    private AutorDAO autorDao;

    public AutorService(){
        autorDao = new AutorDAO();
    }

    public List<Autor> listar(){
        List<Autor> autorList = null;
        try{
            ResultSet autores = autorDao.listar();
            autorList = new ArrayList<Autor>();
            while(autores.next()){
                Autor autor = new Autor();
                autor.setId(autores.getInt("id"));
                autor.setNome(autores.getString("nome"));
                autor.setDataNascimento(autores.getDate("data_nascimento"));
                autor.setPseudonimo(autores.getString("pseudonimo"));
                autorList.add(autor);
            }
            return autorList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return autorList;
    }


    public boolean cadastrarAutor(Autor autor) {
        boolean valido = validarAutor(autor);
        if(!valido){
            System.err.println("[!] Não foi possível cadastrar essa editora");
            return false;
        }
        autorDao.cadastrarAutor(autor);
        System.out.println("[!] Autor cadastrado com sucesso.");
        return true;
    }


    public boolean excluir(int id){
        if(id <= 0){
            System.err.println("Id Inválido");
            return false;
        }

        autorDao.excluirAutor(id);
        System.out.println("[!] Autor excluído.");
        return true;
    }

    public boolean editar(int id, Autor autor){
        if(autor == null){
            System.err.println("Autor Inválido");
            return false;
        }

        if(id <= 0){
            System.err.println("[!] ID Inválido");
            return false;
        }

        autorDao.editarAutor(id, autor);
        System.out.println("[!] Autor editado com sucesso.");
        return true;
    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static boolean validarAutor(Autor autor){
        if(isNullOrEmpty(autor.getNome())){
            System.err.println("[!] O nome do autor é obrigatório");
            return false;
        }

        return true;
    }

}
