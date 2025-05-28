package br.csi.model;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private String tipo; // "CLIENTE" ou "MECANICO"

    // Construtores
    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha, boolean ativo, String tipo) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.tipo = tipo;
    }

    // Getters e Setters (mantendo seu padrão)
    public int getId() {
        return id_usuario;
    }

    public void setId(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}