package com.projeto.ms_principal.controllers;


import com.projeto.ms_principal.entities.Cliente;
import com.projeto.ms_principal.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obterTodosClientes() {
        return clienteService.obterTodosClientes();
    }

    @PostMapping
    public void cadastrarCliente(@RequestBody Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente obterCliente(@PathVariable Long id) {
        return clienteService.obterCliente(id);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
    }

    @PostMapping("/{id}")
    public void alterarRoupa(@RequestBody Cliente cliente, Long id) {
        clienteService.alterarCliente(cliente,id);
    }
}
