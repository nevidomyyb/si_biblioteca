package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;

public class PrincipalMenu {
    
    private IO io = new IO();

    public void imprimirMenuPrincipal(){
        List<String> opcoesMenuPrincipal = new ArrayList<String>(Arrays.asList("1. Área livros", "2. Área Leitores", "3. Área Operações", "4. Área Funcionários", "5. Área Gerencial", "6. Sair"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "MENU PRINCIPAL");

        while (opc != 6) {
            switch (opc) {
                case 1:
                    System.out.println("MENU AINDA NÃO FOI FEITO");
                    break;
                case 2:
                    LeitorMenu leitorMenu = new LeitorMenu();
                    leitorMenu.imprimirMenu();
                    break;
                case 3:
                    OperacaoMenu operacaoMenu = new OperacaoMenu();
                    operacaoMenu.imprimirMenuOperacao();
                case 4:
                    System.out.println("AINDA NAO FOI FEITO");
                case 5:
                    GerencialMenu gerencialMenu = new GerencialMenu();
                    gerencialMenu.imprimirMenuGerencial();
                    break;

                default:
                    System.out.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "MENU PRINCIPAL");
        }
    }

}
