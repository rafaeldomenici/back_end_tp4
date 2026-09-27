package com.projeto.ms_principal.services;

import com.projeto.ms_principal.entities.Funcionario;
import com.projeto.ms_principal.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> obterTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public Funcionario obterFuncionario(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void excluirFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
        funcionarioRepository.delete(funcionario);
    }

    public void alterarFuncionario(Funcionario funcionario, Long id) {
        Funcionario funcionarioBusca = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
        funcionarioBusca.setNome(funcionario.getNome());
        funcionarioBusca.setEmail(funcionario.getEmail());
        funcionarioBusca.setTelefone(funcionario.getTelefone());
        funcionarioBusca.setSalario(funcionario.getSalario());
        funcionarioRepository.save(funcionarioBusca);
    }
}
