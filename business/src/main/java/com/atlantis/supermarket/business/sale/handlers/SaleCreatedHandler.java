package com.atlantis.supermarket.business.sale.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.atlantis.supermarket.core.sale.events.SaleCreatedEvent;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;
import com.atlantis.supermarket.infrastructure.external.invoice.InvoiceSender;

public class SaleCreatedHandler extends BaseServiceEventHandler<SaleCreatedEvent> {

    @Autowired
    private InvoiceSender sender;
    
    @Override
    public void handle(SaleCreatedEvent event) {
	sender.send(event.getSale().getId().toString());
    }
}
