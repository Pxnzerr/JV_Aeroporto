package com.aeromanager.enums;

public enum CondicaoClimatica {
    CEU_LIMPO("Céu Limpo", true, 0),
    CHUVA_LEVE("Chuva Leve", true, 5),
    NEVOEIRO_DENSO("Nevoeiro Denso", false, 20),
    TEMPESTADE_ELETRICA("Tempestade Elétrica", false, 50),
    FURACAO("Furacão", false, 100);

    private final String descricao;
    private final boolean tetoPermitidoDefault;
    private final int fatorRisco;

    CondicaoClimatica(String descricao, boolean tetoPermitidoDefault, int fatorRisco) {
        this.descricao = descricao;
        this.tetoPermitidoDefault = tetoPermitidoDefault;
        this.fatorRisco = fatorRisco;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isTetoPermitidoDefault() {
        return tetoPermitidoDefault;
    }

    public int getFatorRisco() {
        return fatorRisco;
    }
}
