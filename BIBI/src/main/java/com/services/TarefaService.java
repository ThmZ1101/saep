package com.services;

import com.dao.TarefaDAO;
import com.models.Tarefa;
import java.sql.SQLException;
import java.util.List;

public class TarefaService {

    private TarefaDAO tarefaDAO;

    // Construtor onde o TarefaDAO é inicializado
    public TarefaService() {
        try {
            // Inicializando o TarefaDAO, que por sua vez vai criar a conexão com o banco de dados
            this.tarefaDAO = new TarefaDAO();  
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o TarefaDAO: " + e.getMessage());
        }
    }

    // Método para adicionar uma nova tarefa
    public void adicionarTarefa(String descricao, int usuarioId, String setor, String prioridade, String status) throws SQLException {
        // Validações
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }
        if (setor == null || setor.trim().isEmpty()) {
            throw new IllegalArgumentException("Setor não pode ser vazio.");
        }
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        if (prioridade == null || (!prioridade.equals("Baixa") && !prioridade.equals("Média") && !prioridade.equals("Alta"))) {
            throw new IllegalArgumentException("Prioridade inválida.");
        }
        if (status == null || (!status.equals("A fazer") && !status.equals("Fazendo") && !status.equals("Pronto"))) {
            throw new IllegalArgumentException("Status inválido.");
        }

        // Criação de uma nova tarefa
        Tarefa novaTarefa = new Tarefa(0, descricao, usuarioId, setor, prioridade, null, status);  // ID gerado automaticamente pelo banco
        tarefaDAO.adicionarTarefa(novaTarefa);  // Chama o método do DAO para salvar no banco de dados
    }

    // Método para listar todas as tarefas
    public List<Tarefa> listarTarefas() throws SQLException {
        return tarefaDAO.listarTarefas();  // Chama o DAO para buscar as tarefas do banco
    }

    // Método para editar uma tarefa
    public void editarTarefa(Tarefa tarefa) throws SQLException {
        if (tarefa == null || tarefa.getId() <= 0) {
            throw new IllegalArgumentException("Tarefa inválida.");
        }
        
        // Chamando o DAO para editar a tarefa
        tarefaDAO.editarTarefa(tarefa);
    }

    // Método para editar uma tarefa utilizando os dados diretamente
    public void editarTarefa(int id, String descricao, String setor, String prioridade, String status) throws SQLException {
        // Cria a tarefa com os dados atualizados
        Tarefa tarefaAtualizada = new Tarefa(id, descricao, 0, setor, prioridade, null, status); // O '0' pode ser um valor padrão ou um id de usuário se necessário
        tarefaDAO.editarTarefa(tarefaAtualizada);
    }
    
    // Método para excluir uma tarefa
    public void excluirTarefa(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        
        // Chamando o DAO para excluir a tarefa
        tarefaDAO.excluirTarefa(id);
    }
}
