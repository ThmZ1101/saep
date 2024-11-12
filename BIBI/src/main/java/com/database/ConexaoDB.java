package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/bibi";  // Substitua pelo nome do seu banco de dados
        String usuario = "postgres";  // Substitua pelo seu usuário do banco
        String senha = "postgres";  // Substitua pela sua senha do banco
        return DriverManager.getConnection(url, usuario, senha);
    }
}
