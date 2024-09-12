package br.ufes.acessousuarios.presenter;

import br.ufes.acessousuarios.dao.UsuarioDAO;
import br.ufes.acessousuarios.model.Notificacao;
import br.ufes.acessousuarios.model.Usuario;
import br.ufes.acessousuarios.service.NotificacaoService; // Importa o serviço para notificações
import br.ufes.log.LogAdapter;
import java.util.List;

public class UsuarioListPresenter {

    private UsuarioDAO usuarioDAO;
    private NotificacaoService notificacaoService; // Adiciona a referência ao serviço de notificações
    private LogAdapter logAdapter;

    public UsuarioListPresenter() {
        this.usuarioDAO = new UsuarioDAO();
        this.notificacaoService = new NotificacaoService(); // Inicializa o serviço de notificações
        this.logAdapter = new LogAdapter("CSV", "usuario_log.csv"); // Exemplo de configuração do log
    }

    // Método para alterar a senha do usuário
    public void alterarSenha(int usuarioId, String novaSenha) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(usuarioId);

            if (usuario == null) {
                logAdapter.log("ALTERAR_SENHA", "Sistema", "Sistema", false, "Usuário com ID " + usuarioId + " não encontrado.");
                throw new IllegalArgumentException("Usuário não encontrado.");
            }

            if (!usuario.getTipo().equals("usuario")) {
                logAdapter.log("ALTERAR_SENHA", "Sistema", "Sistema", false, "Usuário com ID " + usuarioId + " não tem permissão para alterar senha.");
                throw new SecurityException("Usuário não autorizado.");
            }

            usuario.setSenha(novaSenha);
            usuarioDAO.atualizarUsuario(usuario);
            logAdapter.log("ALTERAR_SENHA", "Sistema", "Sistema", true, "Senha alterada com sucesso para o usuário com ID " + usuarioId + ".");
        } catch (Exception e) {
            logAdapter.log("ALTERAR_SENHA", "Sistema", "Sistema", false, "Erro ao alterar senha para o usuário com ID " + usuarioId + ": " + e.getMessage());
            throw e; // Re-throw para que o chamador possa lidar com a exceção
        }
    }

    // Método para visualizar notificações do usuário
    public void visualizarNotificacoes(int usuarioId) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(usuarioId);

            if (usuario == null) {
                logAdapter.log("VISUALIZAR_NOTIFICACOES", "Sistema", "Sistema", false, "Usuário com ID " + usuarioId + " não encontrado. Deslogando a sessão.");
                // Aqui deslogue a sessão do usuário
                deslogarUsuario(); // Método para deslogar o usuário
                return; // Sai do método se o usuário não for encontrado
            }

            // Chama o método específico para listar e exibir notificações
            List<Notificacao> notificacoes = notificacaoService.listarNotificacoes(usuarioId);
            view.exibirNotificacoes(notificacoes); // Assumindo que há um método na view para exibir notificações

            logAdapter.log("VISUALIZAR_NOTIFICACOES", "Sistema", "Sistema", true, "Notificações visualizadas com sucesso para o usuário com ID " + usuarioId + ".");
        } catch (Exception e) {
            logAdapter.log("VISUALIZAR_NOTIFICACOES", "Sistema", "Sistema", false, "Erro ao visualizar notificações para o usuário com ID " + usuarioId + ": " + e.getMessage());
            throw e; // Re-throw para que o chamador possa lidar com a exceção
        }
    }

    // Método fictício para deslogar o usuário, deve ser implementado conforme necessário
    private void deslogarUsuario() {
        // Implementar lógica de deslogin, como limpar sessão, redirecionar, etc.
        System.out.println("Usuário deslogado.");
    }
}