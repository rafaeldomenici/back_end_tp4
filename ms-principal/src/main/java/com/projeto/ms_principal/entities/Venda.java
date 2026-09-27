package com.projeto.ms_principal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Venda {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Long idPagamento;

    @CreatedDate // Preenchido automaticamente
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    @LastModifiedDate
    private LocalDateTime atualizadoEm;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    public Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id")
    @JsonIgnore
    public Funcionario funcionario;

    @OneToMany(mappedBy = "venda")
    List<ItemVenda> itensVenda;
}
