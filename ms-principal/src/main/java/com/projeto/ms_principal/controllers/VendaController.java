package com.projeto.ms_principal.controllers;

import com.projeto.ms_principal.clients.PagamentoClient;
import com.projeto.ms_principal.dto.PagamentoDTO;
import com.projeto.ms_principal.entities.Cliente;
import com.projeto.ms_principal.entities.Venda;
import com.projeto.ms_principal.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private PagamentoClient pagamentoClient;

    @GetMapping
    public List<Venda> obterTodasVendas() {
        return vendaService.obterTodasVendas();
    }

    @PostMapping
    public void cadastrarVenda(@RequestBody Venda venda, @RequestParam Long idCliente,  @RequestParam Long idFuncionario) {
        vendaService.cadastrarVenda(venda, idCliente, idFuncionario);
    }

    @GetMapping("/{id}")
    public Venda obterVenda(@PathVariable Long id) {
        return vendaService.obterVenda(id);
    }

    @DeleteMapping("/{id}")
    public void excluirVenda(@PathVariable Long id) {
        vendaService.excluirVenda(id);
    }

    @PutMapping("/{id}")
    public void alterarVenda(@RequestBody Venda venda, Long id) {
        vendaService.alterarVenda(venda,id);
    }

    @GetMapping("/obterCliente/{id}")
    public Cliente obterCliente(@PathVariable Long id) {
        return vendaService.obterCliente(id);
    }

    @GetMapping("/obterPagamentos")
    public List<PagamentoDTO> obterTodosPagamentos() {
        return pagamentoClient.obterTodosPagamentos();
    }

}
