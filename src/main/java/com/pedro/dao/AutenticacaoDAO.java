package com.pedro.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

import com.pedro.config.Conexao;
import com.pedro.config.Senha;
import com.pedro.models.Aluno;
import com.pedro.models.Funcionario;
import com.pedro.models.Professor;

public class AutenticacaoDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public AutenticacaoDAO() {
        conexao = new Conexao();
    }

    public boolean cadastrarUsuario(
            Funcionario funcionario,
            Aluno aluno,
            Professor professor
    )  {
        if (
            (aluno != null && aluno.getLogin().length() > 12) ||
            (funcionario != null && funcionario.getLogin().length() > 12) ||
            (professor != null && professor.getLogin().length() > 12)
        ) {
            System.out.println("[!] O login do leitor não pode ter mais que 12 caracteres.");
            return false;
        }
        if (professor != null) {
            String senhaHashed;
            try {
                senhaHashed = Senha.hashSenha(professor.getSenha());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }

            String SQL = """
            INSERT INTO professor 
            (id, nome, telefone, email, disciplina,
            credencial, cpf, endereco_id, login ,senha)
            VALUES (
            null, ?, ?, ? , ?, ?, ?, ?, ?, ?
            )
            """;
            try {
                ps = conexao.getConn().prepareStatement(SQL);
                ps.setString(1, professor.getNome());
                ps.setString(2, professor.getTelefone());
                ps.setString(3, professor.getEmail());
                ps.setString(4, professor.getDisciplina());
                ps.setString(5, professor.getCredencial());
                ps.setString(6, professor.getCpf());
                if (professor.getEnderecoId() == 0) {
                    ps.setNull(7, Types.INTEGER);
                } else {
                    ps.setInt(7, professor.getEnderecoId());
                }
                ps.setString(8, professor.getLogin());
                ps.setString(9, senhaHashed);
                ps.executeUpdate();
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }

        }


        else if (aluno != null) {
            String senhaHashed;
            try {
                senhaHashed = Senha.hashSenha(aluno.getSenha());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }
            String SQL = """
            INSERT INTO aluno
            (id, nome, telefone, email, curso, periodo, turno, matricula,
            cpf, endereco_id, login, senha
            )
            VALUES (
            null, ? , ?, ?, ?, ?, ?, ?,
            ?, ?, ?, ?
            )
            """;
            try {
                ps = conexao.getConn().prepareStatement(SQL);
                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getTelefone());
                ps.setString(3, aluno.getEmail());
                ps.setString(4, aluno.getCurso());
                ps.setString(5, aluno.getPeriodo());
                ps.setString(6, aluno.getTurno());
                ps.setString(7, aluno.getMatricula());
                ps.setString(8, aluno.getCpf());
                if (aluno.getEnderecoId() == 0) {
                    ps.setNull(9, Types.INTEGER);
                } else {
                    ps.setInt(9, aluno.getEnderecoId());
                }
                ps.setString(10, aluno.getLogin());
                ps.setString(11, senhaHashed);
                ps.executeUpdate();
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                return false;

            }
        }

        else if (funcionario != null) {
            String senhaHashed;
            try {
                senhaHashed = Senha.hashSenha(funcionario.getSenha());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }

            String SQL = """
            INSERT INTO funcionario
            (id, nome, telefone, email, credencial, cpf, endereco_id, login, senha)
            VALUES (
            null, ?, ?, ?, ?, ?, ?, ?, ?
            );
            """;
            try {
                ps = conexao.getConn().prepareStatement(SQL);
                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getTelefone());
                ps.setString(3, funcionario.getEmail());
                ps.setString(4, funcionario.getCredencial());
                ps.setString(5, funcionario.getCpf());
                if (funcionario.getEnderecoId() == 0) {
                    ps.setNull(6, Types.INTEGER);
                } else {
                    ps.setInt(6, funcionario.getEnderecoId());
                }
                ps.setString(7, funcionario.getLogin());
                ps.setString(8, senhaHashed);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }


        }
        return false;
    }

    public String autenticar(String login, String senha) {
        try {
            boolean encontrado = false;
            int idEncontrado = 0;
            String loginEncontrado = "";
            String senhaEncontrado = "";
            String tipoUsuario = "";

            String SQLAluno = "SELECT * FROM aluno WHERE login = ?";
            String SQLFuncionario = "SELECT * FROM funcionario WHERE login = ?";
            String SQLProfessor = "SELECT * FROM professor WHERE login = ?";

            PreparedStatement psAluno = conexao.getConn().prepareStatement(SQLAluno);
            psAluno.setString(1, login);

            PreparedStatement psFuncionario = conexao.getConn().prepareStatement(SQLFuncionario);
            psFuncionario.setString(1, login);

            PreparedStatement psProfessor = conexao.getConn().prepareStatement(SQLProfessor);
            psProfessor.setString(1, login);

            ResultSet resultAluno = psAluno.executeQuery();
            if (resultAluno.next()) {
                encontrado = true;
                idEncontrado = resultAluno.getInt("id");
                loginEncontrado = resultAluno.getString("login");
                senhaEncontrado = resultAluno.getString("senha");
                tipoUsuario = "Aluno";
            }
            ResultSet resultFuncionario = psFuncionario.executeQuery();
            if (resultFuncionario.next()) {
                encontrado = true;
                idEncontrado = resultFuncionario.getInt("id");
                loginEncontrado = resultFuncionario.getString("login");
                senhaEncontrado = resultFuncionario.getString("senha");
                tipoUsuario = "Funcionario";
            }
            ResultSet resultProfessor = psProfessor.executeQuery();
            if (resultProfessor.next()) {
                encontrado = true;
                idEncontrado = resultProfessor.getInt("id");
                loginEncontrado = resultProfessor.getString("login");
                senhaEncontrado = resultProfessor.getString("senha");
                tipoUsuario = "Professor";
            }
            if (!encontrado || loginEncontrado.isEmpty()) {
                return "";
            }

            boolean valido = Senha.verificarSenha(senha, senhaEncontrado);
            if (Objects.equals(login, loginEncontrado) && valido) {
                return tipoUsuario;
            }
            return "";
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        AutenticacaoDAO dao = new AutenticacaoDAO();

        Funcionario f1 = new Funcionario(
          "07066373480", "Pedro", "82993362376",
          "idk.pedroc@gmail.com",  "abcdefg",0,"pedro1", "Senha!123"
        );
        boolean success = dao.cadastrarUsuario(f1, null, null);
        System.out.println(dao.autenticar(f1.getLogin(), "Senha!123"));
    }


}
