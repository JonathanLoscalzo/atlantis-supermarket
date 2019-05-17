package com.atlantis.supermarket.core.sale;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.atlantis.supermarket.core.sale.events.InvoiceCreatedEvent;
import com.atlantis.supermarket.core.shared.BaseEntityAuditable;

@Entity
public class Invoice extends BaseEntityAuditable {

    @OneToOne(mappedBy = "invoice")
    private Sale sale;
    
    public void invoiceCreated() {
	this.registerEvent( new InvoiceCreatedEvent(this));
    }
}
