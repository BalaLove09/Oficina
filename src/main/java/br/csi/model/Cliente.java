package br.csi.model;

public class Cliente extends Usuario {
    private int idCliente;

    public Cliente() {}

    public Cliente(int idUsuario, String nome, String email, String senha, boolean ativo, int idCliente) {
        super(idUsuario, nome, email, senha, "CLIENTE", ativo);
        this.idCliente = idCliente;
    }

    public Cliente(String nome, String email, String senha) {
        super(0, nome, email, senha, "CLIENTE", true);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
