package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.service.AlunoService;


public class GerencialMenu {
    private IO io;
    private EditoraMenu editoraMenu;
    private AutorMenu autorMenu;
    private GeneroMenu generoMenu;
    private EnderecoMenu enderecoMenu;
    private AlunoService alunoService;

    public GerencialMenu(){
        io = new IO();
        editoraMenu = new EditoraMenu();
        autorMenu = new AutorMenu();
        generoMenu = new GeneroMenu();
        enderecoMenu = new EnderecoMenu();
    }

    public void imprimirMenuGerencial(){
        List<String> opcoesGerencial = new ArrayList<String>(Arrays.asList("1. Editora", "2. Autor", "3. Gênero", "4. Endereço", "5. Consultar Aluno por Locação","6. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");

        while (opc != 6) {
            switch (opc) {
                case 1:
                    editoraMenu.imprimirMenu();
                    break;
                case 2:
                    autorMenu.imprimirMenu();
                    break;
                case 3:
                    generoMenu.imprimirMenu();
                    break;
                case 4:
                    enderecoMenu.imprimirMenu();
                    break;
                case 5:
                    consultarAlunoPorLocacao();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");
        }

    }

    private void consultarAlunoPorLocacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID da locação: ");
        String locacaoIDString = scanner.nextLine().trim();
        int locacaoID = Integer.parseInt(locacaoIDString);
        Aluno aluno = alunoService.consultarAlunoPorLocacaoId(locacaoID);
        if (aluno == null) {
            System.out.println("Locação não identificada.");
        } else {
            System.out.println("Aluno: ");
            System.out.println(aluno.getId());
            System.out.println(aluno.getNome());
            System.out.println(aluno.getCpf());
            System.out.println(aluno.getCurso());
            System.out.println(aluno.getEmail());
        }
    }

    public static void main(String[] args) {
        GerencialMenu gerencial = new GerencialMenu();
        gerencial.imprimirMenuGerencial();
    }
}
