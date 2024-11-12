package com.dao;

import com.models.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection connection;

    public TarefaDAO() throws SQLException {
        // Estabelece a conexão com o banco de dados
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibi", "postgres", "postgres");
    }

    // Método para adicionar uma nova tarefa no banco de dados
    public void adicionarTarefa(Tarefa tarefa) throws SQLException {
        // A SQL para inserir uma nova tarefa
        String sql = "INSERT INTO tarefas (descricao, usuario_id, setor, prioridade, data_cadastro, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Atribui os valores para os parâmetros da consulta SQL
            stmt.setString(1, tarefa.getDescricao());
            stmt.setInt(2, tarefa.getUsuarioId());
            stmt.setString(3, tarefa.getSetor());
            stmt.setString(4, tarefa.getPrioridade());
            
            // Se a data de cadastro não for fornecida, usamos a data atual
            Timestamp dataCadastro = tarefa.getDataCadastro() != null ? new Timestamp(tarefa.getDataCadastro().getTime()) : new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, dataCadastro);
            
            stmt.setString(6, tarefa.getStatus());
            
            // Executa a atualização no banco de dados
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao adicionar tarefa no banco de dados: " + e.getMessage());
        }
    }

    // Método para listar todas as tarefas do banco de dados
    public List<Tarefa> listarTarefas() throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getInt("usuario_id"),
                    rs.getString("setor"),
                    rs.getString("prioridade"),
                    rs.getTimestamp("data_cadastro"),
                    rs.getString("status")
                );
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao listar tarefas: " + e.getMessage());
        }
        
        return tarefas;
    }

    // Método para editar uma tarefa
    public void editarTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefas SET descricao = ?, usuario_id = ?, setor = ?, prioridade = ?, status = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setInt(2, tarefa.getUsuarioId());
            stmt.setString(3, tarefa.getSetor());
            stmt.setString(4, tarefa.getPrioridade());
            stmt.setString(5, tarefa.getStatus());
            stmt.setInt(6, tarefa.getId());  // Utiliza o ID para localizar a tarefa a ser atualizada
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao atualizar a tarefa: " + e.getMessage());
        }
    }

    // Método para excluir uma tarefa
    public void excluirTarefa(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir a tarefa: " + e.getMessage());
        }
    }

    // Outros métodos como listar, etc.
}
