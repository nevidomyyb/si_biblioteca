package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Exemplar;
import com.pedro.service.ExemplarService;
import com.pedro.utils.ColunaUtils;

public class ExemplarMenu {
    
    private IO io = new IO();
    private Scanner scanner;
    private ExemplarService exemplarService = new ExemplarService();

    public ExemplarMenu() {
        scanner = new Scanner(System.in);
    }

    public void imprimirMenu() {
        List<String> opcoesExemplar = new ArrayList<String>(Arrays.asList(
            "1. Cadastrar Exemplar",
            "2. Consultar Exemplares",
            "3. Editar Exemplar",
            "4. Excluir Exemplar",
            "5. Voltar"
        ));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesExemplar, "MENU EXEMPLAR");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarExemplar();
                    break;
                case 2:
                    listarExemplares();
                    break;
                case 3:
                    editarExemplar();
                    break;
                case 4:
                    excluirExemplar();
                    break;
                default:
                    System.err.println("[!] Opção Inválida");
            }
            System.out.println();
            opc = io.imprimirMenuRetornandoOpcao(opcoesExemplar, "MENU EXEMPLAR");
        }
    }

    private void listarExemplares() {
        LivroMenu livroMenu = new LivroMenu();
        List<Exemplar> exemplares = exemplarService.listarExemplares();
        System.out.println("--------------------------------------------------EXEMPLAR----------------------------------------------------------");
        System.out.println(
            "| " + ColunaUtils.formatarColuna("ID", 6) + " | " +
            ColunaUtils.formatarColuna("ID Livro", 6) + " | " + ColunaUtils.formatarColuna("Título", 30) +
            " | " + ColunaUtils.formatarColuna("Edição", 12) + " | " + ColunaUtils.formatarColuna("Sinopse", 45) + " |"
        );
        System.out.println("-".repeat(116));
        if (!exemplares.isEmpty()) {
            for (Exemplar exemplar : exemplares) {
                System.out.println(
                    "| " + ColunaUtils.formatarColuna(String.valueOf(exemplar.getId()), 6) + " | " +
                    livroMenu.imprimirLivro(exemplar.getLivroId()) + " |"
                );
            }
        }
        System.out.println("-".repeat(116));
    }

    private void cadastrarExemplar() {
        System.out.print("[!] ID do Livro: ");
        int livroId = scanner.nextInt();
        scanner.nextLine();

        boolean succ = exemplarService.cadastrarExemplar(new Exemplar(livroId));
        if (!succ) {
            System.err.println("[!] Não foi possível cadastrar o exemplar.");
            return;
        }
        System.out.println("[!] Exemplar cadastrado com sucesso.");
    }

    private void editarExemplar() {
        System.out.print("[!] ID do Exemplar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID do Livro: ");
        int livroId = scanner.nextInt();
        scanner.nextLine();

        boolean succ = exemplarService.editarExemplar(id, new Exemplar(livroId));
        if (!succ) {
            System.err.println("[!] Não foi possível editar o exemplar.");
            return;
        }
        System.out.println("[!] Exemplar editado.");
    }

    private void excluirExemplar() {
        System.out.print("[!] ID do Exemplar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean succ = exemplarService.excluirExemplar(id);
        if (!succ) {
            System.err.println("[!] Não foi possível excluir o exemplar.");
            return;
        }
        System.out.println("[!] Exemplar excluído.");
    }
}