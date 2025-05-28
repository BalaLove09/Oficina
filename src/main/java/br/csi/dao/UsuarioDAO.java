package br.csi.dao;

import br.csi.model.Usuario;
import java.sql.*;

public class UsuarioDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/oficina";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public Usuario autenticar(String email, String senha) {
        System.out.println("Tentando autenticar: " + email + " - " + senha);
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Executando consulta...");
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
