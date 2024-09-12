package br.ufes.acessousuarios.service;

import br.ufes.acessousuarios.dao.UsuarioDAO;
import br.ufes.acessousuarios.model.Usuario;
import br.ufes.log.LogAdapter;

public class LoginService {
    private UsuarioDAO usuarioDAO;
    private LogAdapter logAdapter;

    // Construtor
    public LoginService() {
        this.usuarioDAO = new UsuarioDAO();
        this.logAdapter = new LogAdapter("CSV", "login_log.csv"); // Exemplo de configuração
    }

    // Método responsável por autenticar o usuário
    public Usuario autenticar(String username, String senha) throws Exception {
        // Verifica se os parâmetros fornecidos não estão vazios
        if (username == null || username.isEmpty() || senha == null || senha.isEmpty()) {
            String errorMessage = "Usuário e senha devem ser preenchidos.";
            logAdapter.log("LOGIN", username, "Sistema", false, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Chama o DAO para buscar o usuário no banco de dados
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario != null) {
            // Verifica se a senha é válida
            if (usuario.getSenha().equals(senha)) {
                logAdapter.log("LOGIN", username, "Sistema", true, "Login bem-sucedido");
                return usuario; // Login bem-sucedido, retorna o objeto Usuario
            } else {
                logAdapter.log("LOGIN", username, "Sistema", false, "Senha inválida");
                return null; // Senha inválida
            }
        } else {
            logAdapter.log("LOGIN", username, "Sistema", false, "Usuário não encontrado");
            return null; // Usuário não encontrado
        }
    }
}