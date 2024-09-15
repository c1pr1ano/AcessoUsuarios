/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.acessousuarios.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author fabio
 */
public class ConfiguracaoView extends JFrame {

    private JTextField txtLinguagem;
    private JTextField txtTema;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public ConfiguracaoView() {
        setTitle("Configurações do Sistema");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Criação dos componentes
        JLabel lblLinguagem = new JLabel("Linguagem:");
        JLabel lblTema = new JLabel("Tema:");
        txtLinguagem = new JTextField(20);
        txtTema = new JTextField(20);
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Configuração do layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(lblLinguagem);
        panel.add(txtLinguagem);
        panel.add(lblTema);
        panel.add(txtTema);
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Métodos de acesso aos campos e botões
    public JTextField getTxtLinguagem() {
        return txtLinguagem;
    }

    public JTextField getTxtTema() {
        return txtTema;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    // Métodos para exibir mensagens de sucesso ou erro
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
