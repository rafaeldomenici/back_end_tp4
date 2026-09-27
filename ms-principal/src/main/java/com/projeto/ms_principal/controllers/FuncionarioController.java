package com.projeto.ms_principal.controllers;

import com.projeto.ms_principal.entities.Funcionario;
import com.projeto.ms_principal.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> obterTodosFuncionarios() {
        return funcionarioService.obterTodosFuncionarios();
    }

    @PostMapping
    public void cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.cadastrarFuncionario(funcionario);
    }

    @GetMapping("/{id}")
    public Funcionario obterFuncionario(@PathVariable Long id) {
        return funcionarioService.obterFuncionario(id);
    }

    @DeleteMapping("/{id}")
    public void excluirFuncionsrio(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
    }

    @PostMapping("/{id}")
    public void alterarFuncionario(@RequestBody Funcionario funcionario, Long id) {
        funcionarioService.alterarFuncionario(funcionario,id);
    }
}
