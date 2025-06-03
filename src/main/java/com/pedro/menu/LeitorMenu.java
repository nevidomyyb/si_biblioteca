package com.pedro.menu;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
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
            "[3] Voltar"
        );
        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenu, "Gerenciar Leitores");
        while (opc != 4) {
            switch (opc) {
                case 1:
                    System.out.println("Nome:");
                    String nomeUsuario = scanner.nextLine().trim();
                    System.out.println("CPF:");
                    String cpfAluno = scanner.nextLine().trim();
                    System.out.println("Telefone:");
                    String telefoneUsuario  = scanner.nextLine().trim();
                    System.out.println("Email:");
                    String emailUsuario  = scanner.nextLine().trim();
                    System.out.println("Login:");
                    String loginUsuario  = scanner.nextLine().trim();
                    System.out.println("Senha:");
                    String senhaUsuario = scanner.nextLine().trim();
                    System.out.println("Tipo de Usuário: ");
                    System.out.println("    [1] Aluno");
                    System.out.println("    [2] Professor");
                    String tipoUsuario = scanner.nextLine().trim();
                    int tipoUsuarioInt = Integer.parseInt(tipoUsuario);
                    if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
                        System.out.println("[!] Tipo de Usuário inválido");
                    }
                    else if (tipoUsuarioInt == 1) {
                        System.out.println("Curso:");
                        String cursoUsuario = scanner.nextLine().trim();
                        System.out.println("Período:");
                        String periodoUsuario = scanner.nextLine().trim();
                        System.out.println("Matricula:");
                        String matriculaUsuario = scanner.nextLine().trim();
                        System.out.println("Turno:");
                        String turnoUsuario = scanner.nextLine().trim();

                        boolean sucesso = alunoService.registrarAluno(new Aluno(cpfAluno,
                            nomeUsuario, telefoneUsuario, emailUsuario, 0, cursoUsuario,
                            periodoUsuario, turnoUsuario, matriculaUsuario, loginUsuario, senhaUsuario
                        ));
                        if (!sucesso)
                            System.out.println("[!] Não foi possível cadastrar.");
                    }
                    else {
                        System.out.println("Disciplina:");
                        String disciplinaUsuario = scanner.nextLine().trim();
                        System.out.println("Credencial:");
                        String credencialUsuario = scanner.nextLine().trim();

                        boolean sucesso = professorService.registrarProfessor(new Professor(
                            cpfAluno, nomeUsuario, telefoneUsuario, emailUsuario,
                            0, disciplinaUsuario, credencialUsuario,
                            loginUsuario, senhaUsuario
                        ));
                        if (!sucesso)
                            System.out.println("[!] Não foi possível cadastrar.");
                    }

                    break;
                case 2:
                    List<Aluno> alunos = alunoService.listarAlunos();
                    List<Professor> professores = professorService.listarProfessors();
                    System.out.println("Leitores");
                    System.out.println("ID | Nome | E-mail | Tipo ");
                    if (!alunos.isEmpty()) {
                        for (Aluno aluno : alunos) {
                            int max = aluno.getNome().length() < 11 ? aluno.getNome().length()  : 12;
                            System.out.println("[" + aluno.getId() + "] " + aluno.getNome().substring(0, max) + " - " + aluno.getEmail() + " - " + "Aluno");

                        }
                    }
                    if (!professores.isEmpty()){
                        for (Professor professor : professores) {
                            int max = professor.getNome().length() < 11 ? professor.getNome().length() : 12;
                            System.out.println("[" + professor.getId() + "]" + professor.getNome().substring(0, max) + " - " + professor.getEmail() + " - " + "Professor");
                        }
                    }
                    break;

                case 3:
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

}
