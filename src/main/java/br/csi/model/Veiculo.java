package br.csi.model;

public class Veiculo {
    private int id;
    private int idCliente;
    private String marca;
    private String modelo;
    private String placa;

    public Veiculo(int id, int idCliente, String marca, String modelo, String placa) {
        this.id = id;
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public Veiculo(int idCliente, String marca, String modelo, String placa) {
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }


    public Veiculo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }
}