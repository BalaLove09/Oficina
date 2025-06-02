package br.csi.service;

import br.csi.dao.CadastroDAO;
import br.csi.model.Cliente;
import br.csi.model.Mecanico;

public class CadastroService {
    private final CadastroDAO dao = new CadastroDAO();

    public boolean cadastrarCliente(Cliente cliente) {
        return dao.cadastrarCliente(cliente);
    }

    public boolean cadastrarMecanico(Mecanico mecanico) {
        return dao.cadastrarMecanico(mecanico);
    }
}
