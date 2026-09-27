package com.projeto.ms_principal.repositories;

import com.projeto.ms_principal.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
