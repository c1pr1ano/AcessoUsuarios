package br.ufes.acessousuarios.presenter;

import br.ufes.acessousuarios.model.Notificacao;
import br.ufes.acessousuarios.service.NotificacaoService;
import br.ufes.log.LogAdapter;
import java.util.List;

public class NotificacaoListPresenter {
    
    private NotificacaoService notificacaoService;
    private NotificacaoListView view;
    private LogAdapter logAdapter;

    // Construtor
    public NotificacaoListPresenter(NotificacaoListView view) {
        this.notificacaoService = new NotificacaoService();
        this.view = view;
        this.logAdapter = new LogAdapter("CSV", "notificacao_log.csv"); // Exemplo de configuração
    }

    // Método para listar as notificações e atualizar a view
    public void listarNotificacoes(int usuarioId) {
        try {
            List<Notificacao> notificacoes = notificacaoService.listarNotificacoes(usuarioId);
            view.exibirNotificacoes(notificacoes);
            logAdapter.log("LISTAGEM_NOTIFICACOES", "Sistema", "Sistema", true, "Notificações listadas com sucesso.");
        } catch (Exception e) {
            logAdapter.log("LISTAGEM_NOTIFICACOES", "Sistema", "Sistema", false, "Erro ao listar notificações: " + e.getMessage());
            view.exibirMensagemErro("Erro ao listar notificações.");
        }
    }

    // Método para criar uma nova notificação
    public void criarNotificacao(int usuarioId, String mensagem) {
        try {
            Notificacao notificacao = new Notificacao(usuarioId, mensagem, java.time.LocalDateTime.now(), false);
            notificacaoService.criarNotificacao(notificacao);
            logAdapter.log("CRIACAO_NOTIFICACAO", "Sistema", "Sistema", true, "Notificação criada com sucesso.");
            view.exibirMensagemSucesso("Notificação criada com sucesso.");
        } catch (Exception e) {
            logAdapter.log("CRIACAO_NOTIFICACAO", "Sistema", "Sistema", false, "Erro ao criar notificação: " + e.getMessage());
            view.exibirMensagemErro("Erro ao criar notificação.");
        }
    }

    // Método para marcar uma notificação como lida
    public void marcarNotificacaoComoLida(int id) {
        try {
            notificacaoService.marcarNotificacaoComoLida(id);
            logAdapter.log("MARCAR_LIDA_NOTIFICACAO", "Sistema", "Sistema", true, "Notificação marcada como lida com sucesso.");
            view.exibirMensagemSucesso("Notificação marcada como lida com sucesso.");
        } catch (Exception e) {
            logAdapter.log("MARCAR_LIDA_NOTIFICACAO", "Sistema", "Sistema", false, "Erro ao marcar notificação como lida: " + e.getMessage());
            view.exibirMensagemErro("Erro ao marcar notificação como lida.");
        }
    }

    // Método para deletar uma notificação
    public void deletarNotificacao(int id) {
        try {
            notificacaoService.deletarNotificacao(id);
            logAdapter.log("DELECAO_NOTIFICACAO", "Sistema", "Sistema", true, "Notificação deletada com sucesso.");
            view.exibirMensagemSucesso("Notificação deletada com sucesso.");
        } catch (Exception e) {
            logAdapter.log("DELECAO_NOTIFICACAO", "Sistema", "Sistema", false, "Erro ao deletar notificação: " + e.getMessage());
            view.exibirMensagemErro("Erro ao deletar notificação.");
        }
    }
}