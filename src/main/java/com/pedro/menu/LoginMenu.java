package com.pedro.menu;

import java.util.Scanner;

import com.pedro.models.Aluno;
import com.pedro.models.Endereco;
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
        EnderecoMenu enderecoMenu = new EnderecoMenu();

        System.out.print("[!] Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("[!] CPF: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("[!] Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("[!] Telefone (opcional, pressione [ENTER] para pular): ");
        String telefone = scanner.nextLine().trim();
        System.out.println("ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        System.out.println("[!] Login: ");
        String login = scanner.nextLine().trim();

        System.out.println("[!] Senha: ");
        String senha = scanner.nextLine().trim();

        System.out.println("[!] Tipo do Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);

        if (tipoUsuarioInt == 1) {
            System.out.println("[!] Curso");
            String curso = scanner.nextLine().trim();

            System.out.println("[!] Período: ");
            String periodo = scanner.nextLine().trim();

            System.out.println("[!] Turno: ");
            String turno = scanner.nextLine().trim();

            System.out.println("[!] Matrícula: ");
            String matricula = scanner.nextLine().trim();

            Aluno aluno = new Aluno(cpf, nome, email, curso, periodo, turno, matricula, login, senha);

            if (!telefone.isEmpty()) {
                aluno.setTelefone(telefone);
            }

            if(endereco != null){
                aluno.setEnderecoId(endereco.getId());
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
            String disciplina = scanner.nextLine().trim();

            System.out.println("[!] Credencial: ");
            String credencial = scanner.nextLine().trim();

            Professor professor = new Professor(cpf, nome, email, disciplina, credencial, login, senha);

            if (!telefone.isEmpty()) {
                professor.setTelefone(telefone);
            }

            if(endereco != null){
                professor.setEnderecoId(endereco.getId());
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
