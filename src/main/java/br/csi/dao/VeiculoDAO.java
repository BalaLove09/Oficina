package br.csi.dao;

import br.csi.model.Veiculo;
import br.csi.util.ConectarBD; // Sua classe de conexão
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public String alterar(Veiculo veiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBD.ConectarBancoPostgres();
            stmt = conn.prepareStatement(
                    "UPDATE veiculo SET marca = ?, modelo = ?, placa = ? WHERE id = ? AND id_cliente = ?"
            );

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setInt(4, veiculo.getId());
            stmt.setInt(5, veiculo.getIdCliente());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Veículo alterado com sucesso";
            } else {
                return "Nenhum veículo encontrado para alteração ou sem permissão.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao alterar veículo: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar veículo: " + e.getMessage();
        } finally {
            // Fechar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos (alterar): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean excluir(int idVeiculo, int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBD.ConectarBancoPostgres();
            stmt = conn.prepareStatement(
                    "DELETE FROM veiculo WHERE id = ? AND id_cliente = ?"
            );

            stmt.setInt(1, idVeiculo);
            stmt.setInt(2, idCliente);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir veículo: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Fechar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos (excluir): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String inserir(Veiculo veiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBD.ConectarBancoPostgres();
            stmt = conn.prepareStatement(
                    "INSERT INTO veiculo (id_cliente, marca, modelo, placa) VALUES (?, ?, ?, ?)"
            );

            stmt.setInt(1, veiculo.getIdCliente());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getPlaca());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Veículo inserido com sucesso";
            } else {
                return "Erro ao inserir veículo.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir veículo: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao inserir veículo: " + e.getMessage();
        } finally {
            // Fechar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos (inserir): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public ArrayList<Veiculo> getVeiculos() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectarBD.ConectarBancoPostgres();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT id, id_cliente, marca, modelo, placa FROM veiculo");
            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));

                veiculos.add(v);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos (getVeiculos): " + e.getMessage());
                e.printStackTrace();
            }
        }
        return veiculos;
    }

    public Veiculo getVeiculoById(int idVeiculo, int idCliente) {
        Veiculo veiculo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectarBD.ConectarBancoPostgres();
            stmt = conn.prepareStatement(
                    "SELECT id, id_cliente, marca, modelo, placa FROM veiculo WHERE id = ? AND id_cliente = ?"
            );
            stmt.setInt(1, idVeiculo);
            stmt.setInt(2, idCliente);

            rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setIdCliente(rs.getInt("id_cliente"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar veículo por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos (getVeiculoById): " + e.getMessage());
                e.printStackTrace();
            }
        }
        return veiculo;
    }


    public List<Veiculo> getVeiculosByClienteId(int idCliente) {
        List<Veiculo> veiculos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectarBD.ConectarBancoPostgres();
            String sql = "SELECT id, id_cliente, marca, modelo, placa FROM veiculo WHERE id_cliente = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                veiculos.add(new Veiculo(
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao listar veículos por cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos (getVeiculosByClienteId): " + e.getMessage());
                e.printStackTrace();
            }
        }
        return veiculos;
    }

}