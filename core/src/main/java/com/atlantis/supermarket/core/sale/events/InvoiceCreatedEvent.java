package com.atlantis.supermarket.core.sale.events;

import com.atlantis.supermarket.core.sale.Invoice;
import com.atlantis.supermarket.core.shared.BaseEvent;

public class InvoiceCreatedEvent extends BaseEvent {

    Invoice invoice;
    
    public InvoiceCreatedEvent(Invoice invoice) {
	this.invoice = invoice;
    }
}
