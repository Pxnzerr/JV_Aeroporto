package com.aeromanager.models;

public class Helicoptero extends Aeronave {
    private final double capacidadeCargaToneladas;

    public Helicoptero(String identificador, String tipoFabricante, int capacidadePassageiros, double capacidadeCargaToneladas) {
        super(identificador, tipoFabricante, capacidadePassageiros);
        this.capacidadeCargaToneladas = capacidadeCargaToneladas;
    }

    public double getCapacidadeCargaToneladas() {
        return capacidadeCargaToneladas;
    }

    @Override
    public double calcularTempoOperacao() {
        return capacidadeCargaToneladas > 0 ? 1.5 : 1.0;
    }
}
