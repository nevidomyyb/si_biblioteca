package com.pedro.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pedro.dao.OperacaoDAO;
import com.pedro.models.Operacao;
import com.pedro.models.TipoOperacao;

public class OperacaoService {

    private OperacaoDAO operacaoDao;

    public OperacaoService() {
        this.operacaoDao = new OperacaoDAO();
    }

    public List<Operacao> listar() {
        List<Operacao> operacaoList = null;
        try {
            ResultSet operacoes = operacaoDao.listar();
            operacaoList = new ArrayList<Operacao>();
            while (operacoes.next()) {
                Operacao operacao = new Operacao();
                operacao.setId(operacoes.getInt("id"));
                operacao.setFuncionarioLocacaoId(operacoes.getInt("funcionario_locacao_id"));
                operacao.setFuncionarioLocacaoId(operacoes.getInt("funcionario_devolucao_id"));

                if (operacoes.getInt("aluno_id") != 0) {
                    operacao.setTipoUsuario("Aluno");
                    operacao.setLocador(operacoes.getInt("aluno_id"));
                } else if (operacoes.getInt("professor_id") != 0) {
                    operacao.setTipoUsuario("Professor");
                    operacao.setLocador(operacoes.getInt("professor_id"));
                } else if (operacoes.getInt("funcionario_id") != 0) {
                    operacao.setTipoUsuario("Funcionário");
                    operacao.setLocador(operacoes.getInt("funcionario_id"));
                }

                operacao.setExemplarId(operacoes.getInt("exemplar_id"));

                if (operacoes.getString("tipo_operacao").equals("LOCACAO")) {
                    operacao.setTipoOperacao(TipoOperacao.LOCACAO);
                } else if (operacoes.getString("tipo_operacao").equals("DEVOLUCAO")) {
                    operacao.setTipoOperacao(TipoOperacao.DEVOLUCAO);
                }

                operacao.setDataLocacao(operacoes.getDate("data_locacao"));
                operacao.setDataDevolucao(operacoes.getDate("data_devolucao"));
                operacao.setDataDevolvido(operacoes.getDate("data_devolvido"));
                operacaoList.add(operacao);
            }
            return operacaoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operacaoList;
    }

    public boolean locacao(Operacao operacao) {
        boolean valido = valido(operacao);
        if (!valido) {
            System.err.println("[!] Não foi possível cadastrar operação");
            return false;
        }

        if (operacaoDao.isExemplarLocado(operacao.getExemplarId())) {
            System.err.println("[!] Erro: o exemplar já está locado.");
            return false;
        }

        operacaoDao.locacao(operacao);
        System.out.println("[!] Operação realizada com sucesso.");
        return true;
    }

    public boolean excluirOperacao(int id) {
        if (id <= 0) {
            System.err.println("[!] ID Inválido");
            return false;
        }

        operacaoDao.excluirOperacao(id);
        System.out.println("[!] Operação excluída.");
        return true;
    }

    public boolean editar(int id, Operacao operacao, String tipoUsuario) {
        if (operacao == null) {
            System.err.println("[!] Operação Inválida.");
            return false;
        }

        if (id <= 0) {
            System.err.println("[!] ID Inválido");
            return false;
        }

        operacaoDao.editarOperacao(id, operacao);
        System.out.println("[!] Operação editada com sucesso");
        return true;
    }

    public boolean devolucao(int id, int funcionarioDevolucao, Date dataDevolvido) {
        if (funcionarioDevolucao == 0) {
            System.out.println("[!] O ID do Funcionário responsável pela devolução é obrigatório");
        }

        operacaoDao.devolucao(id, funcionarioDevolucao, dataDevolvido);
        return true;

    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean valido(Operacao operacao) {
        if (operacao.getFuncionarioLocacaoId() == 0) {
            System.err.println("[!] ID do Funcionário responsável pela locação é obrigatório.");
            return false;
        }

        if (operacao.getLocador() == 0) {
            System.err.println("[!] O ID do Locador é obrigatório.");
            return false;
        }

        if (operacao.getExemplarId() == 0) {
            System.err.println("[!] O ID do Exemplar é obrigatório.");
            return false;
        }

        if (isNullOrEmpty(operacao.getTipoOperacao().toString())) {
            System.err.println("[!] O Tipo de Operação é obrigatório.");
            return false;
        }

        if (isNullOrEmpty(operacao.getDataLocacao().toString())) {
            System.err.println("[!] A Data de Locação é obrigatória.");
            return false;
        }

        if (isNullOrEmpty(operacao.getDataDevolucao().toString())) {
            System.err.println("[! A Data de Devolução é obrigatória.");
            return false;
        }

        return true;
    }

    public boolean usuarioPossuiAtrasos(int id, String tipoUsuario) {
        if (operacaoDao.usuarioPossuiAtraso(id, tipoUsuario)) {
            System.err.println("[!] Nâo foi possível realizar a locação, pois o usuário possui pendências.");
            return false; 
        }
        return true; 
    }
}