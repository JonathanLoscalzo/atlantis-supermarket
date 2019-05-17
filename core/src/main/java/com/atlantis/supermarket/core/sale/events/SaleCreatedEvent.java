package com.atlantis.supermarket.core.sale.events;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.shared.BaseEvent;

public class SaleCreatedEvent extends BaseEvent {
    
    private Sale sale;
    
    public Sale getSale() {
        return sale;
    }

    public SaleCreatedEvent(Sale sale) {
	this.sale = sale;
    }
}
