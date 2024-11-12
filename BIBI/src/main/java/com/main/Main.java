package com.main;

import javax.swing.SwingUtilities; // Importação necessária para SwingUtilities
// import com.gui.CadastroUsuarioFrame;
//import com.gui.CadastroTarefaFrame;
import com.gui.GerenciamentoTarefasFrame;
public class Main {
    public static void main(String[] args) {
        // Executa a aplicação na thread de eventos Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              // new CadastroUsuarioFrame(); // Cria uma nova instância da Janela Inicial
         //new CadastroTarefaFrame(); // Cria uma nova instância da Janela Inicial
          new GerenciamentoTarefasFrame(); // Cria uma nova instância da Janela Inicial
            }
        });
    }
}
