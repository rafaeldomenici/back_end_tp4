package com.projeto.ms_principal.repositories;


import com.projeto.ms_principal.entities.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionarioRepositoryTests {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    void cadastrarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario(null, "Pedro","teste@teste.com" ,"1234567",2000,null,null,null);

        Funcionario funcionarioSalvo =  funcionarioRepository.save(funcionario);

        // ASSERT
        assertNotNull(funcionarioSalvo.getId());
    }

    @Test
    void obterFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario(null, "Pedro","teste@teste.com" ,"1234567",2000,null,null,null);

        Funcionario funcionarioSalvo =  funcionarioRepository.save(funcionario);

        Funcionario funcionarioObtido = funcionarioRepository.findById(funcionarioSalvo.getId()).orElseThrow();
        // ASSERT
        assertEquals("Pedro",funcionarioObtido.getNome());
    }

    @Test
    void atualizarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario(null, "Pedro","teste@teste.com" ,"1234567",2000,null,null,null);

        Funcionario funcionarioSalvo =  funcionarioRepository.save(funcionario);
        funcionarioSalvo.setNome("Ricardo");
        Funcionario funcionarioSalvo2 = funcionarioRepository.save(funcionarioSalvo);
        Funcionario funcionarioObtido = funcionarioRepository.findById(funcionarioSalvo2.getId()).orElseThrow();
        // ASSERT
        assertEquals("Ricardo",funcionarioObtido.getNome());
    }

    @Test
    void obterTodosFuncionariosComSucesso() {
        List<Funcionario> funcionarios1 = funcionarioRepository.findAll();
        Funcionario funcionario1 = new Funcionario(null, "Pedro","teste@teste.com" ,"1234567",2000,null,null,null);
        Funcionario funcionario2 = new Funcionario(null, "Ricardo","teste@teste.com" ,"1234567",2000,null,null,null);
        funcionarioRepository.save(funcionario1);
        funcionarioRepository.save(funcionario2);
        List<Funcionario> funcionarios2 = funcionarioRepository.findAll();
        assertEquals(funcionarios1.size() + 2, funcionarios2.size());

    }

    @Test
    void deveDeletarUmFuncionario(){

        Funcionario funcionario = new Funcionario(null, "Pedro","teste@teste.com" ,"1234567",2000,null,null,null);
        Funcionario funcionarioSalvo =  funcionarioRepository.save(funcionario);

        funcionarioRepository.deleteById(funcionarioSalvo.getId());

        Optional<Funcionario> funcionarioEncontrado = funcionarioRepository.findById(funcionarioSalvo.getId());
        assertFalse(funcionarioEncontrado.isPresent());
    }
}
