package com.aeromanager.models;

public abstract class Aeronave {
    private final String identificador;
    private final String tipoFabricante;
    private final int capacidadePassageiros;

    protected Aeronave(String identificador, String tipoFabricante, int capacidadePassageiros) {
        this.identificador = identificador;
        this.tipoFabricante = tipoFabricante;
        this.capacidadePassageiros = capacidadePassageiros;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipoFabricante() {
        return tipoFabricante;
    }

    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    public abstract double calcularTempoOperacao();
}
