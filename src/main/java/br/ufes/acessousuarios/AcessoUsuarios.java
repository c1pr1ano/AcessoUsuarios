package br.ufes.acessousuarios;

import br.ufes.acessousuarios.dao.UsuarioDAO;
import br.ufes.acessousuarios.model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AcessoUsuarios {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.criarTabela();

        // Inserindo um usuário de teste
        String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Usuario usuario = new Usuario(0, "admin", "senha123", "Administrador", dataCadastro);
        usuarioDAO.inserirUsuario(usuario);

        // Listando os usuários
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro());
        });
    }
}