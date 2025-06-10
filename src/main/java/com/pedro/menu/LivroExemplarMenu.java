package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;

public class LivroExemplarMenu {
    private IO io = new IO();

    public void imprimirMenu(){
        List<String> opcoesLivroExemplar = new ArrayList<String>(Arrays.asList(
            "1. Livros",
            "2. Exemplares",
            "3. Voltar"
        ));

        int opc = io.imprimirMenuRetornandoOpcao(opcoesLivroExemplar, "MENU LIVRO/EXEMPLAR");
        while (opc != 3) {
            switch (opc) {
                case 1:
                    LivroMenu livroMenu = new LivroMenu();
                    livroMenu.imprimirMenu();
                    break;
                case 2:
                    ExemplarMenu exemplarMenu = new ExemplarMenu();
                    exemplarMenu.imprimirMenu();
                    break;
                default:
                    System.err.println("[!] Opção Inválida");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesLivroExemplar, "MENU LIVRO/EXEMPLAR");
        }
    }
}
