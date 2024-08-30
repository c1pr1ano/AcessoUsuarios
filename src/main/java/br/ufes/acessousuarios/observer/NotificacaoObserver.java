package br.ufes.acessousuarios.observer;

import br.ufes.acessousuarios.model.Notificacao;

public class NotificacaoObserver implements Observer {

    @Override
    public void atualizar(String mensagem) {
        // Aqui você pode implementar a lógica para tratar a atualização de notificação.
        System.out.println("Notificação recebida: " + mensagem);
    }

    // Método para processar uma notificação
    public void processarNotificacao(Notificacao notificacao) {
        String mensagem = String.format(
            "Notificação para usuário ID %d: %s (Enviada em: %s, Lida: %s)",
            notificacao.getUsuarioId(),
            notificacao.getMensagem(),
            notificacao.getDataEnvio(),
            notificacao.isLida() ? "Sim" : "Não"
        );
        atualizar(mensagem);
    }
}