package com.aeromanager.enums;

public enum StatusVoo {
    AGUARDANDO_AUTORIZACAO("Aguardando Autorização"),
    AUTORIZADO("Autorizado para Operação"),
    EM_PISTA("Em Pista"),
    POUSANDO("Pousando"),
    DECOLANDO("Decolando"),
    NO_PORTAO("No Portão de Embarque"),
    EM_ROTA("Em Rota (Finalizado)"),
    EMERGENCIA("EMERGÊNCIA DECLARADA"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusVoo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
