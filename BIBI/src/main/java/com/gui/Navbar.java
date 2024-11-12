package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar {

    public static JMenuBar criarMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(33, 150, 243)); // Define a cor de fundo azul para a barra de menu

        // Define fonte e cor para o texto dos itens de menu
        Font menuFont = new Font("SansSerif", Font.PLAIN, 14);
        Color menuTextColor = Color.WHITE;

        // Menu de navegação principal
        JMenu menu = new JMenu("Navegação");
        menu.setForeground(menuTextColor);
        menu.setFont(menuFont);

        // Itens do menu para Cadastro de Usuários, Cadastro de Tarefas e Gerenciamento de Tarefas
        JMenuItem itemCadastroUsuario = new JMenuItem("Cadastro de Usuário");
        JMenuItem itemCadastroTarefa = new JMenuItem("Cadastro de Tarefa");
        JMenuItem itemGerenciamentoTarefa = new JMenuItem("Gerenciamento de Tarefas");

        // Estiliza os itens de menu
        estilizarMenuItem(itemCadastroUsuario, menuFont, menuTextColor);
        estilizarMenuItem(itemCadastroTarefa, menuFont, menuTextColor);
        estilizarMenuItem(itemGerenciamentoTarefa, menuFont, menuTextColor);

        // Adiciona ações para cada item de menu
        itemCadastroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroUsuarioFrame().setVisible(true);
                frame.dispose(); // Fecha o frame atual
            }
        });

        itemCadastroTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroTarefaFrame().setVisible(true);
                frame.dispose(); // Fecha o frame atual
            }
        });

        itemGerenciamentoTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciamentoTarefasFrame().setVisible(true);
                frame.dispose(); // Fecha o frame atual
            }
        });

        // Adiciona os itens ao menu
        menu.add(itemCadastroUsuario);
        menu.add(itemCadastroTarefa);
        menu.add(itemGerenciamentoTarefa);

        // Adiciona o menu à barra de navegação
        menuBar.add(menu);

        return menuBar;
    }

    // Método auxiliar para estilizar cada JMenuItem
    private static void estilizarMenuItem(JMenuItem item, Font font, Color textColor) {
        item.setFont(font);
        item.setForeground(textColor);
        item.setBackground(new Color(33, 150, 243)); // Fundo azul para os itens de menu
        item.setOpaque(true); // Necessário para a cor de fundo funcionar em todos os sistemas
        item.setBorderPainted(false); // Remove as bordas dos itens de menu

        // Adiciona efeito visual ao passar o mouse
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item.setBackground(new Color(25, 118, 210)); // Tom de azul mais escuro ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item.setBackground(new Color(33, 150, 243)); // Restaura o fundo azul original
            }
        });
    }
}
