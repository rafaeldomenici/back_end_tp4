package com.projeto.ms_principal.clients;

import com.projeto.ms_principal.dto.PagamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="ms-pagamento", url="http://localhost:8081/")
public interface PagamentoClient {

    @GetMapping("/obter")
    public List<PagamentoDTO> obterTodosPagamentos();


}
