package com.gui;

import javax.swing.*;
import com.services.TarefaService;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CadastroTarefaFrame extends JFrame {
    private JTextField descricaoField, setorField;
    private JComboBox<String> usuarioComboBox, prioridadeComboBox, statusComboBox;
    private JButton cadastrarButton;
    private TarefaService tarefaService;
    private List<Integer> usuariosIds;

    public CadastroTarefaFrame() {
        tarefaService = new TarefaService();
        usuariosIds = new ArrayList<>();

        // Configurações de cores e fontes
        Color backgroundColor = new Color(60, 63, 65);
        Color buttonColor = new Color(0, 122, 204);
        Color textColor = Color.WHITE;
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        setTitle("Cadastro de Tarefa");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Adiciona a barra de navegação
        setJMenuBar(Navbar.criarMenuBar(this));

        // Inicialização dos campos
        descricaoField = new JTextField(20);
        setorField = new JTextField(20);
        descricaoField.setFont(fieldFont);
        setorField.setFont(fieldFont);

        // ComboBox para selecionar o usuário
        usuarioComboBox = new JComboBox<>();
        carregarUsuarios();

        // ComboBox para selecionar a prioridade
        prioridadeComboBox = new JComboBox<>(new String[] { "Baixa", "Média", "Alta" });
        statusComboBox = new JComboBox<>(new String[] { "A fazer", "Fazendo", "Pronto" });

        // Estilização dos ComboBoxes
        usuarioComboBox.setFont(fieldFont);
        prioridadeComboBox.setFont(fieldFont);
        statusComboBox.setFont(fieldFont);
        // Inicialização dos campos
        descricaoField = new JTextField(20);
        setorField = new JTextField(20);

        // Estiliza os campos de texto
        descricaoField.setFont(fieldFont);
        descricaoField.setBackground(new Color(43, 43, 43)); // Fundo escuro
        descricaoField.setForeground(Color.WHITE); // Texto branco
        descricaoField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Remove a borda padrão

        setorField.setFont(fieldFont);
        setorField.setBackground(new Color(43, 43, 43)); // Fundo escuro
        setorField.setForeground(Color.WHITE); // Texto branco
        setorField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Remove a borda padrão

        // Botão para cadastrar a tarefa
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(labelFont);
        cadastrarButton.setBackground(buttonColor);
        cadastrarButton.setForeground(textColor);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = descricaoField.getText();
                String setor = setorField.getText();
                String prioridade = (String) prioridadeComboBox.getSelectedItem();
                String status = (String) statusComboBox.getSelectedItem();
                int usuarioId = usuariosIds.get(usuarioComboBox.getSelectedIndex());

                try {
                    tarefaService.adicionarTarefa(descricao, usuarioId, setor, prioridade, status);
                    JOptionPane.showMessageDialog(CadastroTarefaFrame.this, "Tarefa cadastrada com sucesso!");
                    descricaoField.setText("");
                    setorField.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CadastroTarefaFrame.this,
                            "Erro ao cadastrar a tarefa: " + ex.getMessage());
                }
            }
        });

        // Painel de layout
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adiciona os componentes ao painel usando GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        panel.add(descricaoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Setor:"), gbc);
        gbc.gridx = 1;
        panel.add(setorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Usuário:"), gbc);
        gbc.gridx = 1;
        panel.add(usuarioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Prioridade:"), gbc);
        gbc.gridx = 1;
        panel.add(prioridadeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(cadastrarButton, gbc);

        // Configurações de estilização das labels
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(textColor);
                c.setFont(labelFont);
            }
        }

        add(panel);
        setVisible(true);
    }

    private void carregarUsuarios() {
        String sql = "SELECT id, nome FROM usuarios";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibi", "postgres",
                "postgres");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int usuarioId = rs.getInt("id");
                String nome = rs.getString("nome");
                usuarioComboBox.addItem(nome);
                usuariosIds.add(usuarioId);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários.");
        }
    }

    public static void main(String[] args) {
        new CadastroTarefaFrame();
    }
}
