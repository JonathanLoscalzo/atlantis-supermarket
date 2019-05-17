package com.atlantis.supermarket.infrastructure.external.invoice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.sale.gateway.GeneratePayment;

@Component
@RabbitListener(queues = "invoice_queue")
public class InvoiceReceiver {
    
    @Autowired
    private GeneratePayment generatePayment;
    
    public void receiveInvoice(String saleId) {
        System.out.println("Received <" + saleId + ">");
        generatePayment.to(saleId);
    }
}
