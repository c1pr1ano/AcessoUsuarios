package br.ufes.acessousuarios.state;

public class UsuarioState {

    // Definindo os possíveis estados do usuário
    public enum Estado {
        ATIVO,
        INATIVO,
        BLOQUEADO,
        PENDENTE
    }

    private Estado estadoAtual;

    public UsuarioState(Estado estadoInicial) {
        this.estadoAtual = estadoInicial;
    }

    // Getter e Setter para o estado atual
    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Estado estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    // Método para verificar se o usuário está ativo
    public boolean isAtivo() {
        return estadoAtual == Estado.ATIVO;
    }

    // Método para verificar se o usuário está inativo
    public boolean isInativo() {
        return estadoAtual == Estado.INATIVO;
    }

    // Método para verificar se o usuário está bloqueado
    public boolean isBloqueado() {
        return estadoAtual == Estado.BLOQUEADO;
    }

    // Método para verificar se o usuário está pendente
    public boolean isPendente() {
        return estadoAtual == Estado.PENDENTE;
    }

    @Override
    public String toString() {
        return "Estado atual do usuário: " + estadoAtual;
    }
}