package br.ufes.acessousuarios;

import br.ufes.acessousuarios.dao.UsuarioDAO;
import br.ufes.acessousuarios.model.Usuario;
import br.ufes.acessousuarios.view.CadastroView;
import br.ufes.acessousuarios.view.InicialView;
import br.ufes.acessousuarios.view.LoginView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;

public class AcessoUsuarios {
    LoginView view = new LoginView();
   // CadastroView cadastroView = new CadastroView();
                    
    
    public static void main(String[] args) {
        // Inicializa o DAO e cria a tabela se não existir
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.criarTabela();
        
        System.out.println("\nListagem de usuários após exclusão:");
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
        });
        
        SwingUtilities.invokeLater(() -> {
            try {
                // Verifica se há usuários cadastrados
                boolean usuariosExistem = usuarioDAO.existeUsuarios();

                if (usuariosExistem) {
                    // Se há usuários, abre a tela de login
                    InicialView inicialView = new InicialView();
                    inicialView.setVisible(true);
                } else {
                    // Se não há usuários, abre a tela de cadastro
                    CadastroView cadastroView = new CadastroView();
                    cadastroView.setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
                // Em caso de erro, exibe uma mensagem de erro
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Erro ao iniciar a aplicação: " + e.getMessage(),
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
//        // Adiciona um usuário de teste
//        String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
//        Usuario usuario = new Usuario(0, "admin", "senha123", "Administrador", dataCadastro, "ativo");
//        usuarioDAO.inserirUsuario(usuario);
//        System.out.println("Usuário 'admin' inserido com sucesso.");
//
        // Lista todos os usuários
//        System.out.println("\nListagem de usuários:");
//        usuarioDAO.listarUsuarios().forEach(u -> {
//            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
//        });
//
//        // Atualiza o usuário inserido
//        Usuario usuarioAtualizado = new Usuario(usuario.getId(), "admin", "novaSenha123", "Administrador", dataCadastro, "ativo");
//        usuarioDAO.atualizarUsuario(usuarioAtualizado);
//        System.out.println("\nUsuário 'admin' atualizado com nova senha.");
//
//        // Lista todos os usuários após a atualização
//        System.out.println("\nListagem de usuários após atualização:");
//        usuarioDAO.listarUsuarios().forEach(u -> {
//            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
//        });
//
//        // Exclui o usuário inserido
//        usuarioDAO.excluirUsuario(usuario.getId());
//        System.out.println("\nUsuário 'admin' excluído.");
//
//        // Lista todos os usuários após a exclusão
        System.out.println("\nListagem de usuários após exclusão:");
        usuarioDAO.listarUsuarios().forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Tipo: " + u.getTipo() + ", Data de Cadastro: " + u.getDataCadastro() + ", Status: " + u.getStatus());
        });
    }
}