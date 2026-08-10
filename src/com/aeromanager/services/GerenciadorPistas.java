package com.aeromanager.services;

import com.aeromanager.exceptions.RecursoIndisponivelException;
import com.aeromanager.models.Voo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class GerenciadorPistas {
    private final Deque<Voo> filaDecolagem;
    private final Deque<Voo> filaPouso;
    private final int quantidadePistasDisponiveis;

    public GerenciadorPistas(int quantidadePistasDisponiveis) {
        this.quantidadePistasDisponiveis = quantidadePistasDisponiveis;
        this.filaDecolagem = new ArrayDeque<>();
        this.filaPouso = new ArrayDeque<>();
    }

    public void agendarVoo(Voo voo) {
        if (voo.getOperacao() == null) {
            throw new RecursoIndisponivelException("Operação inválida para agendamento.");
        }
        if (voo.getOperacao().name().equals("POUSO")) {
            filaPouso.addLast(voo);
        } else {
            filaDecolagem.addLast(voo);
        }
    }

    public Voo liberarProximoVoo() {
        if (quantidadePistasDisponiveis <= 0) {
            throw new RecursoIndisponivelException("Nenhuma pista disponível no momento.");
        }
        Optional<Voo> proximo = Optional.ofNullable(filaPouso.peekFirst());
        if (proximo.isEmpty()) {
            proximo = Optional.ofNullable(filaDecolagem.peekFirst());
        }
        return proximo.orElseThrow(() -> new RecursoIndisponivelException("Nenhum voo aguardando liberação."));
    }

    public boolean removerVoo(Voo voo) {
        return filaPouso.remove(voo) || filaDecolagem.remove(voo);
    }

    public List<Voo> listarVoosEspera() {
        return List.copyOf(filaPouso.stream().toList());
    }
}
