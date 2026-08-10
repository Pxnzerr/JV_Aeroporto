package com.aeromanager;

import com.aeromanager.enums.CondicaoClimatica;
import com.aeromanager.enums.TipoOperacao;
import com.aeromanager.models.Aeronave;
import com.aeromanager.models.Helicoptero;
import com.aeromanager.models.Jato;
import com.aeromanager.models.Voo;
import com.aeromanager.services.TorreDeControle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TorreDeControle torre = new TorreDeControle(2);
        criarVoosDeDemonstracao(torre);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println("\n=== AeroManager - Torre de Controle ===");
            System.out.println("1. Ver resumo da torre");
            System.out.println("2. Autorizar próximo voo");
            System.out.println("3. Atualizar condição climática");
            System.out.println("4. Declarar emergência em voo");
            System.out.println("5. Listar fila de espera");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();

            try {
                switch (opcao) {
                    case "1" -> System.out.println(torre.obterResumo());
                    case "2" -> {
                        Voo autorizado = torre.autorizarProximoVoo();
                        System.out.println("Autorizado: " + autorizado.getDescricao());
                    }
                    case "3" -> {
                        CondicaoClimatica clima = escolherClima(scanner);
                        torre.atualizarClima(clima);
                        System.out.println("Clima atualizado para " + clima.getDescricao());
                    }
                    case "4" -> {
                        System.out.print("Informe o ID do voo: ");
                        String id = scanner.nextLine().trim();
                        torre.declararEmergencia(id);
                        System.out.println("Emergência declarada para " + id);
                    }
                    case "5" -> torre.listarFilaEspera().forEach(voo -> System.out.println(voo.getDescricao()));
                    case "0" -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void criarVoosDeDemonstracao(TorreDeControle torre) {
        Aeronave jatoA = new Jato("PT-AAA", "Embraer", 120, 850, 3800);
        Aeronave heliB = new Helicoptero("PR-HBI", "Sikorsky", 8, 1.2);
        Aeronave jatoC = new Jato("PR-CCC", "Boeing", 200, 900, 5000);

        torre.registrarVoo(new Voo(jatoA, TipoOperacao.DECOLAGEM, LocalDateTime.now().plusHours(2), "São Paulo", "Rio de Janeiro", CondicaoClimatica.CEU_LIMPO));
        torre.registrarVoo(new Voo(heliB, TipoOperacao.POUSO, LocalDateTime.now().plusMinutes(45), "Campinas", "Congonhas", CondicaoClimatica.CEU_LIMPO));
        torre.registrarVoo(new Voo(jatoC, TipoOperacao.DECOLAGEM, LocalDateTime.now().plusHours(4), "Brasília", "Salvador", CondicaoClimatica.CHUVA_LEVE));
    }

    private static CondicaoClimatica escolherClima(Scanner scanner) {
        System.out.println("1. Céu Limpo");
        System.out.println("2. Chuva Leve");
        System.out.println("3. Nevoeiro Denso");
        System.out.println("4. Tempestade Elétrica");
        System.out.println("5. Furacão");
        System.out.print("Escolha condição: ");
        String escolha = scanner.nextLine().trim();
        return switch (escolha) {
            case "1" -> CondicaoClimatica.CEU_LIMPO;
            case "2" -> CondicaoClimatica.CHUVA_LEVE;
            case "3" -> CondicaoClimatica.NEVOEIRO_DENSO;
            case "4" -> CondicaoClimatica.TEMPESTADE_ELETRICA;
            case "5" -> CondicaoClimatica.FURACAO;
            default -> CondicaoClimatica.CEU_LIMPO;
        };
    }
}
