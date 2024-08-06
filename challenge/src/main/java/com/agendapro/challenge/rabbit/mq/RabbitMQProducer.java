package com.agendapro.challenge.rabbit.mq;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate rabbitAmqpTemplate;

    @Value("${challenge.rabbitmq.exchange}")
    private String exchange;

    @Value("${challenge.rabbitmq.routingkey}")
    private String routingkey;

    public void send(CreateUpdateProductRequest createUpdateProductRequest) {
        rabbitAmqpTemplate.convertAndSend(exchange, routingkey, createUpdateProductRequest);
        System.out.println("Send msg = " + createUpdateProductRequest.toString());

    }

}