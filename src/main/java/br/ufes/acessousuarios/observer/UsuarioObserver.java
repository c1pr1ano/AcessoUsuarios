package br.ufes.acessousuarios.observer;

import br.ufes.acessousuarios.model.Usuario;

public class UsuarioObserver implements Observer {

    @Override
    public void atualizar(String mensagem) {
        // Aqui você pode implementar a lógica para tratar a atualização de usuário.
        System.out.println("Atualização de usuário recebida: " + mensagem);
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
        atualizar(mensagem);
    }
}