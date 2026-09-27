package com.example.ms_pagamento.messaging;


import com.example.ms_pagamento.model.Pagamento;
import com.example.ms_pagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class VendaListener {
    private final PagamentoService pagamentoService;
    private final RabbitTemplate rabbitTemplate;

    public VendaListener(PagamentoService pagamentoService, RabbitTemplate rabbitTemplate) {
        this.pagamentoService = pagamentoService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CRIAR_PAGAMENTO)
    public void receber(CriarPagamentoCommand comando) {

        System.out.println("Mensagem recebida pelo produto-api!");
        System.out.println("IdVenda: " + comando.getVendaId());
        System.out.println("IdPagamento: " + comando.getPagamentoId());
        System.out.println("Tipo: " + comando.getTipo());
        System.out.println("Parcelas: " + comando.getParcelas());
        System.out.println("A vista: " + comando.isAVista());
        try {

            // chama o service que já existe pra baixar o estoque de fato. se o estoque
            // tiver insuficiente agora (que é a info mais atual, pode ter mudado desde
            // a checagem otimista lá no mspedido-api), estoura IllegalArgumentException
            // e cai no catch
            Pagamento pagamento = new Pagamento(null,comando.getTipo(),comando.isAVista(),comando.getParcelas(),comando.getVendaId());
            pagamentoService.cadastrarPagamento(
                    pagamento
            );

            // deu certo, avisa o mspedido-api publicando em "estoque.confirmado"
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY_CONFIRMADO,
                    new ResultadoPagamento(
                            comando.getVendaId(),
                            comando.getPagamentoId(),
                            comando.getTipo(),
                            true,
                            3
                    )
            );

        } catch (IllegalArgumentException e) {


        }
    }
}
