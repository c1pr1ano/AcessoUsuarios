package br.ufes.acessousuarios.model;

import java.time.LocalDateTime;

public class Notificacao {

    private int id;
    private int usuarioId;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private boolean lida;

    // Construtor com todos os parâmetros
    public Notificacao(int id, int usuarioId, String mensagem, LocalDateTime dataEnvio, boolean lida) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
    }

    // Construtor sem id (para inserção)
    public Notificacao(int usuarioId, String mensagem, LocalDateTime dataEnvio, boolean lida) {
        this.usuarioId = usuarioId;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}