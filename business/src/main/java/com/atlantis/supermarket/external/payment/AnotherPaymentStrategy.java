package com.atlantis.supermarket.external.payment;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.external.PaymentStrategy;
import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;

@Component
public class AnotherPaymentStrategy implements PaymentStrategy {

    @Override
    public Map<String, String> pay(Map<String, String> parameters) throws ExternalPaymentException {
	// TODO Auto-generated method stub
	Map<String, String> m = new HashMap<>();
	m.put("id", "some_id");
	return m;
    }

    @Override
    public Map<String, String> cancel(String externalId) {
	// TODO Auto-generated method stub
	Map<String, String> m = new HashMap<>();
	m.put("id", "some_id");
	return m;
    }

}
