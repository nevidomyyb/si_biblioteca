package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Editora;
import com.pedro.models.Endereco;
import com.pedro.service.EditoraService;
import com.pedro.utils.ColunaUtils;

public class EditoraMenu {

    private Scanner scanner;
    private EditoraService editoraService;
    private IO io;

    public EditoraMenu() {
        scanner = new Scanner(System.in);
        editoraService = new EditoraService();
        io = new IO();
    }

    public void imprimirMenu() {
        List<String> opcoesEditora = new ArrayList<>(Arrays.asList(
                "1. Cadastrar editora",
                "2. Consultar editoras",
                "3. Editar editora",
                "4. Excluir editora",
                "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesEditora, "MENU EDITORA");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarEditora();
                    break;
                case 2:
                    listarEditoras();
                    break;
                case 3:
                    editarEditora();
                    break;
                case 4:
                    excluirEditora();
                    break;

                default:
                    System.err.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesEditora, "MENU EDITORA");
        }
    }

    private void cadastrarEditora() {
        Editora editora = lerDadosEditora();
        boolean succ = editoraService.cadastrarEditora(editora);
        if (succ) {
            System.out.println("[!] Editora cadastrada com sucesso.");
        } else {
            System.err.println("[!] Não foi possível cadastrar editora.");
        }
    }

    public void listarEditoras() {
        List<Editora> editoras = editoraService.listar();
        System.out.println("--------------------EDITORAS-----------------");
        System.out.println(
                "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Nome", 14) +
                        " | " + ColunaUtils.formatarColuna("CNPJ", 14) + " |");
        System.out.println("-".repeat(45));
        if (!editoras.isEmpty()) {
            for (Editora editora : editoras) {
                System.out.println(
                        "| " + ColunaUtils.formatarColuna(String.valueOf(editora.getId()), 6) + " | " +
                                ColunaUtils.formatarColuna(editora.getNome(), 14) + " | " +
                                ColunaUtils.formatarColuna(editora.getCnpj(), 14) + " |");
            }
        }
        System.out.println("-".repeat(45));
    }

    public String imprimirEditora(int id) {
        Editora editora = editoraService.buscarEditoraPorId(id);
        if (editora == null) {
            return ColunaUtils.formatarColuna(null, 12);
        }
        return ColunaUtils.formatarColuna(editora.getNome(), 12);
    }

    private void editarEditora() {
        listarEditoras();

        System.out.print("[!] ID da Editora: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Editora editora = lerDadosEditora();
        boolean succ = editoraService.editarEditora(id, editora);
        if (succ) {
            System.out.println("[!] Editora editada.");
        } else {
            System.err.println("[!] Não foi possível editar editora.");
        }
    }

    private Editora lerDadosEditora() {
        Editora editora = new Editora();
        EnderecoMenu enderecoMenu = new EnderecoMenu();

        System.out.print("[!] Nome: ");
        editora.setNome(scanner.nextLine().trim());

        System.out.print("[!] CNPJ: ");
        editora.setCnpj(scanner.nextLine().trim());

        System.out.println("ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        if (endereco != null) {
            editora.setEnderecoMatrizId(endereco.getId());
        } else {
            editora.setEnderecoMatrizId(0);
        }

        return editora;
    }

    private void excluirEditora() {
        listarEditoras();
        try {
            System.out.print("[!] ID da Editora: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            boolean succ = editoraService.excluirEditora(id);
            if (succ) {
                System.out.println("[!] Editora excluída.");
            } else {
                System.err.println("[!] Não foi possívele excluir editora.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EditoraMenu().imprimirMenu();
    }

}
