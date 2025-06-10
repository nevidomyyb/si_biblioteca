package com.pedro.menu;

import com.pedro.config.IO;
import com.pedro.models.Endereco;
import com.pedro.service.EnderecoService;
import com.pedro.utils.ColunaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EnderecoMenu {
    private Scanner scanner;
    private EnderecoService enderecoService;
    private IO io;

    public EnderecoMenu() {
        scanner = new Scanner(System.in);
        enderecoService = new EnderecoService();
        io = new IO();
    }

    public void imprimirMenu() {
        List<String> opcoesEndereco = new ArrayList<>(Arrays.asList(
                "1. Cadastrar endereço",
                "2. Consultar endereços",
                "3. Editar endereço",
                "4. Excluir endereço",
                "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesEndereco, "MENU ENDEREÇO");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarEndereco();
                    break;
                case 2:
                    listarEnderecos();
                    break;
                case 3:
                    editarEndereco();
                    break;
                case 4:
                    excluirEndereco();
                    break;
                default:
                    System.err.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesEndereco, "MENU ENDEREÇO");
        }
    }

    public Endereco cadastrarEndereco() {
        System.out.print("[!] RUA: ");
        String rua = scanner.nextLine();
        System.out.print("[!] BAIRRO: ");
        String bairro = scanner.nextLine();
        System.out.print("[!] NÚMERO: ");
        String numero = scanner.nextLine();
        System.out.print("[!] CIDADE: ");
        String cidade = scanner.nextLine();
        System.out.print("[!] ESTADO: ");
        String estado = scanner.nextLine();
        System.out.print("[!] CEP (opcional, pressione [ENTER] para pular): ");
        String cep = scanner.nextLine();

        Endereco endereco = new Endereco(rua, bairro, numero, cidade, estado);
        if (!cep.isEmpty()) {
            endereco.setCep(cep);
        }
        return enderecoService.cadastrarEndereco(endereco);
    }

    private void listarEnderecos() {
        List<Endereco> enderecos = enderecoService.listarEnderecos();
        System.out.println(
                "--------------------------------------------ENDEREÇOS---------------------------------------");
        System.out.println(
                "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("RUA", 12) +
                        " | " + ColunaUtils.formatarColuna("BAIRRO", 12) + " | "
                        + ColunaUtils.formatarColuna("NÚMERO", 6) +
                        " | " + ColunaUtils.formatarColuna("CEP", 10) + " | " + ColunaUtils.formatarColuna("CIDADE", 12)
                        +
                        " | " + ColunaUtils.formatarColuna("ESTADO", 12) + " |");
        System.out.println(
                "--------------------------------------------------------------------------------------------");
        if (!enderecos.isEmpty()) {
            for (Endereco endereco : enderecos) {
                System.out.println(
                        "| " + ColunaUtils.formatarColuna(String.valueOf(endereco.getId()), 6) + " | " +
                                ColunaUtils.formatarColuna(endereco.getRua(), 12) +
                                " | " + ColunaUtils.formatarColuna(endereco.getBairro(), 12) +
                                " | " + ColunaUtils.formatarColuna(endereco.getNumero(), 6) +
                                " | " + ColunaUtils.formatarColuna(endereco.getCep(), 10) +
                                ColunaUtils.formatarColuna(endereco.getCidade(), 12) +
                                " | " + ColunaUtils.formatarColuna(endereco.getEstado(), 12) + " |");
            }
        }
        System.out.println(
                "--------------------------------------------------------------------------------------------");

    }

    public String imprimirEndereco(int id) {
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        if (endereco == null) {
            return ColunaUtils.formatarColuna(null, 6) + " | " +
                    ColunaUtils.formatarColuna(null, 12) + " | " +
                    ColunaUtils.formatarColuna(null, 12) + " | " +
                    ColunaUtils.formatarColuna(null, 6) + " | " +
                    ColunaUtils.formatarColuna(null, 10) + " | " +
                    ColunaUtils.formatarColuna(null, 12) + " | " +
                    ColunaUtils.formatarColuna(null, 12);
        }
        return ColunaUtils.formatarColuna(String.valueOf(id), 6) + " | " +
                ColunaUtils.formatarColuna(endereco.getRua(), 12) + " | " +
                ColunaUtils.formatarColuna(endereco.getBairro(), 12) + " | " +
                ColunaUtils.formatarColuna(endereco.getNumero(), 6) + " | " +
                ColunaUtils.formatarColuna(endereco.getCep(), 10) + " | " +
                ColunaUtils.formatarColuna(endereco.getCidade(), 12) + " | " +
                ColunaUtils.formatarColuna(endereco.getEstado(), 12);
    }

    private void editarEndereco() {
        listarEnderecos();
        System.out.println("[!] ID do Endereço: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("[!] RUA (pressione [ENTER] para manter atual): ");
        String rua = scanner.nextLine();
        System.out.print("[!] BAIRRO (pressione [ENTER] para manter atual): ");
        String bairro = scanner.nextLine();
        System.out.print("[!] NÚMERO (pressione [ENTER] para manter atual): ");
        String numero = scanner.nextLine();
        System.out.print("[!] CIDADE (pressione [ENTER] para manter atual): ");
        String cidade = scanner.nextLine();
        System.out.print("[!] ESTADO (pressione [ENTER] para manter atual):");
        String estado = scanner.nextLine();
        System.out.print("[!] CEP (pressione [ENTER] para manter atual):");
        String cep = scanner.nextLine();

        Endereco endereco = new Endereco(rua.isEmpty() ? null : rua, bairro.isEmpty() ? null : bairro,
                numero.isEmpty() ? null : numero, cidade.isEmpty() ? null : cidade, estado.isEmpty() ? null : estado);
        if (!cep.isEmpty()) {
            endereco.setCep(cep);
        }
        enderecoService.editar(id, endereco);
    }

    private void excluirEndereco() {
        listarEnderecos();
        System.out.println("[!] ID do Endereço: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        enderecoService.excluirEndereco(id);
    }

    public Endereco selecionarOuCadastrarEndereco() {
        System.out.println(
                "[!] Deseja (1) Selecionar um endereço existente, (2) Cadastrar novo endereço, ou (3) Pular? ");
        int opc = scanner.nextInt();
        scanner.nextLine();
        switch (opc) {
            case 1:
                listarEnderecos();
                System.out.println("[!] ID do Endereço: ");
                int id = scanner.nextInt();
                return enderecoService.buscarEnderecoPorId(id);
            case 2:
                return cadastrarEndereco();
            default:
                return null;

        }
    }

    public Endereco cadastrarOuPular() {
        System.out.println(
                "[!] Deseja (1) Cadastrar um endereço, ou (2) Pular?");
        int opc = scanner.nextInt();
        scanner.nextLine();
        switch (opc) {
            case 1:
                return cadastrarEndereco();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        new EnderecoMenu().imprimirMenu();
    }
}