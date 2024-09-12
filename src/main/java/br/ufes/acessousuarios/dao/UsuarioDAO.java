package br.ufes.acessousuarios.dao;

import br.ufes.acessousuarios.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        try {
            this.connection = SQLiteConnection.getConnection(); // Inicializa a conexão
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar o erro de forma adequada em um projeto real
        }
    }

    // Cria a tabela 'usuario' se não existir
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "senha TEXT NOT NULL," +
                     "tipo TEXT NOT NULL," +
                     "dataCadastro TEXT NOT NULL," +
                     "status TEXT NOT NULL" +
                     ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // Insere um novo usuário na tabela
    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, senha, tipo, dataCadastro, status) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setString(3, usuario.getTipo());
            pstmt.setString(4, usuario.getDataCadastro());
            pstmt.setString(5, usuario.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Lista todos os usuários da tabela
    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("tipo"),
                        rs.getString("dataCadastro"),
                        rs.getString("status")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    // Atualiza as informações de um usuário existente
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, tipo = ?, status = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setString(3, usuario.getTipo());
            pstmt.setString(4, usuario.getStatus());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Exclui um usuário da tabela pelo seu ID
    public void excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    // Busca um usuário pelo nome de usuário
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        Usuario usuario = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("senha"),
                            rs.getString("tipo"),
                            rs.getString("dataCadastro"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por nome: " + e.getMessage());
        }
        return usuario;
    }

    // Método para buscar um usuário por ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("senha"),
                            rs.getString("tipo"),
                            rs.getString("dataCadastro"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
        }

        return usuario;
    }
}