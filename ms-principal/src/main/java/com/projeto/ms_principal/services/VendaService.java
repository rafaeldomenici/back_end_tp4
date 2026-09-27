package com.projeto.ms_principal.services;

import com.projeto.ms_principal.entities.Cliente;
import com.projeto.ms_principal.entities.Funcionario;
import com.projeto.ms_principal.entities.Venda;
import com.projeto.ms_principal.repositories.ClienteRepository;
import com.projeto.ms_principal.repositories.FuncionarioRepository;
import com.projeto.ms_principal.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Venda> obterTodasVendas() {
        return vendaRepository.findAll();
    }

    public void cadastrarVenda(Venda venda, Long idCliente, Long idFuncionario) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException());
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() -> new RuntimeException());
        venda.cliente = cliente;
        venda.funcionario = funcionario;
        vendaRepository.save(venda);
    }

    public Venda obterVenda(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void excluirVenda(Long id) {
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        vendaRepository.delete(venda);
    }

    public void alterarVenda(Venda venda, Long id) {
        Venda vendaBusca = vendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        vendaBusca.setIdPagamento(venda.getIdPagamento());
        vendaRepository.save(vendaBusca);
    }

    public Cliente obterCliente(Long id) {
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return venda.cliente;
    }


}
