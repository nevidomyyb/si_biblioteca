package com.pedro.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conn;

    public Conexao() {
        try {
            // Carregar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar ao banco de dados
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sis_biblioteca?useSSL=false&serverTimezone=UTC",
                    "root",
                    "12345678"
            );

            System.out.println("Conexão criada com sucesso!");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Falha na conexão");
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public static void main(String[] args) {
        Conexao c = new Conexao();
    }
}
