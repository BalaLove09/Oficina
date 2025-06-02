package br.csi.dao;

import br.csi.model.Demanda;
import br.csi.util.ConectarBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemandaDAO {

    public String inserir(Demanda demanda) {
        String sql = "INSERT INTO demanda (titulo, descricao, id_mecanico) VALUES (?, ?, ?)";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, demanda.getTitulo());
            stmt.setString(2, demanda.getDescricao());
            stmt.setInt(3, demanda.getIdMecanico());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Demanda cadastrada com sucesso!";
            } else {
                return "Erro ao cadastrar demanda.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro no banco de dados ao cadastrar demanda: " + e.getMessage();
        }
    }

    public String alterar(Demanda demanda) {
        String sql = "UPDATE demanda SET titulo = ?, descricao = ? WHERE id = ? AND id_mecanico = ?";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, demanda.getTitulo());
            stmt.setString(2, demanda.getDescricao());
            stmt.setInt(3, demanda.getId());
            stmt.setInt(4, demanda.getIdMecanico());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Demanda alterada com sucesso!";
            } else {
                return "Nenhuma demanda encontrada para alteração ou sem permissão.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro no banco de dados ao alterar demanda: " + e.getMessage();
        }
    }

    public boolean excluir(int idDemanda, int idMecanico) {
        String sql = "DELETE FROM demanda WHERE id = ? AND id_mecanico = ?";

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDemanda);
            stmt.setInt(2, idMecanico);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Demanda excluída com sucesso.");
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Demanda getDemandaById(int idDemanda, int idMecanico) {
        String sql = "SELECT id, titulo, descricao, id_mecanico FROM demanda WHERE id = ? AND id_mecanico = ?";
        Demanda demanda = null;

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDemanda);
            stmt.setInt(2, idMecanico);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    demanda = new Demanda(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("descricao"),
                            rs.getInt("id_mecanico")
                    );
                } else {
                    System.out.println("Nenhuma demanda encontrada");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return demanda;
    }

    public List<Demanda> getDemandasByMecanicoId(int idMecanico) {
        String sql = "SELECT id, titulo, descricao, id_mecanico FROM demanda WHERE id_mecanico = ?";
        List<Demanda> demandas = new ArrayList<>();

        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMecanico);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    demandas.add(new Demanda(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("descricao"),
                            rs.getInt("id_mecanico")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return demandas;
    }

    public List<Demanda> getAllDemandas() {
        String sql = "SELECT id, titulo, descricao, id_mecanico FROM demanda";
        List<Demanda> demandas = new ArrayList<>();


        try (Connection conn = ConectarBD.ConectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                demandas.add(new Demanda(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("id_mecanico")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return demandas;
    }
}