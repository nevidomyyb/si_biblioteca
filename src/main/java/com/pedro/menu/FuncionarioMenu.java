package com.pedro.menu;

import com.pedro.config.IO;
import com.pedro.models.Endereco;
import com.pedro.models.Funcionario;
import com.pedro.service.FuncionarioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FuncionarioMenu {

    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final Scanner scanner = new Scanner(System.in);
    private final IO io = new IO();

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
        List<Funcionario> lista = funcionarioService.listarFuncionarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            for (Funcionario f : lista) {
                System.out.println("- " + f.getId() + ": " + f.getNome() + " | " + f.getEmail());
            }
        }
    }

    private void editarFuncionario() {
        System.out.print("ID Funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario novoFuncionario = lerDadosFuncionario();
        funcionarioService.editarFuncionario(id, novoFuncionario);
    }

    private void removerFuncionario() {
        System.out.print("ID Funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario func = funcionarioService.consultarFuncionario(id);
        if (func != null) {
            funcionarioService.excluirFuncionario(func);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
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