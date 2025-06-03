package com.pedro.service;

import com.pedro.dao.ExemplarDao;
import com.pedro.models.Exemplar;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExemplarService {

    private ExemplarDao exemplarDao;

    public ExemplarService() {
        this.exemplarDao = new ExemplarDao();
    }

    public void cadastrarExemplar(Exemplar exemplar) {
        if (exemplar.getLivroId() <= 0) {
            System.out.println("Erro: livroId inválido para o exemplar.");
            return;
        }
        exemplarDao.cadastrarExemplar(exemplar);
    }

    public void editarExemplar(Exemplar exemplar) {
        if (exemplar.getId() <= 0) {
            System.out.println("Erro: ID inválido para editar exemplar.");
            return;
        }
        exemplarDao.editarExemplar(exemplar);
    }

    public void excluirExemplar(int id) {
        Exemplar exemplar = new Exemplar(0); // Inicializa só para passar o ID
        exemplar.setId(id);
        exemplarDao.excluirExemplar(exemplar);
    }

    public List<Exemplar> listarExemplares() {
        List<Exemplar> lista = new ArrayList<>();
        try {
            ResultSet rs = exemplarDao.consultarExemplares();
            while (rs != null && rs.next()) {
                Exemplar exemplar = new Exemplar(rs.getInt("livroId"));
                exemplar.setId(rs.getInt("id"));
                lista.add(exemplar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

