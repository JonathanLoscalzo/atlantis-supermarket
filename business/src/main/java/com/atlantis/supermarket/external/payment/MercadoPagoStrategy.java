package com.atlantis.supermarket.external.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.external.PaymentStrategy;
import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;

@Component
public class MercadoPagoStrategy implements PaymentStrategy {

    @Override
    public Map<String, String> pay(Map<String, String> parameters) throws ExternalPaymentException {
	// 
	Map<String, String> m = new HashMap<>();
	m.put("id", "some_id");
	return m;
    }

    @Override
    public Map<String, String> cancel(String externalId) {
	// TODO PUEDE SER ASYNC
	Map<String, String> m = new HashMap<>();
	m.put("id", "some_id");
	return m;
    }

}
