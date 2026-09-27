package com.projeto.ms_principal.repositories;


import com.projeto.ms_principal.entities.ItemVenda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemVendaRepositoryTests {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Test
    void cadastrarItemVendaComSucesso() {
        ItemVenda itemVenda = new ItemVenda(null,null,null,null,null,4,null);

        ItemVenda itemVendaSalvo =  itemVendaRepository.save(itemVenda);

        // ASSERT
        assertNotNull(itemVendaSalvo.getId());
    }

    @Test
    void obterItemVendaComSucesso() {
        ItemVenda itemVenda = new ItemVenda(null,null,null,null,null,4,null);
        ItemVenda itemVendaSalvo =  itemVendaRepository.save(itemVenda);

        ItemVenda itemVendaObtido = itemVendaRepository.findById(itemVendaSalvo.getId()).orElseThrow();
        // ASSERT
        assertEquals(4,itemVendaObtido.getQuantidade());
    }

    @Test
    void atualizarItemVendaComSucesso() {
        ItemVenda itemVenda = new ItemVenda(null,null,null,null,null,4,null);
        ItemVenda itemVendaSalvo =  itemVendaRepository.save(itemVenda);
        itemVendaSalvo.setQuantidade(2);
        ItemVenda itemVendaSalvo2 = itemVendaRepository.save(itemVendaSalvo);
        ItemVenda itemVendaObtido = itemVendaRepository.findById(itemVendaSalvo2.getId()).orElseThrow();
        // ASSERT
        assertEquals(2,itemVendaObtido.getQuantidade());
    }

    @Test
    void obterTodosItensVendaComSucesso() {
        List<ItemVenda> itensVenda1 = itemVendaRepository.findAll();
        ItemVenda itemVenda1 = new ItemVenda(null,null,null,null,null,4,null);
        ItemVenda itemVenda2 = new ItemVenda(null,null,null,null,null,3,null);
        itemVendaRepository.save(itemVenda1);
        itemVendaRepository.save(itemVenda2);
        List<ItemVenda> itensVenda2 = itemVendaRepository.findAll();
        assertEquals(itensVenda1.size() + 2, itensVenda2.size());

    }

    @Test
    void deveDeletarUmItemVenda(){

        ItemVenda itemVenda = new ItemVenda(null,null,null,null,null,4,null);
        ItemVenda itemVendaSalvo =  itemVendaRepository.save(itemVenda);

        itemVendaRepository.deleteById(itemVendaSalvo.getId());

        Optional<ItemVenda> itemVendaEncontrado = itemVendaRepository.findById(itemVendaSalvo.getId());
        assertFalse(itemVendaEncontrado.isPresent());
    }
}
