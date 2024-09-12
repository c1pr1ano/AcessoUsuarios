package br.ufes.acessousuarios;

import br.ufes.acessousuarios.dao.UsuarioDAO;
import br.ufes.acessousuarios.model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AcessoUsuarios {

    public static void main(String[] args) {
        // Inicializa o DAO e cria a tabela se não existir
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.criarTabela();

        // Adiciona um usuário de teste
        String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Usuario usuario = new Usuario(0, "admin", "senha123", "Administrador", dataCadastro, "ativo");
        usuarioDAO.inserirUsuario(usuario);
        System.out.println("Usuário 'admin' inserido com sucesso.");

        // Lista todos os usuários
        System.out.println("\nListagem de usuários:");
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
        });

        // Atualiza o usuário inserido
        Usuario usuarioAtualizado = new Usuario(usuario.getId(), "admin", "novaSenha123", "Administrador", dataCadastro, "ativo");
        usuarioDAO.atualizarUsuario(usuarioAtualizado);
        System.out.println("\nUsuário 'admin' atualizado com nova senha.");

        // Lista todos os usuários após a atualização
        System.out.println("\nListagem de usuários após atualização:");
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
        });

        // Exclui o usuário inserido
        usuarioDAO.excluirUsuario(usuario.getId());
        System.out.println("\nUsuário 'admin' excluído.");

        // Lista todos os usuários após a exclusão
        System.out.println("\nListagem de usuários após exclusão:");
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
        });
    }
}