package br.ufes.acessousuarios.observer;

import br.ufes.acessousuarios.model.Notificacao;
import br.ufes.log.LogAdapter;

public class NotificacaoObserver implements Observer {

    private LogAdapter logAdapter;

    public NotificacaoObserver(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void atualizar(String mensagem) {
        // Exibe a notificação no console GARANTIA
        System.out.println("Notificação recebida: " + mensagem);

        // Grava a notificação no log
        logAdapter.log("Recebimento de Notificação", "Notificação", "Sistema", true, null);
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

        logAdapter.log(
            "Processamento de Notificação",
            "Notificação para Usuário " + notificacao.getUsuarioId(),
            "Sistema",
            true,
            null
        );
    }
}