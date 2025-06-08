package com.pedro.menu;

import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.models.Professor;
import com.pedro.service.AlunoService;
import com.pedro.service.AutenticacaoService;
import com.pedro.service.ProfessorService;

public class LoginMenu {
    private Scanner scanner;

    public LoginMenu() {
        scanner = new Scanner(System.in);
    }

    public void login() {
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        System.out.println("[!] Login: ");
        String login = scanner.nextLine();

        System.out.println("[!] Senha: ");
        String senha = scanner.nextLine();
        try {
            String tipoUsuario = autenticacaoService.autenticar(login, senha).toLowerCase().trim();

            if (tipoUsuario == null || tipoUsuario.equals("")) {
                System.out.println("[!] Login ou Senha Inválidos.");
                return;
            }

            System.out.println("[!] Bem-Vindo(a), " + login);
            if (tipoUsuario.equals("aluno") || tipoUsuario.equals("professor")) {
                AlunoProfessorMenu alunoProfessorMenu = new AlunoProfessorMenu();
                alunoProfessorMenu.imprimirMenuAlunoProfessor();
            } else if (tipoUsuario.toLowerCase().equals("funcionario")) {
                PrincipalMenu principalMenu = new PrincipalMenu();
                principalMenu.imprimirMenuPrincipal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cadastro() {
        System.out.println("[!] Nome: ");
        String nome = scanner.nextLine();

        System.out.println("[!] CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("[!] Email: ");
        String email = scanner.nextLine();

        System.out.println("[!] Telefone (opcional, pressione [ENTER] para pular):");
        String telefone = scanner.nextLine();
        System.out.println("[!] ENDEREÇO (opcional, pressione [ENTER] para pular):");
        String endereco = scanner.nextLine();

        System.out.println("[!] Login: ");
        String login = scanner.nextLine();

        System.out.println("[!] Senha: ");
        String senha = scanner.nextLine();

        System.out.println("[!] Tipo do Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);

        if (tipoUsuarioInt == 1) {
            System.out.println("[!] Curso");
            String curso = scanner.nextLine();

            System.out.println("[!] Período: ");
            String periodo = scanner.nextLine();

            System.out.println("[!] Turno: ");
            String turno = scanner.nextLine();

            System.out.println("[!] Matrícula: ");
            String matricula = scanner.nextLine();

            Aluno aluno = new Aluno(cpf, nome, email, curso, periodo, turno, matricula, login, senha);

            if (!telefone.isEmpty()) {
                aluno.setTelefone(telefone);
            }

            if (!endereco.isEmpty()) {
                int enderecoInt = Integer.parseInt(endereco);
                aluno.setEnderecoId(enderecoInt);
            }

            try {
                AlunoService alunoService = new AlunoService();
                alunoService.registrarAluno(aluno);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (tipoUsuarioInt == 2) {
            System.out.println("[!] Disciplina: ");
            String disciplina = scanner.nextLine();

            System.out.println("[!] Credencial: ");
            String credencial = scanner.nextLine();

            Professor professor = new Professor(cpf, nome, email, disciplina, credencial, login, senha);

            if (!telefone.isEmpty()) {
                professor.setTelefone(telefone);
            }

            if (!endereco.isEmpty()) {
                int enderecoInt = Integer.parseInt(endereco);
                professor.setEnderecoId(enderecoInt);
            }

            try {
                ProfessorService professorService = new ProfessorService();
                professorService.registrarProfessor(professor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
