package com.pedro.config;

import java.util.List;
import java.util.Scanner;

public class IO {

    private  Scanner scanner;

    public IO(){
        scanner = new Scanner(System.in);
    }

    private void limparTerminal() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static String centerString(String text, int width) {
        if (text == null) {
            text = "";
        }
        if (text.length() >= width) {
            return text;
        }
        int paddingSize = width - text.length();
        int padStart = paddingSize / 2;
        int padEnd = paddingSize - padStart;

        return " ".repeat(padStart) + text + " ".repeat(padEnd);
    }

    public void criarMenu(List<String> listaString, String titulo) {
        int maiorValor = 0;
        if (listaString != null && !listaString.isEmpty()) {
            for (String s : listaString) {
                if (s != null && s.length() > maiorValor) {
                    maiorValor = s.length();
                }
            }
        }
        String separador = "-".repeat(maiorValor);
        System.out.println(separador);
        System.out.println(centerString(titulo, maiorValor));
        System.out.println(separador);
        if (listaString != null) {
            for (String item : listaString) {
                System.out.println(item);

            }
        }
        System.out.println(separador);
    }
    public int imprimirMenuRetornandoOpcao(List<String> listaString, String titulo) {
        while (true) {
            criarMenu(listaString, titulo);
            String linhaEntrada = scanner.nextLine().trim();
            try {
                int opc = Integer.parseInt(linhaEntrada);
                limparTerminal();
                return opc;
            } catch (NumberFormatException e){

            }
        }
    }

    public static void main(String[] args) {
        // apenas para testes
        List<String> opcoesMenu = List.of(
                "1. Cadastrar Aluno",
                "2. Listar Alunos",
                "3. Sair"
        );
        String tituloMenu = "Menu Principal da Biblioteca";

        System.out.println("Bem-vindo ao sistema!");
        IO io = new IO();
        int escolha = io.imprimirMenuRetornandoOpcao(opcoesMenu, tituloMenu);

        System.out.println("Você escolheu a opção: " + escolha);
    }

}
