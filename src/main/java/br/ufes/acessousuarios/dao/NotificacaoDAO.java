package br.ufes.acessousuarios.dao;

import br.ufes.acessousuarios.model.Notificacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {

    private Connection connection;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NotificacaoDAO() {
        try {
            this.connection = SQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar o erro de forma adequada em um projeto real
        }
    }

    // Cria a tabela de notificações se não existir
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS notificacoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario_id INTEGER NOT NULL, " +
                "mensagem TEXT NOT NULL, " +
                "data_envio TEXT NOT NULL, " +
                "lida BOOLEAN NOT NULL DEFAULT 0, " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id))";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insere uma nova notificação
    public void inserirNotificacao(Notificacao notificacao) {
        String sql = "INSERT INTO notificacoes (usuario_id, mensagem, data_envio, lida) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificacao.getUsuarioId());
            stmt.setString(2, notificacao.getMensagem());
            stmt.setString(3, notificacao.getDataEnvio().format(FORMATTER)); // Formata LocalDateTime para String
            stmt.setBoolean(4, notificacao.isLida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todas as notificações de um usuário
    public List<Notificacao> listarNotificacoes(int usuarioId) {
        String sql = "SELECT * FROM notificacoes WHERE usuario_id = ?";
        List<Notificacao> notificacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notificacao notificacao = new Notificacao(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getString("mensagem"),
                        LocalDateTime.parse(rs.getString("data_envio"), FORMATTER), // Converte String para LocalDateTime
                        rs.getBoolean("lida")
                );
                notificacoes.add(notificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notificacoes;
    }

    // Marca uma notificação como lida
    public void marcarComoLida(int id) {
        String sql = "UPDATE notificacoes SET lida = 1 WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deleta uma notificação
    public void deletarNotificacao(int id) {
        String sql = "DELETE FROM notificacoes WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}