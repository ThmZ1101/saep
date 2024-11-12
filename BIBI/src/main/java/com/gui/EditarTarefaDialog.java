package com.gui;

import com.models.Tarefa;
import com.services.TarefaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarTarefaDialog extends JDialog {
    private JTextField descricaoField, setorField;
    private JComboBox<String> prioridadeComboBox, statusComboBox;
    private Tarefa tarefa;
    private TarefaService tarefaService;

    public EditarTarefaDialog(JFrame parent, Tarefa tarefa, TarefaService tarefaService) {
        super(parent, "Editar Tarefa", true);
        this.tarefa = tarefa;
        this.tarefaService = tarefaService;

        setLayout(new GridLayout(6, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Campos de edição
        descricaoField = new JTextField(tarefa.getDescricao());
        setorField = new JTextField(tarefa.getSetor());
        prioridadeComboBox = new JComboBox<>(new String[]{"Baixa", "Média", "Alta"});
        prioridadeComboBox.setSelectedItem(tarefa.getPrioridade());
        statusComboBox = new JComboBox<>(new String[]{"A fazer", "Fazendo", "Pronto"});
        statusComboBox.setSelectedItem(tarefa.getStatus());

        // Adiciona os componentes
        add(new JLabel("Descrição:"));
        add(descricaoField);
        add(new JLabel("Setor:"));
        add(setorField);
        add(new JLabel("Prioridade:"));
        add(prioridadeComboBox);
        add(new JLabel("Status:"));
        add(statusComboBox);

        // Botão de salvar
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tarefaService.editarTarefa(tarefa.getId(), descricaoField.getText(), setorField.getText(),
                            (String) prioridadeComboBox.getSelectedItem(), (String) statusComboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(EditarTarefaDialog.this, "Tarefa atualizada com sucesso!");
                    dispose();  // Fecha o dialog
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EditarTarefaDialog.this, "Erro ao atualizar tarefa: " + ex.getMessage());
                }
            }
        });

        add(salvarButton);
    }
}
