package com.example.ms_pagamento.controller;

import com.example.ms_pagamento.model.Pagamento;
import com.example.ms_pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @PostMapping("/cadastrar")
    public void cadastrarPagamento(@RequestBody Pagamento pagamento) {
        pagamentoService.cadastrarPagamento(pagamento);
    }
    @GetMapping("/obter")
    public List<Pagamento> obterTodosPagamentos() {
        return pagamentoService.obterTodosPagamentos();
    }
    @GetMapping("/obterPorId/{id}")
    public Pagamento obterPagamentoPorId(@PathVariable Long id) {
        return pagamentoService.obterPagamentoPorId(id);
    }
}
