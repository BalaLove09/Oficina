package br.csi.dao;

import br.csi.model.Usuario;
import br.csi.util.ConectarBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listarMecanicos() {
        String sql = "SELECT id_usuario, nome, email, ativo FROM usuario WHERE tipo = 'MECANICO' AND ativo = true";
        List<Usuario> mecanicos = new ArrayList<>();

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario mecanico = new Usuario();
                mecanico.setIdUsuario(rs.getInt("id_usuario"));
                mecanico.setNome(rs.getString("nome"));
                mecanico.setEmail(rs.getString("email"));
                mecanico.setAtivo(rs.getBoolean("ativo"));
                mecanico.setTipo("MECANICO");

                mecanicos.add(mecanico);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mecanicos;
    }

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("tipo"),
                            rs.getBoolean("ativo")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int getIdClienteByUsuarioId(int idUsuario) {
        String sql = "SELECT id_cliente FROM cliente WHERE id_usuario = ?";
        int idCliente = -1;

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idCliente = rs.getInt("id_cliente");
                    System.out.println("id_cliente encontrado: " + idCliente);
                } else {
                    System.out.println("Nenhum id_cliente encontrado para id_usuario: " + idUsuario);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return idCliente;
    }

    public int getIdMecanicoByUsuarioId(int idUsuario) {
        String sql = "SELECT id_mecanico FROM mecanico WHERE id_usuario = ?";
        int idMecanico = -1;

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idMecanico = rs.getInt("id_mecanico");
                    System.out.println("id_mecanico encontrado: " + idMecanico);
                } else {
                    System.out.println("Nenhum id_mecanico encontrado para id_usuario: " + idUsuario);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return idMecanico;
    }

    public String alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo = ?, ativo = ? WHERE id_usuario = ?";
        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setBoolean(5, usuario.isAtivo());
            stmt.setInt(6, usuario.getIdUsuario());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Usuario alterado com sucesso";
            } else {
                return "Nenhum usuário encontrado para alteração.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao alterar usuario: " + e.getMessage();
        }
    }

    public boolean excluir(int id_usuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_usuario);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao excluir usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String inserir(Usuario usuario) {
        String sqlUsuario = "INSERT INTO usuario (nome, email, senha, tipo, ativo) VALUES (?, ?, ?, ?, ?) RETURNING id_usuario";
        String sqlCliente = "INSERT INTO cliente (id_usuario) VALUES (?)";
        String sqlMecanico = "INSERT INTO mecanico (id_usuario) VALUES (?)";

        Connection conn = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtTipo = null;
        ResultSet rs = null;

        try {
            conn = ConectarBD.ConectarBancoPostgres();
            conn.setAutoCommit(false);

            stmtUsuario = conn.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, usuario.getNome());
            stmtUsuario.setString(2, usuario.getEmail());
            stmtUsuario.setString(3, usuario.getSenha());
            stmtUsuario.setString(4, usuario.getTipo());
            stmtUsuario.setBoolean(5, true);

            rs = stmtUsuario.executeQuery();

            if (rs.next()) {
                int idUsuarioGerado = rs.getInt("id_usuario");
                usuario.setIdUsuario(idUsuarioGerado);

                if ("CLIENTE".equalsIgnoreCase(usuario.getTipo())) {
                    stmtTipo = conn.prepareStatement(sqlCliente);
                } else if ("MECANICO".equalsIgnoreCase(usuario.getTipo())) {
                    stmtTipo = conn.prepareStatement(sqlMecanico);
                }

                if (stmtTipo != null) {
                    stmtTipo.setInt(1, idUsuarioGerado);
                    stmtTipo.executeUpdate();
                }

                conn.commit();
                return "Usuário inserido com sucesso";
            } else {
                conn.rollback();
                return "Erro ao inserir usuário: ID não gerado.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao inserir usuario: " + e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Erro ao realizar rollback: " + ex.getMessage());
                ex.printStackTrace();
            }
            return "Erro ao inserir usuario: " + e.getMessage();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (stmtUsuario != null) stmtUsuario.close(); } catch (SQLException e) { System.err.println("Erro ao fechar stmtUsuario: " + e.getMessage()); }
            try { if (stmtTipo != null) stmtTipo.close(); } catch (SQLException e) { System.err.println("Erro ao fechar stmtTipo: " + e.getMessage()); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { System.err.println("Erro ao fechar Connection: " + e.getMessage()); }
        }
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email, senha, tipo, ativo FROM usuario";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setTipo(rs.getString("tipo"));
                u.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(u);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario, nome, email, senha, tipo, ativo FROM usuario WHERE id_usuario = ?";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipo(rs.getString("tipo"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
}