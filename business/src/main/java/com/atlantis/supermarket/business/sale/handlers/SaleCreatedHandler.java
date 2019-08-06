package com.atlantis.supermarket.business.sale.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.atlantis.supermarket.core.sale.events.SaleCreatedEvent;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;
import com.atlantis.supermarket.infrastructure.external.invoice.InvoiceSender;

@Service
public class SaleCreatedHandler extends BaseServiceEventHandler<SaleCreatedEvent> {

    @Autowired
    private InvoiceSender sender;
    
    @Async
    @TransactionalEventListener
    public void handle(SaleCreatedEvent event) {
	sender.send(event.getSale().getId().toString());
    }
}
