package com.pedro.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class Senha {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH_BYTES = 16;

    public static String hashSenha(String senha) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH_BYTES];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        md.update(salt);
        byte[] hashedSenha = md.digest(senha.getBytes());

        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashedSenhaBase64 = Base64.getEncoder().encodeToString(hashedSenha);
        return hashedSenhaBase64 + "$" + saltBase64;
    }
    public static boolean verificarSenha(String senha, String hashSenha) throws NoSuchAlgorithmException {

        if (hashSenha == null || !hashSenha.contains("$")) {
            throw new IllegalArgumentException("Formato inválido para hashSenha.");
        }
        String[] partes = hashSenha.split("\\$");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Formato inválido para hashSenha.");
        }
        byte[] salt = Base64.getDecoder().decode(partes[1]);
        byte[] hashSenhaCorreta = Base64.getDecoder().decode(partes[0]);
        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        md.update(salt);
        byte[] hashSenhaRecebida = md.digest(senha.getBytes());

        return MessageDigest.isEqual(hashSenhaCorreta, hashSenhaRecebida);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String senha = "bolsonaroMeuPastor";
        String senhahashed = Senha.hashSenha(senha);
        System.out.println(hashSenha(senhahashed));
        System.out.println(verificarSenha(senha, senhahashed));

    }

}
