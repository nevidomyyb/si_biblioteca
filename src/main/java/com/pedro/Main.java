package com.pedro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;
import com.pedro.menu.LoginMenu;

public class Main {
    
    public static void main(String[] args) {
        IO io = new IO();
        LoginMenu loginMenu = new LoginMenu();

        List<String> opcoesMenuPrincipal = new ArrayList<String>(Arrays.asList(
            "1. Entrar",
            "2. Cadastrar",
            "3. Sair"
        ));

        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "INÍCIO");
        while (opc != 3) {
            switch (opc) {
                case 1:
                    loginMenu.login();
                    break;
                case 2:
                    loginMenu.cadastro();
                    break;
                default:
                    System.err.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesMenuPrincipal, "INÍCIO");
        }

    }

}
