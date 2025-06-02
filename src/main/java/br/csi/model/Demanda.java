package br.csi.model;

public class Demanda {
    private int id;
    private String titulo;
    private String descricao;
    private int idMecanico;


    public Demanda(int id, String titulo, String descricao, int idMecanico) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idMecanico = idMecanico;
    }


    public Demanda(String titulo, String descricao, int idMecanico) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.idMecanico = idMecanico;
    }


    public Demanda() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    @Override
    public String toString() {
        return "Demanda{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idMecanico=" + idMecanico +
                '}';
    }
}