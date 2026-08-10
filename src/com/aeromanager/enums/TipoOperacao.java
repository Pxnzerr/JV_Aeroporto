package com.aeromanager.enums;

public enum TipoOperacao {
    POUSO("Pouso"),
    DECOLAGEM("Decolagem");

    private final String descricao;

    TipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
