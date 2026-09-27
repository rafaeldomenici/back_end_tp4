package com.projeto.ms_principal.repositories;

import com.projeto.ms_principal.entities.Venda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendaRepositoryTests {

    @Autowired
    private VendaRepository vendaRepository;

    @Test
    void cadastrarVendaComSucesso() {
        Venda venda = new Venda(null,"teste",1L,null,null,null,null,null);

        Venda vendaSalva =  this.vendaRepository.save(venda);

        // ASSERT
        assertNotNull(vendaSalva.getId());
    }

    @Test
    void obterVendaComSucesso() {
        Venda venda = new Venda(null,"teste",1L,null,null,null,null,null);

        Venda vendaSalva =  vendaRepository.save(venda);

        Venda vendaObtida = vendaRepository.findById(vendaSalva.getId()).orElseThrow();
        // ASSERT
        assertEquals("teste",vendaObtida.getDescricao());
    }

    @Test
    void atualizarVendaComSucesso() {
        Venda venda = new Venda(null,"teste",1L,null,null,null,null,null);

        Venda vendaSalva =  vendaRepository.save(venda);
        vendaSalva.setDescricao("teste2");
        Venda vendaSalva2 = vendaRepository.save(vendaSalva);
        Venda vendaObtida = vendaRepository.findById(vendaSalva2.getId()).orElseThrow();
        // ASSERT
        assertEquals("teste2",vendaObtida.getDescricao());
    }

    @Test
    void obterTodasVendasComSucesso() {
        List<Venda> vendas1 = vendaRepository.findAll();
        Venda venda1 = new Venda(null,"teste",1L,null,null,null,null,null);
        Venda venda2 = new Venda(null,"teste",2L,null,null,null,null,null);
        vendaRepository.save(venda1);
        vendaRepository.save(venda2);
        List<Venda> vendas2 = vendaRepository.findAll();
        assertEquals(vendas1.size() + 2, vendas2.size());

    }

    @Test
    void deveDeletarUmaVenda(){

        Venda venda = new Venda(null,"teste",1L,null,null,null,null,null);
        Venda vendaSalva =  vendaRepository.save(venda);

        vendaRepository.deleteById(vendaSalva.getId());

        Optional<Venda> vendaEncontrada = vendaRepository.findById(vendaSalva.getId());
        assertFalse(vendaEncontrada.isPresent());
    }
}
