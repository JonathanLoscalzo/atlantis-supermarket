package com.atlantis.supermarket.business.product.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.atlantis.supermarket.core.product.events.ProductCreatedEvent;
import com.atlantis.supermarket.core.product.search.ProductSolrDto;
import com.atlantis.supermarket.core.shared.BaseServiceEventHandler;
import com.atlantis.supermarket.infrastructure.product.search.ProductSolrRepository;

@Service
public class ProductCreatedHandler extends BaseServiceEventHandler<ProductCreatedEvent>{

    @Autowired
    private ProductSolrRepository repo;
    
    @Async
    @TransactionalEventListener
    public void handle(ProductCreatedEvent event) {
	System.out.println("aloha: " + event);
	//repo.save((ProductSolrDto) event.getProduct().getSolrDto());
    }
}
