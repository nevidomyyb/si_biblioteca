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

    public boolean cadastrarExemplar(Exemplar exemplar) {
        if (exemplar.getLivroId() <= 0) {
            System.out.println("ID Inválido.");
            return false;
        }

        boolean succ = exemplarDao.cadastrarExemplar(exemplar);
        if(!succ) {
            return false;
        }
        return true;
    }

    public boolean editarExemplar(int id, Exemplar exemplar) {
        if (id <= 0) {
            System.out.println("ID Inválido.");
            return false;
        }

        return exemplarDao.editarExemplar(id, exemplar);
    }

    public boolean excluirExemplar(int id) {
        if(id <= 0){
            System.err.println("[!] ID Inválido");
        }
        return exemplarDao.excluirExemplar(id);
    }

    public List<Exemplar> listarExemplares() {
        List<Exemplar> lista = new ArrayList<>();
        try {
            ResultSet rs = exemplarDao.consultarExemplares();
            while (rs != null && rs.next()) {
                Exemplar exemplar = new Exemplar(rs.getInt("livro_id"));
                exemplar.setId(rs.getInt("id"));
                lista.add(exemplar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

