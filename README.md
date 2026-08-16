# ✈️ AeroManager — Gerenciador de Aeroporto

Sistema de gerenciamento de operações aeroportuárias desenvolvido em Java, demonstrando conceitos avançados de POO como herança, polimorfismo, interfaces, enums e tratamento de exceções customizadas.

## Estrutura do Projeto

```
src/com/aeromanager/
├── Main.java                              # Ponto de entrada
├── enums/
│   ├── CondicaoClimatica.java             # Enum de condições climáticas
│   ├── StatusVoo.java                     # Enum de status de voo
│   └── TipoOperacao.java                 # Enum de tipos de operação
├── exceptions/
│   ├── OperacaoNaoPermitidaException.java # Exceção customizada
│   └── RecursoIndisponivelException.java  # Exceção customizada
├── interfaces/
│   └── Monitoravel.java                   # Interface de monitoramento
├── models/
│   ├── Aeronave.java                      # Classe abstrata base
│   ├── Helicoptero.java                   # Subclasse concreta
│   ├── Jato.java                          # Subclasse concreta
│   └── Voo.java                           # Modelo de voo
└── services/
    ├── GerenciadorPistas.java             # Serviço de gerenciamento de pistas
    └── TorreDeControle.java               # Serviço de torre de controle
```

## Conceitos de POO Aplicados

| Conceito | Aplicação |
|---|---|
| Herança | `Helicoptero`, `Jato` → `Aeronave` |
| Polimorfismo | Comportamentos específicos por tipo de aeronave |
| Abstração | `Aeronave` como classe abstrata, `Monitoravel` como interface |
| Encapsulamento | Atributos privados com getters/setters |
| Enums | `StatusVoo`, `CondicaoClimatica`, `TipoOperacao` |
| Exceções Customizadas | Tratamento de erros de domínio |

## Como Executar

```bash
# Compilar
javac -encoding UTF-8 -d out src/com/aeromanager/**/*.java src/com/aeromanager/Main.java

# Executar
java -cp out com.aeromanager.Main
```

## Requisitos

- Java 11+
