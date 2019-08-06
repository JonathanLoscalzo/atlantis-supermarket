package com.atlantis.supermarket.infrastructure.external.invoice;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.sale.gateway.GeneratePayment;

/**
 * Aquí se reciben los eventos de manera desacoplada. Aquí podríamos generar un
 * servicio de facturación con una demora.
 * 
 * @author jloscalzo
 *
 */
@Component
@RabbitListener(queues = "invoice_queue")
public class InvoiceReceiver {

    Logger logger = LoggerFactory.getLogger(InvoiceReceiver.class);
    
    @Autowired
    private GeneratePayment generatePayment;

    public void receiveInvoice(String saleId) {
	try {
	    try {
		TimeUnit.SECONDS.sleep(15);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    generatePayment.to(saleId);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
    }
}
