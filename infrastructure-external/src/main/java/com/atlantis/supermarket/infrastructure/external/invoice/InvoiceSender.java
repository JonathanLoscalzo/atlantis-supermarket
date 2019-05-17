package com.atlantis.supermarket.infrastructure.external.invoice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InvoiceSender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;
    
    
    public void send(String saleId) {
        this.template.convertAndSend(queue.getName(), saleId);
    }
    
    /*@Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void sendTest() {
        this.template.convertAndSend(queue.getName(), "pruebita");
    }*/
}
