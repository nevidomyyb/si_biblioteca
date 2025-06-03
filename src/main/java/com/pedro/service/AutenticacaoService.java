package com.pedro.service;


import com.pedro.dao.AutenticacaoDAO;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;
import com.pedro.models.Professor;

public class AutenticacaoService {

    AutenticacaoDAO dao;

    public AutenticacaoService() {
        dao = new AutenticacaoDAO();
    }

    public String autenticar(String login, String senha) {

        if (login.isEmpty() || senha.isEmpty()) {
            return "";
        }
        return dao.autenticar(login, senha);

    }

}
