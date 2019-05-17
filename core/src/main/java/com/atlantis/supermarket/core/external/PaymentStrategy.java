package com.atlantis.supermarket.core.external;

import java.util.Map;

import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;

public interface PaymentStrategy {

    /**
     * Define parameters dynamically
     * @param parameters
     * @return
     */
    public Map<String, String> pay(Map<String, String> parameters) throws ExternalPaymentException;
    
    public Map<String, String> cancel(String externalId);
}
