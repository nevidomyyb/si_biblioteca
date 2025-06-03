package com.pedro.service;

import com.pedro.dao.LivroDAO;
import com.pedro.models.Livro;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrarLivro(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            System.out.println("Erro: Título do livro não pode ser vazio.");
            return;
        }
        livroDAO.cadastrarLivro(livro);
    }

    public void editarLivro(Livro livro) {
        if (livro.getId() <= 0) {
            System.out.println("Erro: ID do livro inválido.");
            return;
        }
        livroDAO.editarLivro(livro);
    }

    public void removerLivro(int id) {
        Livro livro = new Livro("", 0, "", "", 0, 0);
        livro.setId(id);
        livroDAO.removerLivro(livro);
    }

    public List<Livro> listarLivros() {
        List<Livro> lista = new ArrayList<>();
        try {
            ResultSet rs = livroDAO.consultarLivros();
            while (rs != null && rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getInt("editor_id"),
                        rs.getString("edicao"),
                        rs.getString("sinopse"),
                        rs.getInt("genero_id"),
                        rs.getInt("autor_id")
                );
                livro.setId(rs.getInt("id"));
                lista.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
