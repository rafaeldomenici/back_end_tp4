package com.projeto.ms_principal.repositories;


import com.projeto.ms_principal.entities.Roupa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoupaRepositoryTests {

    @Autowired
    private RoupaRepository roupaRepository;

    @Test
    void cadastrarRoupaComSucesso() {
        Roupa roupa = new Roupa(null,"camisa azul",'G',150,"teste",2,null,null,null);

        Roupa roupaSalva =  roupaRepository.save(roupa);

        // ASSERT
        assertNotNull(roupaSalva.getId());
    }

    @Test
    void obterRoupaComSucesso() {
        Roupa roupa = new Roupa(null,"camisa azul",'G',150,"teste",2,null,null,null);
        Roupa roupaSalva = roupaRepository.save(roupa);

        Roupa roupaObtida = roupaRepository.findById(roupaSalva.getId()).orElseThrow();
        // ASSERT
        assertEquals("camisa azul",roupaObtida.getNome());
    }

    @Test
    void atualizarRoupaComSucesso() {
        Roupa roupa = new Roupa(null,"camisa azul",'G',150,"teste",2,null,null,null);

        Roupa roupaSalva =  roupaRepository.save(roupa);
        roupaSalva.setTamanho('M');
        Roupa roupaSalva2 = roupaRepository.save(roupaSalva);
        Roupa roupaObtida = roupaRepository.findById(roupaSalva2.getId()).orElseThrow();
        // ASSERT
        assertEquals('M',roupaObtida.getTamanho());
    }

    @Test
    void obterTodasRoupasComSucesso() {
        List<Roupa> roupas1 = roupaRepository.findAll();
        Roupa roupa1 = new Roupa(null,"camisa azul",'G',150,"teste",2,null,null,null);
        Roupa roupa2 = new Roupa(null,"camisa amarela",'G',150,"teste",2,null,null,null);
        roupaRepository.save(roupa1);
        roupaRepository.save(roupa2);
        List<Roupa> roupas2 = roupaRepository.findAll();
        assertEquals(roupas1.size() + 2, roupas2.size());

    }

    @Test
    void deveDeletarUmaRoupa(){

        Roupa roupa = new Roupa(null,"camisa azul",'G',150,"teste",2,null,null,null);
        Roupa roupaSalva = roupaRepository.save(roupa);

        roupaRepository.deleteById(roupaSalva.getId());

        Optional<Roupa> roupaEncontrada = roupaRepository.findById(roupaSalva.getId());
        assertFalse(roupaEncontrada.isPresent());
    }
}
