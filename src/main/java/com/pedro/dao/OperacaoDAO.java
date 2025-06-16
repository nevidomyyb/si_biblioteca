package com.pedro.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.pedro.config.Conexao;
import com.pedro.models.Operacao;
import com.pedro.models.TipoOperacao;

public class OperacaoDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public OperacaoDAO() {
        conexao = new Conexao();
    }

    public ResultSet listar() {
        try {
            return conexao.getConn().createStatement().executeQuery(
                    "SELECT * FROM operacao");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean locacao(Operacao operacao) {
        try {
            String sql = "INSERT INTO operacao (funcionario_locacao_id, aluno_id, professor_id, funcionario_id, exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexao.getConn().prepareStatement(sql);

            ps.setInt(1, operacao.getFuncionarioLocacaoId());
            if (operacao.getTipoUsuario().equals("1")) {
                ps.setInt(2, operacao.getLocador());
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
            } else if (operacao.getTipoUsuario().equals("2")) {
                ps.setNull(2, Types.INTEGER);
                ps.setInt(3, operacao.getLocador());
                ps.setNull(4, Types.INTEGER);
            } else if (operacao.getTipoUsuario().equals("3")) {
                ps.setNull(2, Types.INTEGER);
                ps.setNull(3, Types.INTEGER);
                ps.setInt(4, operacao.getLocador());
            }

            ps.setInt(5, operacao.getExemplarId());
            ps.setString(6, operacao.getTipoOperacao().toString());
            ps.setDate(7, operacao.getDataLocacao());
            ps.setDate(8, operacao.getDataDevolucao());
            ps.setNull(9, Types.DATE);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirOperacao(int id) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "DELETE FROM operacao WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarOperacao(int id, Operacao operacao) {
        try {
            ps = conexao.getConn().prepareStatement(
                """
                UPDATE operacao SET funcionario_locacao_id = ?,
                funcionario_devolucao_id = ?,
                aluno_id = ?, professor_id = ?,
                funcionario_id = ?,
                exemplar_id = ?, tipo_operacao = ?,
                data_locacao = ?, data_devolucao = ?,
                data_devolvido = ?
                WHERE id = ?
                """
            );

            ps.setInt(1, operacao.getFuncionarioLocacaoId());
            
            if(operacao.getFuncionarioDevolucaoId() != 0){
                ps.setInt(2, operacao.getFuncionarioDevolucaoId());
            } else {
               ps.setNull(2, java.sql.Types.INTEGER);
            }

            if(operacao.getTipoUsuario() != null){
                if(operacao.getTipoUsuario().equals("1")){
                    ps.setInt(3, operacao.getLocador());
                    ps.setNull(4, java.sql.Types.INTEGER);
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else if(operacao.getTipoUsuario().equals("2")){
                    ps.setNull(3, java.sql.Types.INTEGER);
                    ps.setInt(4, operacao.getLocador());
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else if(operacao.getTipoUsuario().equals("3")){
                    ps.setNull(3, java.sql.Types.INTEGER);
                    ps.setNull(4, java.sql.Types.INTEGER);
                    ps.setInt(5, operacao.getLocador());
                }
            }

            if(operacao.getExemplarId() != 0){
                ps.setInt(6, operacao.getExemplarId());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setString(7, operacao.getTipoOperacao().toString());
            ps.setDate(8, operacao.getDataLocacao());
            ps.setDate(9, operacao.getDataDevolucao());

            if (operacao.getDataDevolvido() != null) {
                ps.setDate(10, operacao.getDataDevolvido());
            } else {
                ps.setNull(10, java.sql.Types.DATE);
            }

            ps.setInt(11, id);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean devolucao(int id, int funcionarioDevolucao, Date dataDevolvido) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "UPDATE operacao SET tipo_operacao = ?, funcionario_devolucao_id = ?, data_devolvido = ? WHERE id = ?");

            ps.setString(1, TipoOperacao.DEVOLUCAO.toString());
            ps.setInt(2, funcionarioDevolucao);
            ps.setDate(3, dataDevolvido);
            ps.setInt(4, id);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isExemplarLocado(int idExemplar) {
        try {
            ps = conexao.getConn().prepareStatement(
                    "SELECT data_devolvido FROM operacao WHERE exemplar_id = ? AND tipo_operacao = ?");
            ps.setInt(1, idExemplar);
            ps.setString(2, TipoOperacao.LOCACAO.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("data_devolvido") == null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean usuarioPossuiAtraso(int idUsuario, String tipoUsuario) {
        String colunaUsuario;

        if ("1".equals(tipoUsuario)) {
            colunaUsuario = "aluno_id = ?";
        } else if ("2".equals(tipoUsuario)) {
            colunaUsuario = "professor_id = ?";
        } else if ("3".equals(tipoUsuario)) {
            colunaUsuario = "funcionario_id = ?";
        } else {
            return false;
        }

        String sql = "SELECT data_devolucao FROM operacao WHERE " + colunaUsuario
                + " AND tipo_operacao = ? AND data_devolvido IS NULL AND data_devolucao < CURDATE()";

        try {
            ps = conexao.getConn().prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2, TipoOperacao.LOCACAO.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
