package br.csi.dao;

import br.csi.model.OrdemServico;
import br.csi.model.Veiculo;
import br.csi.util.ConectarBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDAO {
    private static final String SQL_LISTAR_MECANICO =
            "SELECT os.id, os.descricao, os.status, v.modelo " +
                    "FROM ordem_servico os " +
                    "JOIN veiculo v ON os.id_veiculo = v.id " +
                    "WHERE os.id_mecanico = ?";

    private static final String SQL_LISTAR_CLIENTE =
            "SELECT os.id, os.descricao, os.status, os.data_entrada " +
                    "FROM ordem_servico os " +
                    "JOIN veiculo v ON os.id_veiculo = v.id " +
                    "WHERE v.id_cliente = ?";

    public List<OrdemServico> listarOrdensPorMecanico(int idMecanico) {
        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = new ConectarBD().ConectarBancoPostgres();
             PreparedStatement ps = conn.prepareStatement(SQL_LISTAR_MECANICO)) {

            ps.setInt(1, idMecanico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdemServico os = new OrdemServico();
                os.setId_ordem(rs.getInt("id"));
                os.setDescricao(rs.getString("descricao"));
                os.setStatus(rs.getString("status"));

                Veiculo v = new Veiculo();
                v.setModelo(rs.getString("modelo"));
                os.setVeiculo(v);

                ordens.add(os);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }

    public List<OrdemServico> listarOrdensPorCliente(int idCliente) {
        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = new ConectarBD().ConectarBancoPostgres();
             PreparedStatement ps = conn.prepareStatement(SQL_LISTAR_CLIENTE)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdemServico os = new OrdemServico();
                os.setId_ordem(rs.getInt("id"));
                os.setDescricao(rs.getString("descricao"));
                os.setStatus(rs.getString("status"));
                os.setDataEntrada(rs.getDate("data_entrada"));
                ordens.add(os);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }
}