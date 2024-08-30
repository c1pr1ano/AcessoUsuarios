package br.ufes.acessousuarios.state;

public class InativoState extends UsuarioState {

    // Atributos específicos para o estado inativo, se houver
    private String motivoInatividade;

    public InativoState() {
        super(Estado.INATIVO); // Estado inicial para um usuário inativo é INATIVO
        this.motivoInatividade = "";
    }

    // Getter e Setter para motivoInatividade
    public String getMotivoInatividade() {
        return motivoInatividade;
    }

    public void setMotivoInatividade(String motivoInatividade) {
        this.motivoInatividade = motivoInatividade;
    }

    @Override
    public String toString() {
        return "Usuário Inativo - Motivo: " + motivoInatividade;
    }
}
