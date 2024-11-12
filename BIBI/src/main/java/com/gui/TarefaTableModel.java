package com.gui;

import com.models.Tarefa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TarefaTableModel extends AbstractTableModel {
    private List<Tarefa> tarefas;
    private String[] colunas = {"ID", "Descrição", "Usuário", "Setor", "Prioridade", "Status", "Data Cadastro"};

    public TarefaTableModel() {
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
        fireTableDataChanged(); // Notifica a tabela que os dados foram alterados
    }

    @Override
    public int getRowCount() {
        return tarefas != null ? tarefas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarefa tarefa = tarefas.get(rowIndex);
        switch (columnIndex) {
            case 0: return tarefa.getId();
            case 1: return tarefa.getDescricao();
            case 2: return tarefa.getUsuarioId(); // Ajustar conforme necessário
            case 3: return tarefa.getSetor();
            case 4: return tarefa.getPrioridade();
            case 5: return tarefa.getStatus();
            case 6: return tarefa.getDataCadastro();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // Método auxiliar para obter tarefa por linha
    public Tarefa getTarefaAt(int rowIndex) {
        return tarefas.get(rowIndex);
    }
}
