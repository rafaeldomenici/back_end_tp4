package com.example.ms_pagamento.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import java.util.Map;

// config do rabbit do lado do produto-api (quem recebe o pedido de baixa e responde se
// confirmou ou recusou). exchange e routing keys são idênticos aos do mspedido-api -
// isso é obrigatório, os dois lados precisam concordar nesses nomes. só o nome da fila
// muda entre os dois, porque cada serviço tem a sua
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "venda.exchange";

    // fila onde o produto-api recebe os pedidos de baixa vindos do mspedido-api
    public static final String QUEUE_CRIAR_PAGAMENTO =
            "pagamento.criar-pagamento.queue";

    public static final String ROUTING_KEY_CRIAR_PAGAMENTO =
            "pagamento.criar";

    public static final String ROUTING_KEY_CONFIRMADO =
            "pagamento.confirmado";

    public static final String ROUTING_KEY_RECUSADO =
            "pagamento.recusado";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queueCriarPagamento() {
        return new Queue(QUEUE_CRIAR_PAGAMENTO);
    }

    // liga a fila "produto.baixar-estoque.queue" ao exchange, só pra mensagens com
    // routing key "estoque.baixar". não precisa de binding pra
    // "estoque.confirmado"/"estoque.recusado" porque esse serviço PUBLICA essas
    // mensagens (ver EstoqueListener), não consome
    @Bean
    public Binding bindingCriarPagamento() {
        return BindingBuilder
                .bind(queueCriarPagamento())
                .to(exchange())
                .with(ROUTING_KEY_CRIAR_PAGAMENTO);
    }

    // conversor json pras mensagens do rabbit (mesmo motivo do RabbitMQConfig do
    // mspedido-api: sem isso o spring amqp ia tentar serialização java por padrão,
    // que quebra entre pacotes diferentes)
    @Bean
    public MessageConverter jsonMessageConverter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();

        // mesmo apelido usado no mspedido-api pra cada tipo de mensagem, mapeado aqui
        // pra cópia local das classes (pacote com.pedidos.produtoapimysql.messaging)
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setIdClassMapping(Map.of(
                "criarPagamentoCommand", CriarPagamentoCommand.class
                //"resultadoPagamento", ResultadoPagamento.class
        ));
        converter.setClassMapper(classMapper);
        return converter;
    }
}
