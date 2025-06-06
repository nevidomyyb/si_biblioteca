package com.pedro.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

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

    public boolean inserir(Operacao operacao, String tipoUsuario) {
        if (operacao.getFuncionarioLocacaoId() == 0) { 
            System.out.println("[!] O ID do Funcionário de Locação é obrigatório.");
            return false;
        }

        if (tipoUsuario == null || tipoUsuario == ""){
            System.out.println("[!] Erro ao pegar o tipo de usuário.");
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


        boolean succ = operacaoDao.inserir(operacao, tipoUsuario);
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

    public boolean editar(Operacao operacao, String tipoUsuario) {
        if (operacao.getId() == 0) {
            return false;
        }

        if (operacao.getFuncionarioLocacaoId() == 0) {
            System.out.println("[!] O ID do Funcionário de Locação é obrigatório.");
            return false;
        }

        if (tipoUsuario == null || tipoUsuario == ""){
            System.out.println("[!] Erro ao pegar o tipo de usuário.");
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

        boolean succ = operacaoDao.editar(operacao.getId(), operacao, tipoUsuario);
        if (!succ) {
            return false;
        }
        return true;
    }

    public boolean fecharOperacao(int id, int funcionarioDevolucao, Date dataDevolvido){
        if(funcionarioDevolucao == 0){
            System.out.println("[!] O ID do Funcionário responsável pela devolução é obrigatório");
        }
        
        operacaoDao.fecharOperacao(id, funcionarioDevolucao, dataDevolvido);
        return true;
         
    }
}