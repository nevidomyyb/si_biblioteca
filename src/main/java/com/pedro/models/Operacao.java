package com.pedro.models;

import java.sql.Date;

public class Operacao {

    private int id;
    private int funcionarioLocacaoId;
    private int funcionarioDevolucaoId;
    private int locador;
    private int exemplarId;
    private TipoOperacao tipoOperacao;
    private Date dataLocacao;
    private Date dataDevolucao;
    private Date dataDevolvido;
    private String tipoUsuario;

    public Operacao() {
    }

    public Operacao(
            int funcionarioLocacaoId, int locador,
            int exemplarId, TipoOperacao tipoOperacao, Date dataLocacao,
            Date dataDevolucao) {
        this.funcionarioLocacaoId = funcionarioLocacaoId;
        this.locador = locador;
        this.exemplarId = exemplarId;
        this.tipoOperacao = tipoOperacao;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuncionarioLocacaoId() {
        return funcionarioLocacaoId;
    }

    public void setFuncionarioLocacaoId(int funcionarioLocacaoId) {
        this.funcionarioLocacaoId = funcionarioLocacaoId;
    }

    public int getFuncionarioDevolucaoId() {
        return funcionarioDevolucaoId;
    }

    public void setFuncionarioDevolucaoId(int funcionarioDevolucaoId) {
        this.funcionarioDevolucaoId = funcionarioDevolucaoId;
    }

    public int getLocador() {
        return this.locador;
    }

    public void setLocador(int locador) {
        this.locador = locador;
    }

    public int getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(int exemplarId) {
        this.exemplarId = exemplarId;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataDevolvido() {
        return dataDevolvido;
    }

    public void setDataDevolvido(Date dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
