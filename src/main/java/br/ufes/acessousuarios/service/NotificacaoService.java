package br.ufes.acessousuarios.service;

import br.ufes.acessousuarios.dao.NotificacaoDAO;
import br.ufes.acessousuarios.model.Notificacao;
import java.util.List;

public class NotificacaoService {

    private NotificacaoDAO notificacaoDAO;

    public NotificacaoService() {
        this.notificacaoDAO = new NotificacaoDAO();
    }

    public List<Notificacao> listarNotificacoes(int usuarioId) throws Exception {
        return notificacaoDAO.listarNotificacoes(usuarioId);
    }

    public void criarNotificacao(Notificacao notificacao) throws Exception {
        notificacaoDAO.inserirNotificacao(notificacao);
    }

    public void marcarNotificacaoComoLida(int id) throws Exception {
        notificacaoDAO.marcarComoLida(id);
    }

    public void deletarNotificacao(int id) throws Exception {
        notificacaoDAO.deletarNotificacao(id);
    }
}