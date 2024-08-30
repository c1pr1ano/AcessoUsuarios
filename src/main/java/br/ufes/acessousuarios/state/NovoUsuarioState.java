package br.ufes.acessousuarios.state;

public class NovoUsuarioState extends UsuarioState {

    // Estado adicional específico para um novo usuário, se necessário
    private boolean emailVerificado;
    private boolean perfilCompleto;

    public NovoUsuarioState() {
        super(Estado.PENDENTE); // Estado inicial para um novo usuário é PENDENTE
        this.emailVerificado = false;
        this.perfilCompleto = false;
    }

    // Getter e Setter para emailVerificado
    public boolean isEmailVerificado() {
        return emailVerificado;
    }

    public void setEmailVerificado(boolean emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    // Getter e Setter para perfilCompleto
    public boolean isPerfilCompleto() {
        return perfilCompleto;
    }

    public void setPerfilCompleto(boolean perfilCompleto) {
        this.perfilCompleto = perfilCompleto;
    }

    // Método para verificar se o usuário está pronto para ser ativado
    public boolean isProntoParaAtivacao() {
        return emailVerificado && perfilCompleto;
    }

    @Override
    public String toString() {
        return "Novo Usuário - Estado: " + getEstadoAtual() +
               ", Email Verificado: " + emailVerificado +
               ", Perfil Completo: " + perfilCompleto;
    }
}