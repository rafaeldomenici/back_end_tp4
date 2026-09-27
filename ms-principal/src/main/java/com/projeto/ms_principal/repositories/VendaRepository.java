package com.projeto.ms_principal.repositories;

import com.projeto.ms_principal.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
