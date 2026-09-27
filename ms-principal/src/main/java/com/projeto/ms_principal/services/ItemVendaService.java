package com.projeto.ms_principal.services;

import com.projeto.ms_principal.entities.*;
import com.projeto.ms_principal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private RoupaRepository roupaRepository;

    public List<ItemVenda> obterTodosItensVenda() {
        return itemVendaRepository.findAll();
    }

    public void cadastrarItemVenda(ItemVenda itemVenda, Long idVenda, Long idRoupa) {
        Venda venda = vendaRepository.findById(idVenda).orElseThrow(() -> new RuntimeException());
        Roupa roupa = roupaRepository.findById(idRoupa).orElseThrow(() -> new RuntimeException());
        itemVenda.venda = venda;
        itemVenda.roupa = roupa;
        itemVenda.setNomeRoupa(roupa.getNome());
        itemVendaRepository.save(itemVenda);
    }

    public ItemVenda obterItemVenda(Long id) {
        return itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void excluirItemVenda(Long id) {
        ItemVenda itemVenda = itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        itemVendaRepository.delete(itemVenda);
    }

    public void alterarItemVenda(ItemVenda itemVenda, Long id) {
        ItemVenda itemVendaBusca = itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        itemVendaBusca.setQuantidade(itemVenda.getQuantidade());
        itemVendaRepository.save(itemVendaBusca);
    }

    public Roupa obterRoupaItemVenda(Long id) {
        ItemVenda itemVenda = itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return itemVenda.roupa;
    }

}
