package br.csi.service;

import br.csi.dao.VeiculoDAO;
import br.csi.model.Veiculo;

import java.util.List;

public class VeiculoService {

    private VeiculoDAO dao;

    public VeiculoService() {
        this.dao = new VeiculoDAO();
    }

    public String inserir(Veiculo veiculo) {
        return dao.inserir(veiculo);
    }

    public String alterar(Veiculo veiculo) {
        return dao.alterar(veiculo);
    }

    public boolean excluir(int idVeiculo, int idCliente) {
        return dao.excluir(idVeiculo, idCliente);
    }

    public List<Veiculo> getVeiculos() {
        return dao.getVeiculos();
    }

    public Veiculo getVeiculoById(int idVeiculo, int idCliente) {
        return dao.getVeiculoById(idVeiculo, idCliente);
    }

    public List<Veiculo> getVeiculosByClienteId(int idCliente) {
        return dao.getVeiculosByClienteId(idCliente);
    }
}