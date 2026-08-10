package com.aeromanager.models;

public class Jato extends Aeronave {
    private final double velocidadeCruzeiro;
    private final double autonomiaKm;

    public Jato(String identificador, String tipoFabricante, int capacidadePassageiros, double velocidadeCruzeiro, double autonomiaKm) {
        super(identificador, tipoFabricante, capacidadePassageiros);
        this.velocidadeCruzeiro = velocidadeCruzeiro;
        this.autonomiaKm = autonomiaKm;
    }

    public double getVelocidadeCruzeiro() {
        return velocidadeCruzeiro;
    }

    public double getAutonomiaKm() {
        return autonomiaKm;
    }

    @Override
    public double calcularTempoOperacao() {
        return autonomiaKm / velocidadeCruzeiro;
    }
}
