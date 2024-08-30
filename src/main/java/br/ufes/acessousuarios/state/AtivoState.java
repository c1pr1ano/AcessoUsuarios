package br.ufes.acessousuarios.state;

public class AtivoState extends UsuarioState {

    private String atividadeAtual;

    public AtivoState() {
        super(Estado.ATIVO); // Estado inicial para um usuário ativo é ATIVO
        this.atividadeAtual = "";
    }

    // Getter e Setter para atividadeAtual
    public String getAtividadeAtual() {
        return atividadeAtual;
    }

    public void setAtividadeAtual(String atividadeAtual) {
        this.atividadeAtual = atividadeAtual;
    }

    @Override
    public String toString() {
        return "Usuário Ativo - Atividade Atual: " + atividadeAtual;
    }
}