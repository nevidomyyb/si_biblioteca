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


    public boolean excluir(int id){
        if (id == 0){
            return false;
        }

        boolean succ = autorDao.excluir(id);
        if (!succ) {
            return false;
        }

        return true;

    }

    private static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public boolean editar(int id, Autor autor){
        
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

        boolean succ = autorDao.editar(id, autor);
        if(!succ){
            return false;
        }

        return true;
    }

}
