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
        Autor autor = lerDadosAutor();
        boolean succ = autorService.cadastrarAutor(autor);
        if(succ) {
            System.out.println("[!] Autor cadastrado com sucesso.");
        } else {
            System.err.println("[!] Não foi possível cadastrar autor");
        }
    }

    public void listarAutores() {
        List<Autor> autores = autorService.listar();
        System.out.println("----------------------------AUTORES----------------------------");
        System.out.println(
            "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Nome", 12) + 
            " | " + ColunaUtils.formatarColuna("Data de Nascimento", 20) + " | " + ColunaUtils.formatarColuna("Pseudônimo", 12) + " |"
        );
        System.out.println("-".repeat(63));
        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println(
                    "| " + ColunaUtils.formatarColuna(String.valueOf(autor.getId()), 6) + " | " + ColunaUtils.formatarColuna(autor.getNome(), 12) +
                    " | " + ColunaUtils.formatarColuna(autor.getDataNascimento() == null ? null : autor.getDataNascimento().toString(), 20) + " | " + ColunaUtils.formatarColuna(autor.getPseudonimo(), 12) + " |"
                );
            }
        }
        System.out.println("-".repeat(63));
    }

    public String imprimirAutor(int id){
        Autor autor = autorService.buscarAutorPorId(id);
        if(autor == null){
            return ColunaUtils.formatarColuna(null, 12) + " | " + 
            ColunaUtils.formatarColuna(null, 12) + " | " +
            ColunaUtils.formatarColuna(null, 12);
        }
        return ColunaUtils.formatarColuna(autor.getNome(), 12) + " | " +
        ColunaUtils.formatarColuna(autor.getPseudonimo(), 12) + " | " + 
        ColunaUtils.formatarColuna(autor.getDataNascimento().toString(), 12);
    }

    public void editarAutor() {
        listarAutores();

        System.out.print("[!] ID do Autor: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Autor autor = lerDadosAutor();
        boolean succ = autorService.editar(id, autor);
        if(succ){
            System.out.println("[!] Autor editado.");
        } else {
            System.out.println("[!] Não foi possível editar autor.");
        }
    }

    public Autor lerDadosAutor(){
        Autor autor = new Autor();
        System.out.print("[!] Nome: ");
        autor.setNome(scanner.nextLine().trim());

        System.out.print("[!] Data de Nascimento (opcional, pressione [ENTER] para pular): ");
        String dataNascimento = scanner.nextLine().trim();

        System.out.print("[!] Pseudônimo (opcional, pressione [ENTER] para pular): ");
        autor.setPseudonimo(scanner.nextLine().trim());

        if(!dataNascimento.isEmpty()){
            Date novaData = DataUtils.stringToSqlDate(dataNascimento);
            autor.setDataNascimento(novaData);
        } else {
            autor.setDataNascimento(null);
        }

        return autor;
    }

    public void excluirAutor() {
        try {
            listarAutores();
            System.out.println("\n[!] ID do autor: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            boolean succ = autorService.excluir(id);
            if(succ){
                System.out.println("[!] Autor excluído.");
            } else {
                System.err.println("[!] Não foi possível excluir o autor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AutorMenu().imprimirMenu();
    }

}
