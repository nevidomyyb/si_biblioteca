package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;



public class Gerencial {
    private IO io = new IO();

    private EditoraMenu editoraMenu = new EditoraMenu();
    private AutorMenu autorMenu = new AutorMenu();
    private GeneroMenu generoMenu = new GeneroMenu();

    public void imprimirMenuGenero(){
        List<String> opcoesGeneros = new ArrayList<String>(Arrays.asList("1. Cadastrar Gênero", "2. Listar Gêneros", "3. Editar Gênero", "4. Remover Gênero", "5. Voltar"));

        int opc = io.imprimirMenuRetornandoOpcao(opcoesGeneros, "MENU GÊNERO");

        while(opc != 5){
            switch (opc) {
                case 1:
                    generoMenu.inserirGenero();
                    break;
                case 2:
                    generoMenu.listarGeneros();
                    break;
                case 3:
                    generoMenu.editarGenero();
                    break;
                case 4:
                    generoMenu.excluirGenero();
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesGeneros, "MENU GÊNERO");
        }
    }


    public void imprimirMenuAutor(){
        List<String> opcoesAutor = new ArrayList<String>(Arrays.asList("1. Cadastrar Autor", "2. Listar Autores", "3. Editar Autor", "4. Remover Autor", "5. Voltar"));
        
        int opc = io.imprimirMenuRetornandoOpcao(opcoesAutor, "MENU AUTOR");

        while(opc != 5){
            switch (opc) {
                case 1:
                    autorMenu.inserirAutor();
                    break;
                case 2:
                    autorMenu.listarAutores();
                    break;
                case 3:
                    autorMenu.editarAutor();
                    break;
                case 4:
                    autorMenu.excluirAutor();
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesAutor, "MENU AUTOR");
        }
    }

    private void imprimirMenuEditora(){
        List<String> opcoesEditora = new ArrayList<String>(Arrays.asList("1. Cadastrar Editora", "2. Listar Editoras", "3. Editar Editora", "4. Remover Editora", "5. Voltar"));
        
        int opc = io.imprimirMenuRetornandoOpcao(opcoesEditora, "MENU EDITORA");

        while (opc != 5) {
            switch (opc) {
                case 1:
                    editoraMenu.cadastrarEditora();
                    break;
                case 2:
                    editoraMenu.listarEditora();
                    break;
                case 3:
                    editoraMenu.editarEditora();
                    break;
                case 4:
                    editoraMenu.excluirEditora();   
                    break;
                default:
                    System.out.println("Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesEditora, "MENU EDITORA");
        }

    }

    public void imprimirMenuGerencial(){
        List<String> opcoesGerencial = new ArrayList<String>(Arrays.asList("1. Editora", "2. Autor", "3. Gênero", "4. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");

        while (opc != 4) {
            switch (opc) {
                case 1:
                    imprimirMenuEditora();
                    break;
                case 2:
                    imprimirMenuAutor();
                case 3:
                    imprimirMenuGenero();
                default:
                    System.out.println("Opção inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");
        }

    }

    public static void main(String[] args) {
        Gerencial gerencial = new Gerencial();

        gerencial.imprimirMenuGerencial();
    }
}
