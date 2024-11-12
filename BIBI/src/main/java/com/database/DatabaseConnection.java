package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bibi"; // Substitua pelo nome do seu banco de dados
    private static final String USER = "postgres"; // Seu usuário
    private static final String PASSWORD = "postgres"; // Sua senha

    public static Connection conectar() {
        Connection conn = null;
        try {
            // Registra o driver
            Class.forName("org.postgresql.Driver");
            // Estabelece a conexão
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
