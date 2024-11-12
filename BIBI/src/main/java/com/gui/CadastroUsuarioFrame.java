package com.gui;

import javax.swing.*;
import com.dao.UsuarioDAO;
import com.models.Usuario;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CadastroUsuarioFrame extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JButton cadastrarButton;
    private UsuarioDAO usuarioDAO;

    public CadastroUsuarioFrame() {
        // Inicializa o DAO
        usuarioDAO = new UsuarioDAO();

        setTitle("Cadastro de Usuário");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adiciona a barra de navegação
        setJMenuBar(Navbar.criarMenuBar(this));

        // Configurações de cores e fontes
        Color backgroundColor = new Color(60, 63, 65);
        Color buttonColor = new Color(0, 122, 204);
        Color textColor = Color.WHITE;
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Configura o painel principal
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout()); // Usa GridBagLayout para centralizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Define margens internas

        // Inicializa os campos de texto e botão
        nomeField = new JTextField(20);
        emailField = new JTextField(20);
        cadastrarButton = new JButton("Cadastrar");

        // Estiliza os campos de texto
        nomeField.setFont(fieldFont);
        nomeField.setForeground(textColor);
        nomeField.setBackground(new Color(80, 83, 85));
        nomeField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Remove a borda padrão

        emailField.setFont(fieldFont);
        emailField.setForeground(textColor);
        emailField.setBackground(new Color(80, 83, 85));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Remove a borda padrão

        // Estiliza o botão
        cadastrarButton.setFont(labelFont);
        cadastrarButton.setBackground(buttonColor);
        cadastrarButton.setForeground(textColor);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ação do botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();

                // Validações
                if (nome.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(CadastroUsuarioFrame.this, "Nome e e-mail são obrigatórios!");
                    return;
                }

                // Cria um novo usuário
                Usuario novoUsuario = new Usuario(nome, email);

                try {
                    // Tenta adicionar o usuário no banco de dados
                    usuarioDAO.adicionarUsuario(novoUsuario);
                    JOptionPane.showMessageDialog(CadastroUsuarioFrame.this, "Usuário cadastrado com sucesso!");

                    // Limpa os campos após o sucesso
                    nomeField.setText("");
                    emailField.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CadastroUsuarioFrame.this, "Erro ao cadastrar o usuário: " + ex.getMessage());
                }
            }
        });

        // Adiciona os componentes ao painel usando GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(cadastrarButton, gbc);

        // Adiciona o painel ao frame
        add(panel);

        // Define o texto das labels com a cor branca
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(textColor);
                c.setFont(labelFont);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroUsuarioFrame();
    }
}
