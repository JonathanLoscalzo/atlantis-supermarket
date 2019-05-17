package com.atlantis.supermarket.core.sale.factory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.validation.ClientExistValidation;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.ProductConsumed;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.SaleItem;
import com.atlantis.supermarket.core.sale.validation.PaymentValidation;

@Component
public class SaleFactory {

    @Autowired
    private PaymentValidation paymentValidation;
    
    @Autowired
    private ClientExistValidation clientExistValidation;
    
    /**
     * Algunas excepciones van a ser elevadas en la capa de servicio.
     * @param client
     * @param items
     * @param paymentMethods
     * @return
     */
    @Transactional
    public Sale createSale(Client client, Collection<SaleItem> items, Collection<Payment> paymentMethods) {
	
	if (items.isEmpty()) {

	}

	if (paymentMethods.isEmpty()) {

	}
	
	BigDecimal total = Sale.total(items);
	
	// TODO: ver si se puede desacoplar esto. en caso de cupones...
	paymentValidation.validate(total, paymentMethods);
	clientExistValidation.validate(client);
	
	Sale s = new Sale();
	
	items.stream().forEach(x -> x.setSale(s));
	paymentMethods.stream().forEach(x -> x.setSale(s));
	client.addSale(s);
	
	s.setClient(client);
	s.setItems(items);
	s.setPaymentMethods(paymentMethods);
	
	s.setSaleEvent();
	client.addSale(s);
	
	return s;
    }

}
