package br.ufes.acessousuarios.model;

public class Usuario {

    private int id;
    private String nome;
    private String senha;
    private String tipo; // Pode ser "Usuário" ou "Administrador"
    private String dataCadastro;
    private String status;

    public Usuario(int id, String nome, String senha, String tipo, String dataCadastro, String status) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
}