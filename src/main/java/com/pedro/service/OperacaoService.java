package com.pedro.service;

import java.sql.ResultSet;
import com.pedro.dao.OperacaoDAO;
import com.pedro.models.Operacao;

public class OperacaoService {

    private OperacaoDAO operacaoDao;

    public OperacaoService() {
        this.operacaoDao = new OperacaoDAO();
    }

    public ResultSet listar() {
        return operacaoDao.listar();
    }

    public boolean inserir(Operacao operacao) {
        if (operacao.getFuncionarioLocacaoId() == 0) { 
            System.out.println("[!] O ID do Funcionário de Locação é obrigatório.");
            return false;
        }

        if (operacao.getPessoa() == null || operacao.getPessoa().getId() == 0) {
            System.out.println("[!] A Pessoa (Aluno/Professor/Funcionário) e seu ID são obrigatórios.");
            return false;
        }

        if (operacao.getExemplarId() == 0) {
            System.out.println("[!] O ID do Exemplar é obrigatório.");
            return false;
        }

        if (operacao.getTipoOperacao() == null) {
            System.out.println("[!] O Tipo da Operação é obrigatório.");
            return false;
        }

        if (operacao.getDataLocacao() == null) {
            System.out.println("[!] A Data de Locação é obrigatória.");
            return false;
        }

        if (operacao.getDataDevolucao() == null) { // Data de devolução prevista
            System.out.println("[!] A Data de Devolução (prevista) é obrigatória.");
            return false;
        }


        boolean succ = operacaoDao.inserir(operacao);
        if (!succ) {
            return false;
        }
        return true;
    }

    public boolean excluir(Operacao operacao) {
        if (operacao.getId() == 0) {
            return false;
        }

        boolean succ = operacaoDao.excluir(operacao.getId());
        if (!succ) {
            return false;
        }
        return true;
    }

    public boolean editar(Operacao operacao) {
        if (operacao.getId() == 0) {
            return false;
        }

        if (operacao.getFuncionarioLocacaoId() == 0) {
            System.out.println("[!] O ID do Funcionário de Locação é obrigatório.");
            return false;
        }

        if (operacao.getPessoa() == null || operacao.getPessoa().getId() == 0) {
            System.out.println("[!] A Pessoa (Aluno/Professor/Funcionário) e seu ID são obrigatórios.");
            return false;
        }

        if (operacao.getExemplarId() == 0) {
            System.out.println("[!] O ID do Exemplar é obrigatório.");
            return false;
        }

        if (operacao.getTipoOperacao() == null) {
            System.out.println("[!] O Tipo da Operação é obrigatório.");
            return false;
        }

        if (operacao.getDataLocacao() == null) {
            System.out.println("[!] A Data de Locação é obrigatória.");
            return false;
        }

        if (operacao.getDataDevolucao() == null) {
            System.out.println("[!] A Data de Devolução (prevista) é obrigatória.");
            return false;
        }

        boolean succ = operacaoDao.editar(operacao.getId(), operacao);
        if (!succ) {
            return false;
        }
        return true;
    }
}