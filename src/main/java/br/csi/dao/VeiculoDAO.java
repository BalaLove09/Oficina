package br.csi.dao;

import br.csi.model.Veiculo;
import br.csi.util.ConectarBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private static final String SQL_LISTAR_CLIENTE =
            "SELECT id, modelo, marca, placa FROM veiculo WHERE id_cliente = ?";

    public List<Veiculo> listarVeiculosPorCliente(int idCliente) {
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = new ConectarBD().ConectarBancoPostgres();
             PreparedStatement ps = conn.prepareStatement(SQL_LISTAR_CLIENTE)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId_veiculo(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setMarca(rs.getString("marca"));
                v.setPlaca(rs.getString("placa"));
                veiculos.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return veiculos;
    }
}