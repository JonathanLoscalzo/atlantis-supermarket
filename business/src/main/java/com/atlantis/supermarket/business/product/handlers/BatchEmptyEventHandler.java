package com.atlantis.supermarket.business.product.handlers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

import com.atlantis.supermarket.core.product.events.BatchEmptyEvent;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;

public class BatchEmptyEventHandler extends BaseServiceEventHandler<BatchEmptyEvent>{

    @Async
    @TransactionalEventListener
    public void handle(BatchEmptyEvent event) {
	System.out.println("aloha: " + event);
	//TODO: sin utilidad, PODRÍA PERSISTIR EL EVENTO.
    }
}
