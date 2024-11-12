package com.gui;

import com.models.Tarefa;
import com.services.TarefaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GerenciamentoTarefasFrame extends JFrame {
    private TarefaService tarefaService;
    private JTable tabelaTarefas;
    private TarefaTableModel tabelaModel;

    public GerenciamentoTarefasFrame() {
        tarefaService = new TarefaService();  // Serviço de tarefas
  // Adiciona a barra de navegação
  setJMenuBar(Navbar.criarMenuBar(this));
        // Configurações do JFrame
        setTitle("Gerenciamento de Tarefas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a janela

        // Cria e configura a tabela
        tabelaModel = new TarefaTableModel();
        tabelaTarefas = new JTable(tabelaModel);
        tabelaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaTarefas);

        // Botões para ações de gerenciamento
        JPanel panelBotoes = new JPanel();
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        // Adiciona os eventos de clique para os botões
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarTarefa();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTarefa();
            }
        });

        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);

        // Layout do frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Carregar as tarefas da base de dados
        try {
            carregarTarefas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tarefas: " + ex.getMessage());
        }
    }

    // Método para carregar as tarefas no JTable
    private void carregarTarefas() throws SQLException {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        tabelaModel.setTarefas(tarefas);
    }

    // Método para editar a tarefa selecionada
    private void editarTarefa() {
        int linhaSelecionada = tabelaTarefas.getSelectedRow();
        if (linhaSelecionada != -1) {
            Tarefa tarefa = tabelaModel.getTarefaAt(linhaSelecionada);
            // Exibir uma janela de edição (ou implementar diretamente a edição aqui)
            new EditarTarefaDialog(this, tarefa, tarefaService).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para editar.");
        }
    }

    // Método para excluir a tarefa selecionada
    private void excluirTarefa() {
        int linhaSelecionada = tabelaTarefas.getSelectedRow();
        if (linhaSelecionada != -1) {
            Tarefa tarefa = tabelaModel.getTarefaAt(linhaSelecionada);
            try {
                tarefaService.excluirTarefa(tarefa.getId());
                carregarTarefas(); // Atualiza a tabela após a exclusão
                JOptionPane.showMessageDialog(this, "Tarefa excluída com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir tarefa: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GerenciamentoTarefasFrame().setVisible(true);
        });
    }
}
