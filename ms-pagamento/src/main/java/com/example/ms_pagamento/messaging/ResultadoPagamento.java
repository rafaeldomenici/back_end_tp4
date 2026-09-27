package com.example.ms_pagamento.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoPagamento {

    private Long vendaId;
    private Long pagamentoId;
    private String tipo;
    private boolean aVista;
    private int parcelas;
}
