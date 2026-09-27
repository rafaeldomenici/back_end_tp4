package com.projeto.ms_principal.controllers;

import com.projeto.ms_principal.entities.ItemVenda;
import com.projeto.ms_principal.entities.Roupa;
import com.projeto.ms_principal.services.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensvenda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    public List<ItemVenda> obterTodosItensVenda() {
        return itemVendaService.obterTodosItensVenda();
    }

    @PostMapping
    public void cadastrarItemVenda(@RequestBody ItemVenda itemVenda, @RequestParam Long idVenda, @RequestParam Long idRoupa) {
        itemVendaService.cadastrarItemVenda(itemVenda, idVenda, idRoupa);
    }

    @GetMapping("/{id}")
    public ItemVenda obterItemVenda(@PathVariable Long id) {
        return itemVendaService.obterItemVenda(id);
    }

    @DeleteMapping("/{id}")
    public void excluirItemVenda(@PathVariable Long id) {
        itemVendaService.excluirItemVenda(id);
    }

    @PostMapping("/{id}")
    public void alterarItemVenda(@RequestBody ItemVenda itemVenda, Long id) {
        itemVendaService.alterarItemVenda(itemVenda,id);
    }

    @GetMapping("/obter_roupa/{id}")
    public Roupa obterRoupaItemVenda(@PathVariable Long id) {
        Roupa roupaObtida = itemVendaService.obterRoupaItemVenda(id);
        return roupaObtida;
    }
}
