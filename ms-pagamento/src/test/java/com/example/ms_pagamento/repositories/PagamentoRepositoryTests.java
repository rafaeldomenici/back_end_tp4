package com.example.ms_pagamento.repositories;

import com.example.ms_pagamento.model.Pagamento;
import com.example.ms_pagamento.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Test
    void cadastrarPagamentoComSucesso() {
        Pagamento pagamento = new Pagamento(null,"teste",false,8,1L);

        Pagamento pagamentoSalvo =  pagamentoRepository.save(pagamento);

        // ASSERT
        assertNotNull(pagamentoSalvo.getId());
    }

    @Test
    void obterClienteComSucesso() {
        Pagamento pagamento = new Pagamento(null,"teste",false,8,1L);


        Pagamento pagamentoSalvo =  pagamentoRepository.save(pagamento);

        Pagamento pagamentoObtido = pagamentoRepository.findById(pagamentoSalvo.getId()).orElseThrow();
        // ASSERT
        assertEquals(8,pagamentoObtido.getParcelas());
    }


    @Test
    void obterTodosClientesComSucesso() {
        List<Pagamento> pagamentos1 = pagamentoRepository.findAll();
        Pagamento pagamento1 = new Pagamento(null,"teste",false,8,1L);

        Pagamento pagamento2 = new Pagamento(null,"teste",false,5,1L);

        pagamentoRepository.save(pagamento1);
        pagamentoRepository.save(pagamento2);
        List<Pagamento> pagamentos2 = pagamentoRepository.findAll();
        assertEquals(pagamentos1.size() + 2, pagamentos2.size());
    }



}
