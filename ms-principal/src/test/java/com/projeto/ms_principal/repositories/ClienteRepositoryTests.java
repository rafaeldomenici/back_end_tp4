package com.projeto.ms_principal.repositories;


import com.projeto.ms_principal.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteRepositoryTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void cadastrarClienteComSucesso() {
        Cliente cliente = new Cliente(null, "Pedro", "123456789", "123455678",null,null,null);

        Cliente clienteSalvo =  clienteRepository.save(cliente);

        // ASSERT
        assertNotNull(clienteSalvo.getId());
    }

    @Test
    void obterClienteComSucesso() {
        Cliente cliente = new Cliente(null, "Pedro", "123456789", "123455678",null,null,null);

        Cliente clienteSalvo =  clienteRepository.save(cliente);

        Cliente clienteObtido = clienteRepository.findById(clienteSalvo.getId()).orElseThrow();
        // ASSERT
        assertEquals("Pedro",clienteObtido.getNome());
    }

    @Test
    void atualizarClienteComSucesso() {
        Cliente cliente = new Cliente(null, "Pedro", "123456789", "123455678",null,null,null);

        Cliente clienteSalvo =  clienteRepository.save(cliente);
        clienteSalvo.setNome("Ricardo");
        Cliente clienteSalvo2 = clienteRepository.save(clienteSalvo);
        Cliente clienteObtido = clienteRepository.findById(clienteSalvo2.getId()).orElseThrow();
        // ASSERT
        assertEquals("Ricardo",clienteObtido.getNome());
    }

    @Test
    void obterTodosClientesComSucesso() {
        List<Cliente> clientes1 = clienteRepository.findAll();
        Cliente cliente1 = new Cliente(null, "Pedro", "123456789", "123455678",null,null,null);
        Cliente cliente2 = new Cliente(null, "Ricardo", "123456789", "123455678",null,null,null);
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        List<Cliente> clientes2 = clienteRepository.findAll();
        assertEquals(clientes1.size() + 2, clientes2.size());

    }

    @Test
    void deveDeletarUmCliente(){

        Cliente cliente = new Cliente(null, "Pedro", "123456789", "123455678",null,null,null);
        Cliente clienteSalvo =  this.clienteRepository.save(cliente);

        clienteRepository.deleteById(clienteSalvo.getId());

        Optional<Cliente> clienteEncontrado = this.clienteRepository.findById(clienteSalvo.getId());
        assertFalse(clienteEncontrado.isPresent());
    }
}
