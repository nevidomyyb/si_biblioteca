package com.pedro.utils;

public class ColunaUtils {
    
    public static String formatarColuna(String texto, int tamanho){
        if(texto == null){
            texto = "N/A";
        }

        if(texto.length() > tamanho){
            return texto.substring(0, tamanho);
        }

        int espacosParaAdicionar = tamanho - texto.length();
        String espacos = " ".repeat(espacosParaAdicionar);
        return texto + espacos;
    }
}
