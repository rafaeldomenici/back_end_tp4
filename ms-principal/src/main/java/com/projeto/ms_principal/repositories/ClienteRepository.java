package com.projeto.ms_principal.repositories;

import com.projeto.ms_principal.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
