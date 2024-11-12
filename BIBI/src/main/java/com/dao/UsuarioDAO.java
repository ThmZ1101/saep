package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.models.Usuario;

public class UsuarioDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/bibi";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuário cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao cadastrar o usuário: " + e.getMessage());
        }
    }
}
