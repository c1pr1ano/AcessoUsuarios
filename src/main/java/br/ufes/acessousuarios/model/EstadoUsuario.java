package br.ufes.acessousuarios.model;

public class EstadoUsuario {
    
    private int id; // Identificador único do estado do usuário
    private String descricao; // Descrição do estado (por exemplo, "Ativo", "Inativo", etc.)

    // Construtor
    public EstadoUsuario(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Métodos Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "EstadoUsuario{" +
               "id=" + id +
               ", descricao='" + descricao + '\'' +
               '}';
    }
}