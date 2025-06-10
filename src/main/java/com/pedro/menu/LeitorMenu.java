package com.pedro.menu;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.models.Endereco;
import com.pedro.models.Professor;
import com.pedro.service.AlunoService;
import com.pedro.service.ProfessorService;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorMenu {

    private Scanner scanner;
    AlunoService alunoService = new AlunoService();
    ProfessorService professorService = new ProfessorService();

    public LeitorMenu() {
        scanner = new Scanner(System.in);
    }

    private static String centralizadString(String text, int width) {
        if (text == null) {
            text = "";
        }
        if (text.length() >= width) {
            return text;
        }
        int paddingSize = width - text.length();
        int padStart = paddingSize / 2;
        int padEnd = paddingSize - padStart;

        return " ".repeat(padStart) + text + " ".repeat(padEnd);
    }

    public void imprimirMenu() {
        IO io = new IO();
        List<String> opcoesMenu = List.of(
            "[1] Cadastrar Leitor",
            "[2] Listar Leitores",
            "[3] Editar Leitor",
            "[4] Excluir Leitor",
            "[5] Voltar"
        );
        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenu, "Gerenciar Leitores");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarLeitor();
                    break;
                case 2:
                    List<Aluno> alunos = alunoService.listarAlunos();
                    List<Professor> professores = professorService.listarProfessors();
                    System.out.println("Leitores");
                    System.out.println("ID | Nome | E-mail | Tipo ");
                    if (!alunos.isEmpty()) {
                        for (Aluno aluno : alunos) {
                            int max = aluno.getNome().length() < 11 ? aluno.getNome().length()  : 12;
                            System.out.println("[" + aluno.getId() + "] " + aluno.getNome().substring(0, max-1) + " - " + aluno.getEmail() + " - " + "Aluno");

                        }
                    }
                    if (!professores.isEmpty()){
                        for (Professor professor : professores) {
                            int max = professor.getNome().length() < 11 ? professor.getNome().length() : 12;
                            System.out.println("[" + professor.getId() + "]" + professor.getNome().substring(0, max-1) + " - " + professor.getEmail() + " - " + "Professor");
                        }
                    }
                    break;
                case 3:
                    //editar
                    editarLeitor();
                    break;
                case 4:
                    // excluir
                    break;
                default:
                    System.out.println("[!] Opção inválida");
            }

            opc = io.imprimirMenuRetornandoOpcao(opcoesMenu, "Gerenciar Leitores");
        }

    }

    public static void main(String[] args) {
        LeitorMenu leitorMenu = new LeitorMenu();
        leitorMenu.imprimirMenu();
    }
    private void editarLeitor() {
        boolean sucesso = false;
        System.out.println("Tipo de Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);

        if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
            System.out.println("[!] Tipo de Usuário inválido");
        }
        System.out.print("ID do Leitor: ");
        String ID =scanner.nextLine().trim();
        int IDInt = Integer.parseInt(ID);
        if (tipoUsuarioInt == 1) {
            Aluno aluno = lerDadosAluno(true);
            sucesso = alunoService.editarAluno(IDInt, aluno);
        }
        else {
            Professor professor = lerDadosProfessor(true);
            sucesso = professorService.editarProfessor(IDInt, professor);
        }
        if (!sucesso) {
            System.out.println("[!] Não foi possível cadastrar.");
        } else {
            System.out.println("[!] Cadastrado com sucesso.");
        }
    }
    private void cadastrarLeitor() {
        boolean sucesso = false;
        System.out.println("Tipo de Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);
        if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
            System.out.println("[!] Tipo de Usuário inválido");
        }
        else if (tipoUsuarioInt == 1) {
            Aluno aluno = lerDadosAluno(false);
            sucesso = alunoService.registrarAluno(aluno);
        }
        else {
            Professor professor = lerDadosProfessor(false);
            sucesso = professorService.registrarProfessor(professor);
        }
        if (!sucesso) {
            System.out.println("[!] Não foi possível editar.");
        }
    }

    private Professor lerDadosProfessor(boolean modoEditar) {
        Professor prof = new Professor();
        EnderecoMenu enderecoMenu = new EnderecoMenu();
        System.out.print("Nome: ");
        prof.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        prof.setCpf(scanner.nextLine());

        System.out.print("Telefone (opcional, pressione [ENTER] para pular): ");
        prof.setEmail(scanner.nextLine());

        System.out.print("Email: ");
        prof.setEmail(scanner.nextLine());

        System.out.println("ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        if (endereco != null) prof.setEnderecoId(endereco.getId());

        System.out.print("Disciplina: ");
        prof.setDisciplina(scanner.nextLine());

        System.out.print("Credencial: ");
        prof.setCredencial(scanner.nextLine());

        if (!modoEditar) {
            System.out.print("Login: ");
            prof.setLogin(scanner.nextLine());

            System.out.print("Senha: ");
            prof.setSenha(scanner.nextLine());
        }
        return prof;

    }

    private Aluno lerDadosAluno(boolean modoEditar) {
        Aluno aluno = new Aluno();
        EnderecoMenu enderecoMenu = new EnderecoMenu();
        System.out.print("Nome: ");
        aluno.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        aluno.setCpf(scanner.nextLine());

        System.out.print("Telefone (opcional, pressione [ENTER] para pular): ");
        aluno.setEmail(scanner.nextLine());

        System.out.print("Email: ");
        aluno.setEmail(scanner.nextLine());

        System.out.print("Curso: ");
        aluno.setCurso(scanner.nextLine());

        System.out.print("Periodo: ");
        aluno.setPeriodo(scanner.nextLine());

        System.out.print("Matrícula: ");
        aluno.setMatricula(scanner.nextLine());

        System.out.print("Turno: ");
        aluno.setTurno(scanner.nextLine());

        System.out.println("ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        if (endereco != null) aluno.setEnderecoId(endereco.getId());

        if (!modoEditar) {
            System.out.print("Login: ");
            aluno.setLogin(scanner.nextLine());

            System.out.print("Senha: ");
            aluno.setSenha(scanner.nextLine());
        }

        return aluno;
    }

}
