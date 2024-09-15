/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.acessousuarios.view;

import br.ufes.acessousuarios.model.Notificacao;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabio
 */
public class NotificacaoListView extends JFrame {
    private JTable tblNotificacoes;
    private JButton btnCriar;
    private JButton btnMarcarLida;
    private JButton btnDeletar;
    private JTextField txtMensagem;
    
    public NotificacaoListView() {
        setTitle("Gerenciamento de Notificações");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }
    
    private void initComponents() {
        // Inicializa os componentes
        JScrollPane scrollPane = new JScrollPane();
        tblNotificacoes = new JTable();
        btnCriar = new JButton("Criar Notificação");
        btnMarcarLida = new JButton("Marcar como Lida");
        btnDeletar = new JButton("Deletar Notificação");
        txtMensagem = new JTextField(20);
        
        // Configuração da tabela de notificações
        tblNotificacoes.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Mensagem", "Data", "Lida"}
        ));
        
        scrollPane.setViewportView(tblNotificacoes);
        
        // Layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Mensagem:"));
        panel.add(txtMensagem);
        panel.add(btnCriar);
        panel.add(btnMarcarLida);
        panel.add(btnDeletar);
        
        getContentPane().add(scrollPane, "Center");
        getContentPane().add(panel, "South");
    }

    // Métodos de interação com o Presenter
    
    public String getMensagemNotificacao() {
        return txtMensagem.getText();
    }

    public int getNotificacaoSelecionada() {
        int selectedRow = tblNotificacoes.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tblNotificacoes.getValueAt(selectedRow, 0); // Retorna o ID da notificação selecionada
        }
        return -1;
    }

    public void exibirNotificacoes(List<Notificacao> notificacoes) {
        DefaultTableModel model = (DefaultTableModel) tblNotificacoes.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Notificacao notificacao : notificacoes) {
            model.addRow(new Object[]{notificacao.getId(), notificacao.getMensagem(), notificacao.getData(), notificacao.isLida()});
        }
    }

    public void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public JButton getBtnCriar() {
        return btnCriar;
    }

    public JButton getBtnMarcarLida() {
        return btnMarcarLida;
    }

    public JButton getBtnDeletar() {
        return btnDeletar;
    }
}
