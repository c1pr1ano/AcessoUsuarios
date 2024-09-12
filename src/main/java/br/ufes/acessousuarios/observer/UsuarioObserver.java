package br.ufes.acessousuarios.observer;

import br.ufes.acessousuarios.model.Usuario;
import br.ufes.log.LogAdapter;

public class UsuarioObserver implements Observer {

    private LogAdapter logAdapter;

    // Construtor que recebe o LogAdapter
    public UsuarioObserver(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void atualizar(String mensagem) {
        // Exibe a atualização de usuário no console
        System.out.println("Atualização de usuário recebida: " + mensagem);

        // Grava a atualização de usuário no log
        logAdapter.log("Atualização de Usuário", "Usuário", "Sistema", true, null);
    }

    // Método para processar uma atualização de usuário
    public void processarAtualizacaoUsuario(Usuario usuario) {
        String mensagem = String.format(
            "Atualização do usuário ID %d: Nome: %s, Tipo: %s, Status: %s",
            usuario.getId(),
            usuario.getNome(),
            usuario.getTipo(),
            usuario.getStatus()
        );

        // Atualiza a notificação e registra o log
        atualizar(mensagem);

        // Grava a operação de processamento no log
        logAdapter.log(
            "Processamento de Atualização de Usuário",
            "Usuário " + usuario.getNome(),
            "Sistema",
            true,
            null
        );
    }
}