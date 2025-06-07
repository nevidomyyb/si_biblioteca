package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;



public class GerencialMenu {
    private IO io;
    private EditoraMenu editoraMenu;
    private AutorMenu autorMenu;
    private GeneroMenu generoMenu;
    private EnderecoMenu enderecoMenu;

    public GerencialMenu(){
        io = new IO();
        editoraMenu = new EditoraMenu();
        autorMenu = new AutorMenu();
        generoMenu = new GeneroMenu();
        enderecoMenu = new EnderecoMenu();
    }

    public void imprimirMenuGerencial(){
        List<String> opcoesGerencial = new ArrayList<String>(Arrays.asList("1. Editora", "2. Autor", "3. Gênero", "4. Endereço", "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");

        while (opc != 5) {
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
                default:
                    System.out.println("Opção inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");
        }

    }

    public static void main(String[] args) {
        GerencialMenu gerencial = new GerencialMenu();
        gerencial.imprimirMenuGerencial();
    }
}
