package com.aeromanager.models;

import com.aeromanager.enums.CondicaoClimatica;
import com.aeromanager.enums.StatusVoo;
import com.aeromanager.enums.TipoOperacao;

import java.time.LocalDateTime;
import java.util.UUID;

public class Voo {
    private final String id;
    private final Aeronave aeronave;
    private final TipoOperacao operacao;
    private final LocalDateTime horarioPrevisto;
    private final String origem;
    private final String destino;
    private StatusVoo status;
    private CondicaoClimatica condicaoClimatica;
    private String observacao;

    public Voo(Aeronave aeronave, TipoOperacao operacao, LocalDateTime horarioPrevisto, String origem, String destino, CondicaoClimatica condicaoClimatica) {
        this.id = UUID.randomUUID().toString();
        this.aeronave = aeronave;
        this.operacao = operacao;
        this.horarioPrevisto = horarioPrevisto;
        this.origem = origem;
        this.destino = destino;
        this.status = StatusVoo.AGUARDANDO_AUTORIZACAO;
        this.condicaoClimatica = condicaoClimatica;
        this.observacao = "";
    }

    public String getId() {
        return id;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public LocalDateTime getHorarioPrevisto() {
        return horarioPrevisto;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public StatusVoo getStatus() {
        return status;
    }

    public void setStatus(StatusVoo status) {
        this.status = status;
    }

    public CondicaoClimatica getCondicaoClimatica() {
        return condicaoClimatica;
    }

    public void setCondicaoClimatica(CondicaoClimatica condicaoClimatica) {
        this.condicaoClimatica = condicaoClimatica;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDescricao() {
        return String.format("%s | %s | %s -> %s | %s | %s", id, aeronave.getIdentificador(), origem, destino, operacao.getDescricao(), status.getDescricao());
    }
}
