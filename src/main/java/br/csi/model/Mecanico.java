package br.csi.model;

public class Mecanico extends Usuario {
    private String especialidade; // Ex: "Motor", "Elétrica"

    // Construtores
    public Mecanico() {}

    public Mecanico(int id, String nome, String email, String senha, boolean ativo, String especialidade) {
        super(id, nome, email, senha, ativo, "MECANICO");
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}