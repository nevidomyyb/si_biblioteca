package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Livro;
import com.pedro.service.LivroService;
import com.pedro.utils.ColunaUtils;

public class LivroMenu {
    
    private IO io = new IO();
    private Scanner scanner;
    private LivroService livroService = new LivroService();

    public LivroMenu(){
        scanner = new Scanner(System.in);
    }

    public void imprimirMenu(){
        List<String> opcoesLivro = new ArrayList<String>(Arrays.asList(
            "1. Cadastrar Livro",
            "2. Consultar Livros",
            "3. Editar Livro",
            "4. Excluir Livro",
            "5. Voltar"
        ));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesLivro, "MENU LIVRO");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3: 
                    editarLivro();
                case 4:
                    excluirLivro();
                    break;
                default:
                    System.err.println("[!] Opção Inválida");
            }
            System.out.println();
            opc = io.imprimirMenuRetornandoOpcao(opcoesLivro, "MENU LIVRO");
        }
    }

    private void listarLivros(){
        List<Livro> livros = livroService.listarLivros();
        System.out.println("---------------------------------------------------LIVRO-----------------------------------------------------------");
        System.out.println(
        "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Título", 12) +
        " | " + ColunaUtils.formatarColuna("Edição", 12) + " | " + ColunaUtils.formatarColuna("Sinopse", 25) +
        " | " + ColunaUtils.formatarColuna("ID Edit.", 12) + " | " + ColunaUtils.formatarColuna("ID Gên.", 12) + 
        " | " + ColunaUtils.formatarColuna("ID Autor", 12) + " |"
        );
        System.out.println("-".repeat(115));
        if(!livros.isEmpty()){
            for(Livro livro : livros){
                System.out.println(
                    "| " + ColunaUtils.formatarColuna(String.valueOf(livro.getId()), 6) + " | " +
                    ColunaUtils.formatarColuna(livro.getTitulo(), 12) + " | " + ColunaUtils.formatarColuna(livro.getEdicao(), 12) +
                    " | " + ColunaUtils.formatarColuna(livro.getSinopse(), 25) + " | " + ColunaUtils.formatarColuna(String.valueOf(livro.getEditorId()), 12) +
                    " | " + ColunaUtils.formatarColuna(String.valueOf(livro.getGeneroId()), 12) + " | " + ColunaUtils.formatarColuna(String.valueOf(livro.getAutorId()), 12) +
                    " |"
                );
            }
        }
        System.out.println("-".repeat(115));
    }

    public String imprimirLivro(int id){
        Livro livro = livroService.buscarLivroPorId(id);
        if(id <= 0 || livro == null) {
            return ColunaUtils.formatarColuna(null, 6) + 
            " | " + ColunaUtils.formatarColuna(null, 12) + " | " +
            ColunaUtils.formatarColuna(null, 12) + " | " +
            ColunaUtils.formatarColuna(null, 25);
        }
        return ColunaUtils.formatarColuna(String.valueOf(livro.getId()), 6) + " | " +
        ColunaUtils.formatarColuna(livro.getTitulo(), 12) + " | " + ColunaUtils.formatarColuna(livro.getEdicao(), 12) +
        " | " + ColunaUtils.formatarColuna(livro.getSinopse(), 25);
    }

    public String imprimirLivroParaLeitores(int id){
        Livro livro = livroService.buscarLivroPorId(id);
        if(id <= 0 || livro == null){
            return ColunaUtils.formatarColuna(null, 12) + " | " +
            ColunaUtils.formatarColuna(null, 12) + " | " + ColunaUtils.formatarColuna(null, 25);
        } 
        return ColunaUtils.formatarColuna(livro.getTitulo(), 12) + " | " + ColunaUtils.formatarColuna(livro.getEdicao(), 12) 
        + " | " + ColunaUtils.formatarColuna(livro.getSinopse(), 25);
    }

    private void cadastrarLivro(){
        System.out.print("[!] Título: ");
        String titulo = scanner.nextLine();

        System.out.print("[!] ID Editora: ");
        int editora_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID Gênero: ");
        int genero_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID Autor: ");
        int autor_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] Edição: ");
        String edicao = scanner.nextLine();

        System.out.print("[!] Sinopse: ");
        String sinopse = scanner.nextLine();

        boolean succ = livroService.cadastrarLivro(new Livro(titulo, editora_id, edicao, sinopse, genero_id, autor_id));
        if(!succ){
            System.out.println("[!] Não foi possível cadastrar livro.");
        }

        System.out.println("[!] Livro cadastrado com sucesso.");
    }

    private void editarLivro(){
        System.out.print("[!] ID do Livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] Título: ");
        String titulo = scanner.nextLine();

        System.out.print("[!] ID da editora: ");
        int editora_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID do Gênero: ");
        int genero_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID do Autor: ");
        int autor_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] Edição:  ");
        String edicao = scanner.nextLine();

        System.out.print("[!] Sinopse: ");
        String sinopse = scanner.nextLine();
     
        boolean succ = livroService.editarLivro(id, new Livro(titulo, editora_id, edicao, sinopse, genero_id, autor_id));
        if(!succ){
            System.err.println("[!] Não foi possível editar o livro.");
        }

        System.out.println("[!] Livro editado.");
    }

    private void excluirLivro(){
        System.out.print("[!] ID do Livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean succ = livroService.removerLivro(id);
        if(!succ){
            System.err.println("[!] Não foi possível excluir o livro.");
        }
        System.out.println("[!] Livro excluído.");
    }

}
