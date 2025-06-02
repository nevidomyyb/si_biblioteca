package com.pedro.service;

import com.pedro.dao.LivroDAO;
import com.pedro.models.Livro;

public class LivroService {

    private LivroDAO livroDAO;

    public LivroService(){}

    public void InserirLivro(Livro livro){
        livroDAO.cadastrarLivro(livro);
    }
}
