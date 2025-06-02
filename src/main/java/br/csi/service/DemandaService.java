package br.csi.service;

import br.csi.dao.DemandaDAO;
import br.csi.model.Demanda;

import java.util.List;

public class DemandaService {
    private DemandaDAO demandaDAO;

    public DemandaService() {
        this.demandaDAO = new DemandaDAO();
    }

    public String inserir(Demanda demanda) {
        return demandaDAO.inserir(demanda);
    }

    public String alterar(Demanda demanda) {
        return demandaDAO.alterar(demanda);
    }

    public boolean excluir(int idDemanda, int idMecanico) {
        return demandaDAO.excluir(idDemanda, idMecanico);
    }

    public Demanda getDemandaById(int idDemanda, int idMecanico) {
        return demandaDAO.getDemandaById(idDemanda, idMecanico);
    }

    public List<Demanda> getDemandasByMecanicoId(int idMecanico) {
        return demandaDAO.getDemandasByMecanicoId(idMecanico);
    }

    public List<Demanda> getAllDemandas() {
        return demandaDAO.getAllDemandas();
    }
}