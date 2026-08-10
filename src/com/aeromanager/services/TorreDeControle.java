package com.aeromanager.services;

import com.aeromanager.enums.CondicaoClimatica;
import com.aeromanager.enums.StatusVoo;
import com.aeromanager.exceptions.OperacaoNaoPermitidaException;
import com.aeromanager.models.Voo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TorreDeControle {
    private final GerenciadorPistas gerenciadorPistas;
    private final List<Voo> voos;
    private CondicaoClimatica climaAtual;

    public TorreDeControle(int pistas) {
        this.gerenciadorPistas = new GerenciadorPistas(pistas);
        this.voos = new ArrayList<>();
        this.climaAtual = CondicaoClimatica.CEU_LIMPO;
    }

    public void registrarVoo(Voo voo) {
        validarVoo(voo);
        voos.add(voo);
        gerenciadorPistas.agendarVoo(voo);
    }

    public void atualizarClima(CondicaoClimatica condicao) {
        this.climaAtual = condicao;
        ajustarVoosPorClima();
    }

    public void declararEmergencia(String identificadorVoo) {
        Voo voo = obterVoo(identificadorVoo);
        voo.setStatus(StatusVoo.EMERGENCIA);
    }

    public Voo autorizarProximoVoo() {
        Voo voo = gerenciadorPistas.liberarProximoVoo();
        if (voo.getStatus() == StatusVoo.CANCELADO || voo.getStatus() == StatusVoo.EMERGENCIA) {
            throw new OperacaoNaoPermitidaException("Voo não pode ser autorizado no estado atual.");
        }
        if (climaAtual == CondicaoClimatica.FURACAO || climaAtual == CondicaoClimatica.TEMPESTADE_ELETRICA) {
            throw new OperacaoNaoPermitidaException("Condições meteorológicas impedem autorização de operação.");
        }
        voo.setStatus(voo.getOperacao() == null ? StatusVoo.CANCELADO : StatusVoo.AUTORIZADO);
        return voo;
    }

    public List<Voo> listarVoos() {
        return Collections.unmodifiableList(voos);
    }

    public List<Voo> listarFilaEspera() {
        return gerenciadorPistas.listarVoosEspera();
    }

    public String obterResumo() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Clima atual: %s\n", climaAtual.getDescricao()));
        builder.append("Voos registrados:\n");
        for (Voo voo : voos) {
            builder.append(" - ").append(voo.getDescricao()).append("\n");
        }
        return builder.toString();
    }

    private void validarVoo(Voo voo) {
        if (voo.getOperacao() == null) {
            throw new OperacaoNaoPermitidaException("Operação do voo não pode ser nula.");
        }
        if (voo.getCondicaoClimatica() == CondicaoClimatica.FURACAO && voo.getOperacao().name().equals("DECOLAGEM")) {
            throw new OperacaoNaoPermitidaException("Decolagem proibida em furacão.");
        }
    }

    private void ajustarVoosPorClima() {
        for (Voo voo : voos) {
            if (climaAtual == CondicaoClimatica.NEVOEIRO_DENSO && voo.getOperacao().name().equals("DECOLAGEM")) {
                voo.setStatus(StatusVoo.AGUARDANDO_AUTORIZACAO);
            }
        }
    }

    private Voo obterVoo(String identificador) {
        Optional<Voo> resultado = voos.stream().filter(voo -> voo.getId().equals(identificador)).findFirst();
        return resultado.orElseThrow(() -> new OperacaoNaoPermitidaException("Voo não encontrado: " + identificador));
    }
}
