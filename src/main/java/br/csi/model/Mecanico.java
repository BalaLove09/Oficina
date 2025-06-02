package br.csi.model;

public class Mecanico extends Usuario {
    private int idMecanico;

    public Mecanico() {
        super();
        this.setTipo("MECANICO");
    }

    public Mecanico(int idUsuario, String nome, String email, String senha, boolean ativo, int idMecanico) {
        super(idUsuario, nome, email, senha, "MECANICO", ativo);
        this.idMecanico = idMecanico;
    }


    public Mecanico(String nome, String email, String senha) {
        super(0, nome, email, senha, "MECANICO", true);
    }


    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
}