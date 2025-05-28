package br.csi.model;

public class Cliente extends Usuario {
    private String cpf;
    private String telefone;
    private String endereco;

    // Construtores
    public Cliente() {}

    public Cliente(int id, String nome, String email, String senha, boolean ativo, String cpf) {
        super(id, nome, email, senha, ativo, "CLIENTE");
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}