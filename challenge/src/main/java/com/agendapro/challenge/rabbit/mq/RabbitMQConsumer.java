package com.agendapro.challenge.rabbit.mq;


import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.domain.ProductStatisticsEnum;
import com.agendapro.challenge.service.ProductStatisticsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private final ProductStatisticsService productStatisticsService;

    public RabbitMQConsumer(ProductStatisticsService productStatisticsService) {
        this.productStatisticsService = productStatisticsService;
    }

    @RabbitListener(queues = "${challenge.rabbitmq.queue}")
    public void receivedMessage(CreateUpdateProductRequest createUpdateProductRequest) {
        try {
            System.out.println("Recieved Message From RabbitMQ: " + createUpdateProductRequest);
            productStatisticsService.insertProductStatisticsProductsByCategory(ProductStatisticsEnum.PRODUCT_BY_CATEGORY, createUpdateProductRequest.getCategory());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}