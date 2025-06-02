package com.pedro.dao;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.pedro.config.Conexao;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;
import com.pedro.models.Operacao;
import com.pedro.models.Professor;

public class OperacaoDAO {
    
    private Conexao conexao;
    private PreparedStatement ps;

    public OperacaoDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try{
            return conexao.getConn().createStatement().executeQuery(
                "SELECT * FROM operacao"
            );
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean inserir(Operacao operacao){
        try{
            ps = conexao.getConn().prepareStatement(
                "INSERT INTO operacao(funcionario_locacao_id, funcionario_devolucao_id, aluno_id, professor_id, funcionario_id, " +
                "exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido"
            );

            ps.setInt(1, operacao.getFuncionarioLocacaoId());
            ps.setInt(2, operacao.getFuncionarioDevolucaoId());

            if(operacao.getPessoa() instanceof Aluno){
                ps.setInt(3, operacao.getPessoa().getId());
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.INTEGER);
            } else if(operacao.getPessoa() instanceof Professor){
                ps.setInt(4, operacao.getPessoa().getId());
                ps.setNull(3, Types.INTEGER);
                ps.setNull(5, Types.INTEGER);
            } else if(operacao.getPessoa() instanceof Funcionario){
                ps.setInt(5, operacao.getPessoa().getId());
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
            }

            ps.setInt(6, operacao.getExemplarId());
            ps.setString(7, operacao.getTipoOperacao().name());
            ps.setDate(8, new Date(operacao.getDataLocacao().getTime()));
            ps.setDate(9, new Date(operacao.getDataDevolucao().getTime()));
           if (operacao.getDataDevolvido() == null) {
                ps.setNull(9, Types.DATE);
            } else {
                ps.setDate(9, new java.sql.Date(operacao.getDataDevolvido().getTime()));
            }

            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Operação realizada com sucesso!");
            return true;
        } catch (SQLException e){
            System.out.println("[!] Falha ao realizar operação");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id){
        try{
            ps = conexao.getConn().prepareStatement(
                "DELETE FROM operacao WHERE id = ?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("[!] Operação excluída com sucesso!");
            return true;
        } catch(SQLException e){
            System.out.println("[!] Falhar ao excluir operação!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(int id, Operacao operacao){
        String sql = "UPDATE operacao SET " +
                     "funcionario_locacao_id = ?, " +       // 1
                     "funcionario_devolucao_id = ?, " +    // 2
                     "aluno_id = ?, " +                    // 3
                     "professor_id = ?, " +                // 4
                     "funcionario_id = ?, " +              // 5 (Pessoa related)
                     "exemplar_id = ?, " +                 // 6
                     "tipo_operacao = ?, " +               // 7
                     "data_locacao = ?, " +                // 8
                     "data_devolucao = ?, " +              // 9
                     "data_devolvido = ? " +               // 10
                     "WHERE id = ?";                       // 11
        try{
            ps = conexao.getConn().prepareStatement(sql);

            ps.setInt(1, operacao.getFuncionarioLocacaoId());
            
            if (operacao.getFuncionarioDevolucaoId() == 0) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, operacao.getFuncionarioDevolucaoId());
            }

            if(operacao.getPessoa() instanceof Aluno){
                ps.setInt(3, operacao.getPessoa().getId());
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.INTEGER);
            } else if(operacao.getPessoa() instanceof Professor){
                ps.setNull(3, Types.INTEGER);
                ps.setInt(4, operacao.getPessoa().getId());
                ps.setNull(5, Types.INTEGER);
            } else if(operacao.getPessoa() instanceof Funcionario){
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
                ps.setInt(5, operacao.getPessoa().getId());
            }

            ps.setInt(6, operacao.getExemplarId());
            ps.setString(7, operacao.getTipoOperacao().name());
            
            if (operacao.getDataLocacao() == null) {
                ps.setNull(8, Types.DATE);
            } else {
                ps.setDate(8, new java.sql.Date(operacao.getDataLocacao().getTime()));
            }
            
            if (operacao.getDataDevolucao() == null) {
                ps.setNull(9, Types.DATE);
            } else {
                ps.setDate(9, new java.sql.Date(operacao.getDataDevolucao().getTime()));
            }

            if (operacao.getDataDevolvido() == null) {
                ps.setNull(10, Types.DATE);
            } else {
                ps.setDate(10, new java.sql.Date(operacao.getDataDevolvido().getTime()));
            }

            ps.setInt(11, id);

            int affectedRows = ps.executeUpdate();
            ps.close();

            if (affectedRows > 0) {
                System.out.println("[!] Operação atualizada com sucesso!");
                return true;
            } else {
                System.out.println("[!] Operação não encontrada ou nenhum dado alterado (ID: " + id + ").");
                return false; 
            }
        } catch (SQLException e){
            System.out.println("[!] Falha ao atualizar operação!");
            e.printStackTrace();
            return false;
        }
    }

}
