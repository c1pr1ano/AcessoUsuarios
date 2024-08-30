package br.ufes.acessousuarios.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnviarNotificacaoCommand implements Command {

    private static final Logger logger = Logger.getLogger(EnviarNotificacaoCommand.class.getName());

    private final Connection connection;
    private final List<String> destinatarios;
    private final String mensagem;
    private final String usuarioLogado;

    public EnviarNotificacaoCommand(Connection connection, List<String> destinatarios, String mensagem, String usuarioLogado) {
        this.connection = connection;
        this.destinatarios = destinatarios;
        this.mensagem = mensagem;
        this.usuarioLogado = usuarioLogado;
    }

    @Override
    public void execute() {
        try {
            enviarNotificacao();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao enviar notificação", e);
        }
    }

    private void enviarNotificacao() throws SQLException {
        String sql = "INSERT INTO notificacoes (usuario, mensagem, data_envio, autor) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String destinatario : destinatarios) {
                statement.setString(1, destinatario);
                statement.setString(2, mensagem);
                statement.setString(3, usuarioLogado);
                statement.addBatch();
            }
            int[] result = statement.executeBatch();

            int totalNotificacoes = 0;
            for (int count : result) {
                totalNotificacoes += count;
            }
            logger.info("Notificações enviadas com sucesso para " + totalNotificacoes + " destinatários.");

            // Log da operação
            logger.info(String.format("Notificação enviada: mensagem=\"%s\", autor=%s", mensagem, usuarioLogado));
        }
    }
}