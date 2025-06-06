package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.service.AlunoService;
import com.pedro.service.AutenticacaoService;

public class LoginMenu {
    private IO io = new IO();
    private Scanner scanner = new Scanner(System.in);
    


    private void Login(){
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        System.out.println("[!] Login: ");
        String login = scanner.nextLine();

        System.out.println("[!] Senha: ");
        String senha = scanner.nextLine();
        try{
            String tipoUsuario = autenticacaoService.autenticar(login, senha);

            if(tipoUsuario == null || tipoUsuario.equals("")){
                System.out.println("[!] Login ou Senha Inválidos.");
                return;
            }

            System.out.println("[!] Bem-Vindo(a), " + login);
            if(tipoUsuario.toLowerCase().equals("aluno") || tipoUsuario.toLowerCase().equals("professor")){
                AlunoProfessorMenu alunoProfessorMenu = new AlunoProfessorMenu();
                alunoProfessorMenu.imprimirMenuAlunoProfessor();
            } else if (tipoUsuario.toLowerCase().equals("funcionario")){
                PrincipalMenu principalMenu = new PrincipalMenu();
                principalMenu.imprimirMenuPrincipal();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void Cadastro(){
        System.out.println("[!] Login: ");
        String login = scanner.nextLine();

        System.out.println("[!] Senha: ");
        String senha = scanner.nextLine();

        System.out.println("[!] Tipo do Usuário (Aluno | Professor): ");
        String tipo_usuario = scanner.nextLine();

        if(tipo_usuario.toLowerCase().equals("aluno")){
            AlunoService alunoService = new AlunoService();

            System.out.println("[!] Nome: ");
            String nome = scanner.nextLine();

            System.out.println("[!] CPF: ");
            String cpf = scanner.nextLine();

            System.out.println("[!] Email: ");
            String email = scanner.nextLine();

            System.out.println("[!] Curso");
            String curso = scanner.nextLine();

            System.out.println("[!] Período: ");
            String periodo = scanner.nextLine();

            System.out.println("[!] Turno: ");
            String turno = scanner.nextLine();

            System.out.println("[!] Matrícula: ");
            String matricula = scanner.nextLine();

            System.out.println("INFORMAÇÕES ADICIONAIS: ");
            System.out.println("CASO NÃO DESEJE INSERIR NENHUMA INFORMAÇÃO, PRESSIONE 'ENTER'");
            System.out.println("[!] Telefone: ");
            String telefone = scanner.nextLine();

            System.out.println("[!] ENDEREÇO: ");
            String endereco = scanner.nextLine();

            if(telefone.equals("") || telefone.isEmpty()){
                telefone = null;
            }

            int novo_endereco = 0;

            try{
                alunoService.registrarAluno(new Aluno(cpf, nome, telefone, email, novo_endereco, curso, periodo, turno, matricula, login, senha));
            } catch (Exception e){
                e.printStackTrace();
            }

            

        }
    }

    public void imprimirMenuPrincipal(){
        List<String> opcoesMenuPrincipal = new ArrayList<String>(Arrays.asList("1. Login", "2. Cadastrar", "3. Sair"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "INICIO");

        while (opc != 3) {
            switch (opc) {
                case 1:
                    Login();
                    break;
                case 2:
                    Cadastro();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "INICIO");
        }

    }

    public static void main(String[] args) {
        LoginMenu menu = new LoginMenu();
        menu.imprimirMenuPrincipal();
    }
}
