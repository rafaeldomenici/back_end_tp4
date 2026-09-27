package com.projeto.ms_principal.services;

import com.projeto.ms_principal.entities.Cliente;
import com.projeto.ms_principal.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obterTodosClientes() {
        return clienteRepository.findAll();
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente obterCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void excluirCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException());
        clienteRepository.delete(cliente);
    }

    public void alterarCliente(Cliente cliente, Long id) {
        Cliente clienteBusca = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException());
        clienteBusca.setNome(cliente.getNome());
        clienteBusca.setEmail(cliente.getEmail());
        clienteBusca.setTelefone(cliente.getTelefone());
        clienteRepository.save(clienteBusca);
    }
}
