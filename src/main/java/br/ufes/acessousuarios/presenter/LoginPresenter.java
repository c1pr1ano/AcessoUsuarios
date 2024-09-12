package br.ufes.acessousuarios.presenter;

import br.ufes.acessousuarios.model.Usuario;
import br.ufes.acessousuarios.service.LoginService;
import br.ufes.acessousuarios.view.LoginView;
import br.ufes.log.LogAdapter;

public class LoginPresenter {

    private LoginView view;
    private LoginService loginService;
    private LogAdapter logAdapter;

    public LoginPresenter(LoginView view, String formatoLog, String filePath) {
        this.view = view;
        this.loginService = new LoginService(); // Serviço de login responsável pela lógica de autenticação
        this.logAdapter = new LogAdapter(formatoLog, filePath);
        configurarView();
    }

    private void configurarView() {
        // Configurações iniciais e listeners da View
        view.getBtnLogin().addActionListener((e) -> autenticarUsuario());
        view.getBtnCancelar().addActionListener((e) -> cancelarLogin());
    }

    private void autenticarUsuario() {
        try {
            // Captura os dados de login da view
            String username = view.getTxtUsuario().getText();
            String senha = new String(view.getTxtSenha().getPassword());

            // Chama o serviço de autenticação
            Usuario usuario = loginService.autenticar(username, senha);

            if (usuario != null) {
                // Login bem-sucedido
                logAdapter.log("Login bem-sucedido", "Login de Usuário", username, true, null);
                view.exibirMensagem("Bem-vindo, " + usuario.getNome() + "!");
                
                // Redireciona para a próxima tela, se aplicável
                // view.redirecionarTelaPrincipal(usuario);
                
            } else {
                // Login falhou
                logAdapter.log("Tentativa de login falhou", "Login de Usuário", username, false, "Usuário ou senha inválidos");
                view.exibirErro("Usuário ou senha inválidos.");
            }

        } catch (Exception ex) {
            // Caso ocorra algum erro durante o processo, grava no log e exibe a mensagem de erro
            logAdapter.log("Erro ao realizar login", "Login de Usuário", "Desconhecido", false, ex.getMessage());
            view.exibirErro("Erro ao realizar login: " + ex.getMessage());
        }
    }

    private void cancelarLogin() {
        try {
            // Limpa os campos de login
            view.getTxtUsuario().setText("");
            view.getTxtSenha().setText("");

            logAdapter.log("Login cancelado", "Login de Usuário", "Desconhecido", true, null);
            view.exibirMensagem("Login cancelado.");

        } catch (Exception ex) {
            // Em caso de erro ao limpar ou cancelar o login, exibe a mensagem de erro
            logAdapter.log("Erro ao cancelar login", "Login de Usuário", "Desconhecido", false, ex.getMessage());
            view.exibirErro("Erro ao cancelar login: " + ex.getMessage());
        }
    }
}