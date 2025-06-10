package com.pedro.menu;

import com.pedro.config.IO;
import com.pedro.models.Endereco;
import com.pedro.models.Funcionario;
import com.pedro.service.FuncionarioService;
import com.pedro.utils.ColunaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FuncionarioMenu {

    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final Scanner scanner = new Scanner(System.in);
    private final IO io = new IO();
    private EnderecoMenu enderecoMenu = new EnderecoMenu();

    public void exibirMenu() {
        List<String> opcoesFuncionario = new ArrayList<String>(Arrays.asList(
                "1. Cadastrar funcionário",
                "2. Consultar funcionários",
                "3. Editar funcionário",
                "4. Excluir funcionário",
                "5. Voltar"));

        int opcao = io.imprimirMenuRetornandoOpcao(opcoesFuncionario, "MENU FUNCIONÁRIO");
        while (opcao != 5) {
            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> editarFuncionario();
                case 4 -> removerFuncionario();
                default -> System.out.println("[!] Opção Inválida");
            }
            opcao = io.imprimirMenuRetornandoOpcao(opcoesFuncionario, "MENU FUNCIONÁRIO");
        }

    }

    private void cadastrarFuncionario() {
        Funcionario func = lerDadosFuncionario();
        funcionarioService.registrarFuncionario(func);
    }

        private void listarFuncionarios() {
            List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
            System.out.println(
                    "---------------------------------------------------------------------------------------------------FUNCIONÁRIOS----------------------------------------------------------------------------------------------");
            System.out.println(
                    "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Nome", 12) +
                            " | " + ColunaUtils.formatarColuna("Email", 25) + " | "
                            + ColunaUtils.formatarColuna("Telefone", 12) +
                            " | " + ColunaUtils.formatarColuna("Credencial", 12) + " | "
                            + ColunaUtils.formatarColuna("Login", 12) +
                            " | " + ColunaUtils.formatarColuna("Senha", 12) + " | " + "| "
                            + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("RUA", 12) +
                            " | " + ColunaUtils.formatarColuna("BAIRRO", 12) + " | "
                            + ColunaUtils.formatarColuna("NÚMERO", 6) +
                            " | " + ColunaUtils.formatarColuna("CEP", 10) + " | " + ColunaUtils.formatarColuna("CIDADE", 12)
                            +
                            " | " + ColunaUtils.formatarColuna("ESTADO", 12) + " |");
            System.out.println("-".repeat(205));
            if (!funcionarios.isEmpty()) {
                for (Funcionario funcionario : funcionarios) {
                    System.out.println(
                            "| " + ColunaUtils.formatarColuna(String.valueOf(funcionario.getId()), 6) + " | "
                                    + ColunaUtils.formatarColuna(funcionario.getNome(), 12) +
                                    " | " + ColunaUtils.formatarColuna(funcionario.getEmail(), 25) + " | "
                                    + ColunaUtils.formatarColuna(funcionario.getTelefone(), 12) +
                                    " | " + ColunaUtils.formatarColuna(funcionario.getCredencial(), 12) + " | "
                                    + ColunaUtils.formatarColuna(funcionario.getLogin(), 12) +
                                    " | " + ColunaUtils.formatarColuna(funcionario.getSenha(), 12) + " | " +
                                    enderecoMenu.imprimirEndereco(funcionario.getEnderecoId())
                    );
                }
            }
        }

    private void editarFuncionario() {
        System.out.print("ID Funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("NOME (pressione [ENTER] para manter atual): ");
        String nome = scanner.nextLine();

        System.out.print("EMAIL (pressione [ENTER] para manter atual): ");
        String email = scanner.nextLine();

        System.out.print("CPF (pressione [ENTER] para manter atual): ");
        String cpf = scanner.nextLine();

        System.out.print("TELEFONE (pressione [ENTER] para manter atual): ");
        String telefone = scanner.nextLine();

        System.out.print("CREDENCIAL (pressione [ENTER] para manter atual): ");
        String credencial = scanner.nextLine();

        System.out.print("LOGIN (pressione [ENTER] para manter atual): ");
        String login = scanner.nextLine();

        System.out.print("SENHA (pressione [ENTER] para manter atual): ");
        String senha = scanner.nextLine();

        Endereco endereco = enderecoMenu.cadastrarOuPular();
        Funcionario funcionario = new Funcionario(cpf, nome, telefone, email, credencial, id, login, senha);
        funcionario.setEnderecoId(endereco == null ? 0 : endereco.getId());

        funcionarioService.editarFuncionario(id, funcionario);
    }

    private void removerFuncionario() {
        System.out.print("ID Funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        funcionarioService.excluirFuncionario(id);
    }

    private Funcionario lerDadosFuncionario() {
        Funcionario f = new Funcionario();
        EnderecoMenu enderecoMenu = new EnderecoMenu();

        System.out.print("Nome: ");
        f.setNome(scanner.nextLine());

        System.out.print("Email: ");
        f.setEmail(scanner.nextLine());

        System.out.print("Telefone (opcional, pressione [ENTER] para pular): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            f.setTelefone(telefone);
        }

        System.out.print("CPF: ");
        f.setCpf(scanner.nextLine());

        System.out.print("Credencial: ");
        f.setCredencial(scanner.nextLine());

        System.out.println("ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();

        if (endereco != null) {
            f.setEnderecoId(endereco.getId());
        }

        System.out.print("Login: ");
        f.setLogin(scanner.nextLine());

        System.out.print("Senha: ");
        f.setSenha(scanner.nextLine());

        return f;
    }

    public static void main(String[] args) {
        new FuncionarioMenu().exibirMenu();
    }
}