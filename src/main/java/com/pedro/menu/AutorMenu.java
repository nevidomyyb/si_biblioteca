package com.pedro.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Autor;
import com.pedro.service.AutorService;
import com.pedro.utils.ColunaUtils;
import com.pedro.utils.DataUtils;

public class AutorMenu {
    private Scanner scanner;
    private AutorService autorService;
    private IO io;

    public AutorMenu() {
        scanner = new Scanner(System.in);
        autorService = new AutorService();
        io = new IO();
    }

    public void imprimirMenu() {
        List<String> opcoesAutor = new ArrayList<String>(Arrays.asList(
                "1. Cadastrar autor",
                "2. Consultar autores",
                "3. Editar autor",
                "4. Excluir autor",
                "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesAutor, "MENU AUTORES");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarAutor();
                    break;
                case 2:
                    listarAutores();
                    break;
                case 3:
                    editarAutor();
                    break;
                case 4:
                    excluirAutor();
                    break;
                default:
                    System.err.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesAutor, "MENU AUTORES");
        }
    }

    public void cadastrarAutor() {
        System.out.println("[!] Nome: ");
        String nome = scanner.nextLine();

        System.out.println("[!] Data Nascimento (opcional, pressione [ENTER] para pular): ");
        String dataNascimento = scanner.nextLine();

        System.out.println("[!] Peseudônimo (opcional, pressione [ENTER] para pular): ");
        String pseudonimo = scanner.nextLine();

        Autor autor = new Autor(nome);

        if (!dataNascimento.isEmpty()) {
            Date dataNascimentoDate = DataUtils.stringToSqlDate(dataNascimento);
            autor.setDataNascimento(dataNascimentoDate);
        }

        if (!pseudonimo.isEmpty()) {
            autor.setPseudonimo(pseudonimo);
        }

        autorService.cadastrarAutor(autor);
    }

    public void listarAutores() {
        List<Autor> autores = autorService.listar();
        System.out.println("---------------------------AUTORES--------------------------");
        System.out.println(
            "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Nome", 12) + 
            " | " + ColunaUtils.formatarColuna("Data de Nascimento", 20) + " | " + ColunaUtils.formatarColuna("Pseudônimo", 12) + " |"
        );
        System.out.println("------------------------------------------------------------");
        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println(
                    "| " + ColunaUtils.formatarColuna(String.valueOf(autor.getId()), 6) + " | " + ColunaUtils.formatarColuna(autor.getNome(), 12) +
                    " | " + ColunaUtils.formatarColuna(autor.getDataNascimento() == null ? null : autor.getDataNascimento().toString(), 20) + " | " + ColunaUtils.formatarColuna(autor.getPseudonimo(), 12) + " |"
                );
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    public void editarAutor() {
        listarAutores();
        System.out.println("[!] ID do Autor: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("[!] Nome (pressione [ENTER] para manter atual): ");
        String nome = scanner.nextLine();
        System.out.println("[!] Data de Nascimento (pressione [ENTER] para manter atual): ");
        String dataNascimento = scanner.nextLine();
        System.out.println("[!] Pseudônimo (pressione [ENTER] para manter atual): ");
        String pseudonimo = scanner.nextLine();

        Autor autor = new Autor(nome.isEmpty() ? null : nome);
        autor.setDataNascimento(dataNascimento.isEmpty() ? null
                : DataUtils.stringToSqlDate(
                        dataNascimento));
        autor.setPseudonimo(pseudonimo.isEmpty() ? null : pseudonimo);
        autorService.editar(id, autor);
    }

    public void excluirAutor() {
        try {
            listarAutores();
            System.out.println();
            System.out.println("[!] ID do autor: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            autorService.excluir(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
