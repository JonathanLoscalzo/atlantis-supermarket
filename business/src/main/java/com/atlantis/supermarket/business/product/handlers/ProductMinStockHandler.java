package com.atlantis.supermarket.business.product.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.atlantis.supermarket.core.external.EmailService;
import com.atlantis.supermarket.core.external.MessageService;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.events.ProductMinStockEvent;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class ProductMinStockHandler extends BaseServiceEventHandler<ProductMinStockEvent> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageService messageService;

    @Value("from@atlantis.com")
    private String from;

    @Autowired
    private ProductRepository productRepository;

    @Async
    @EventListener(ProductMinStockEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductMinStockEvent event) {
	// send notifications to admin
	// TODO

	// send notification to provider if exists
	Provider p = event.getProduct().getProvider();

	if (p == null)
	    return;

	String body = String.format("El producto %s %s se encuentra bajo en stock. Por favor acercarse",
		event.getProduct().getBrand(), event.getProduct().getName());
	emailService.sendText(from, p.getEmail(), "Producto bajo en stock", body);

	messageService.sendText(p.getPhone(), body);

	Product product = productRepository.findById(event
		.getProduct()
		.getId()).get();
	
	product.setNotifiedProvider(true);

	productRepository.save(product);
    }

}
