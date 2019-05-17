package com.atlantis.supermarket.business.product.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.atlantis.supermarket.core.external.EmailService;
import com.atlantis.supermarket.core.external.MessageService;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.events.ProductMinStockEvent;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;

@Service
public class ProductMinStockHandler extends BaseServiceEventHandler<ProductMinStockEvent> {

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private MessageService messageService;
    
    @Value("from@atlantis.com")
    private String from;
    
    @Async
    @TransactionalEventListener
    @Override
    public void handle(ProductMinStockEvent event) {
	// send notifications to admin
	//TODO
	
	// send notification to provider if exists
	Provider p = event.getProduct().getProvider();
	
	if (p == null) return;
	
	String body = String.format("El producto %s %s se encuentra bajo en stock. Por favor acercarse", event.getProduct().getBrand(), event.getProduct().getName()); 
	emailService.sendText(from, p.getEmail(), "Producto bajo en stock", body);
	
	messageService.sendText(p.getPhone(), body);
	
    }
    
}
