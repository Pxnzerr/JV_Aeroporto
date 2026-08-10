package com.aeromanager.services;

import com.aeromanager.exceptions.RecursoIndisponivelException;
import com.aeromanager.models.Voo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GerenciadorPistas {
    private final Deque<Voo> filaDecolagem;
    private final Deque<Voo> filaPouso;
    private final int quantidadePistasDisponiveis;
    private int pistasEmUso;

    public GerenciadorPistas(int quantidadePistasDisponiveis) {
        this.quantidadePistasDisponiveis = quantidadePistasDisponiveis;
        this.filaDecolagem = new ArrayDeque<>();
        this.filaPouso = new ArrayDeque<>();
        this.pistasEmUso = 0;
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
        if (pistasEmUso >= quantidadePistasDisponiveis) {
            throw new RecursoIndisponivelException("Nenhuma pista disponível no momento.");
        }
        Voo proximo = filaPouso.pollFirst();
        if (proximo == null) {
            proximo = filaDecolagem.pollFirst();
        }
        if (proximo == null) {
            throw new RecursoIndisponivelException("Nenhum voo aguardando liberação.");
        }
        pistasEmUso++;
        return proximo;
    }

    public boolean removerVoo(Voo voo) {
        boolean removido = filaPouso.remove(voo) || filaDecolagem.remove(voo);
        if (removido && pistasEmUso > 0) {
            pistasEmUso--;
        }
        return removido;
    }

    public List<Voo> listarVoosEspera() {
        return List.copyOf(Stream.concat(filaPouso.stream(), filaDecolagem.stream()).toList());
    }
}
