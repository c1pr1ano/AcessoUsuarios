package br.ufes.acessousuarios.presenter;

import br.ufes.log.LogAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalPresenter {

    private JFrame mainFrame;
    private LogAdapter logAdapter;

    public PrincipalPresenter() {
        // Inicializa o LogAdapter para registro de eventos
        this.logAdapter = new LogAdapter("CSV", "principal_log.csv");
        logAdapter.log("INICIALIZACAO_PRINCIPAL", "Sistema", "Sistema", true, "Aplicação iniciada com sucesso.");

        // Inicializa a interface
        initialize();
    }

    private void initialize() {
        // Criando o frame principal
        mainFrame = new JFrame("Sistema de Acesso de Usuários");
        mainFrame.setBounds(100, 100, 800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Criando o botão de fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharSistema();
            }
        });

        // Adicionando o botão ao frame
        mainFrame.getContentPane().add(btnFechar);

        // Definindo o layout e tornando o frame visível
        mainFrame.setLayout(new java.awt.FlowLayout());
        mainFrame.setVisible(true);
    }

    private void fecharSistema() {
        int opcao = JOptionPane.showConfirmDialog(mainFrame, "Você tem certeza que deseja sair?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            logAdapter.log("FECHAMENTO_PRINCIPAL", "Sistema", "Sistema", true, "Sistema fechado pelo usuário.");
            mainFrame.dispose(); // Fecha o frame
            System.exit(0); // Encerra o aplicativo
        }
    }

    public void exibir() {
        mainFrame.setVisible(true);
    }
}