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
        Endereco endereco = lerDadosEndereco();
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
        System.out.println("-".repeat(90));
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
        System.out.println("-".repeat(90));

    }

    public String imprimirEnderecoPorId(int id) {
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);

        if (id <= 0 || endereco == null) {
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
        System.out.print("[!] ID do Endereço: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Endereco endereco = lerDadosEndereco();
        boolean succ = enderecoService.editar(id, endereco);
        if(succ){
            System.out.println("[!] Endereço editado.");
        } else {
            System.err.println("[!] Não foi possível editar endereço.");
        }
    }

    private void excluirEndereco() {
        listarEnderecos();
        System.out.print("[!] ID do Endereço: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        boolean succ = enderecoService.excluirEndereco(id);
        if(succ){
            System.out.println("[!] Endereço excluído.");
        } else {
            System.err.println("[!] Não foi possível excluir endereço.");
        }
    }
    
    public Endereco lerDadosEndereco(){
        Endereco endereco = new Endereco();
        System.out.print("[!] RUA: ");
        endereco.setRua(scanner.nextLine().trim());

        System.out.print("[!] NÚMERO: ");
        endereco.setNumero(scanner.nextLine().trim());

        System.out.print("[!] BAIRRO: ");
        endereco.setBairro(scanner.nextLine().trim());

        System.out.print("[!] CEP (opcional, pressione [ENTER] para pular): ");
        endereco.setCep(scanner.nextLine().trim());

        System.out.print("[!] CIDADE: ");
        endereco.setCidade(scanner.nextLine().trim());

        System.out.print("[!] ESTADO: ");
        endereco.setEstado(scanner.nextLine().trim());

        return endereco;
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