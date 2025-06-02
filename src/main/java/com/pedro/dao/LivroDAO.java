package com.pedro.dao;

import com.pedro.config.Conexao;
import com.pedro.models.Livro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public LivroDAO(){
        conexao = new Conexao();
    }

    public void cadastrarLivro(Livro livro){
        String SQL = "INSERT INTO livro (titulo, editor_id, edicao, sinopse, genero_id, autor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getEditorId());
            ps.setString(3, livro.getEdicao());
            ps.setString(4, livro.getSinopse());
            ps.setInt(5, livro.getGeneroId());
            ps.setInt(6, livro.getAutorId());

            ps.executeUpdate();
            ps.close();

            System.out.println("Livro inserido com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao inserir livro.");
        }
    }

    public void removerLivro(Livro livro) {
        String SQL = "DELETE FROM livro WHERE id = ?";

        try {
            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, livro.getId()); // Pega o ID do livro para deletar

            ps.executeUpdate();
            ps.close();

            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao excluir livro.");
        }
    }

    public ResultSet consultarLivros() {
        try {
            return conexao.getConn()
                    .createStatement()
                    .executeQuery("SELECT * FROM livro");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void editarLivro(Livro livro) {
        try {
            String SQL = "UPDATE livro SET " +
                    "titulo = ?, editorId = ?, edicao = ?, sinopse = ?, generoId = ?, autorId = ? " +
                    "WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getEditorId());
            ps.setString(3, livro.getEdicao());
            ps.setString(4, livro.getSinopse());
            ps.setInt(5, livro.getGeneroId());
            ps.setInt(6, livro.getAutorId());
            ps.setInt(7, livro.getId());  // id do livro para o WHERE

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao editar livro");
        }
    }

}
