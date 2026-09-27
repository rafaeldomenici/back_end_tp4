package com.projeto.ms_principal.controllers;


import com.projeto.ms_principal.entities.Roupa;
import com.projeto.ms_principal.services.RoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaService roupaService;

    @GetMapping
    public List<Roupa> obterTodasRoupas() {
       return roupaService.obterTodasRoupas();
    }

    @PostMapping
    public void cadastrarRoupa(@RequestBody Roupa roupa) {
        roupaService.cadastrarRoupa(roupa);
    }

    @GetMapping("/{id}")
    public Roupa obterRoupa(@PathVariable Long id) {
        return roupaService.obterRoupa(id);
    }

    @DeleteMapping("/{id}")
    public void excluirRoupa(@PathVariable Long id) {
        roupaService.excluirRoupa(id);
    }

    @PostMapping("/{id}")
    public void alterarRoupa(@RequestBody Roupa roupa, Long id) {
        roupaService.alterarRoupa(roupa,id);
    }

}
