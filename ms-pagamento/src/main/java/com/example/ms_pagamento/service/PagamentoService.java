package com.example.ms_pagamento.service;

import com.example.ms_pagamento.model.Pagamento;
import com.example.ms_pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public List<Pagamento> obterTodosPagamentos() {
        return pagamentoRepository.findAll();
    }

    public void cadastrarPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    public Pagamento obterPagamentoPorId(Long id) {
        return pagamentoRepository.findById(id).orElseThrow();
    }


}
