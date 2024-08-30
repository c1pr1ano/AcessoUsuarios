package br.ufes.acessousuarios.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AutorizarUsuarioCommand implements Command {

    private final Connection connection;
    private final String nomeUsuario;

    public AutorizarUsuarioCommand(Connection connection, String nomeUsuario) {
        this.connection = connection;
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public void execute() {
        try {
            autorizarUsuario();
        } catch (SQLException e) {
            // Lida com exceção de SQL (por exemplo, registra em log ou notifica o usuário)
            System.err.println("Erro ao autorizar usuário: " + e.getMessage());
        }
    }

    private void autorizarUsuario() throws SQLException {
        String sql = "UPDATE usuarios SET autorizado = true WHERE nome = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomeUsuario);
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Usuário autorizado com sucesso: " + nomeUsuario);
            } else {
                System.out.println("Nenhum usuário encontrado para autorizar: " + nomeUsuario);
            }
        }
    }
}