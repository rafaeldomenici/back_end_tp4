package com.projeto.ms_principal.services;

import com.projeto.ms_principal.entities.Roupa;
import com.projeto.ms_principal.repositories.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoupaService {

    @Autowired
    private RoupaRepository roupaRepository;

    public List<Roupa> obterTodasRoupas() {
        return roupaRepository.findAll();
    }

    public void cadastrarRoupa(Roupa roupa) {
        roupaRepository.save(roupa);
    }

    public Roupa obterRoupa(Long id) {
        return roupaRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void excluirRoupa(Long id) {
        Roupa roupa = roupaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        roupaRepository.delete(roupa);
    }

    public void alterarRoupa(Roupa roupa, Long id) {
        Roupa roupaBusca = roupaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        roupaBusca.setNome(roupa.getNome());
        roupaBusca.setTamanho(roupa.getTamanho());
        roupaBusca.setPreco(roupa.getPreco());
        roupaBusca.setGenero(roupa.getGenero());
        roupaBusca.setQuantidadeEmEstoque(roupa.getQuantidadeEmEstoque());
        roupaRepository.save(roupaBusca);

    }

}
