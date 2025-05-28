package br.csi.model;

import java.util.Date;

public class OrdemServico {
    private int id_ordem;
    private String descricao;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_ordem() {
        return id_ordem;
    }

    public void setId_ordem(int id_ordem) {
        this.id_ordem = id_ordem;
    }

    private Date dataEntrada;
    private String status;
    private Veiculo veiculo;


}