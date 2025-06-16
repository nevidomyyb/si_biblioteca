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
        Funcionario func = lerDadosFuncionario(false);
        boolean succ = funcionarioService.registrarFuncionario(func);
        if(succ){
            System.out.println("[!] Funcionário cadastrado com sucesso.");
        } else {
            System.err.println("[!] Não foi possível cadastrar o funcionário.");
        }
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
                            + ColunaUtils.formatarColuna("ID End.", 6) + " | " + ColunaUtils.formatarColuna("RUA", 12) +
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
                                    enderecoMenu.imprimirEnderecoPorId(funcionario.getEnderecoId())
                    );
                }
            }
        }

    private void editarFuncionario() {
        System.out.print("[!] ID do Funcionário: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Funcionario funcionario = lerDadosFuncionario(true);
        boolean succ = funcionarioService.editarFuncionario(id, funcionario);
        if(succ){
            System.out.println("[!] Funcionário editado.");
        } else {
            System.err.println("[!] Não foi possível editar o funcionário.");
        }
    }

    private void removerFuncionario() {
        System.out.print("ID Funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean succ = funcionarioService.excluirFuncionario(id);
        if(succ){
            System.out.println("[!] Funcionário removido.");
        } else {
            System.err.println("[!] Não foi possível remover o funcionário.");
        }
    }

    private Funcionario lerDadosFuncionario(boolean modoEditar) {
        Funcionario f = new Funcionario();
        EnderecoMenu enderecoMenu = new EnderecoMenu();

        System.out.print("[!] NOME: ");
        f.setNome(scanner.nextLine());

        System.out.print("[!] EMAIL: ");
        f.setEmail(scanner.nextLine());

        System.out.print("[!] TELEFONE (opcional, pressione [ENTER] para pular): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            f.setTelefone(telefone);
        }

        System.out.print("[!] CPF: ");
        f.setCpf(scanner.nextLine());

        System.out.print("[!] Credencial: ");
        f.setCredencial(scanner.nextLine());

        System.out.println("[!] ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();

        if (endereco != null) {
            f.setEnderecoId(endereco.getId());
        } else {
            f.setEnderecoId(0);
        }
        if(!modoEditar){
            System.out.print("[!] Login: ");
            f.setLogin(scanner.nextLine());

            System.out.print("[!] Senha: ");
            f.setSenha(scanner.nextLine());
        }
        return f;
    }

    public static void main(String[] args) {
        new FuncionarioMenu().exibirMenu();
    }
}