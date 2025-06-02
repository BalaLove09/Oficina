package br.csi.dao;

import br.csi.model.Cliente;
import br.csi.model.Mecanico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/oficina";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public boolean cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO usuario (nome, email, senha, tipo, ativo) VALUES (?, ?, ?, 'CLIENTE', true)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSenha());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cadastrarMecanico(Mecanico mecanico) {
        String sql = "INSERT INTO usuario (nome, email, senha, tipo, ativo) VALUES (?, ?, ?, 'MECANICO', true)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mecanico.getNome());
            ps.setString(2, mecanico.getEmail());
            ps.setString(3, mecanico.getSenha());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
