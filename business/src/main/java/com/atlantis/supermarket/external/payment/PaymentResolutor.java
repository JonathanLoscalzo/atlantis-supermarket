package com.atlantis.supermarket.external.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.external.PaymentStrategy;
import com.atlantis.supermarket.core.payment.PaymentType;

/**
 * For external payments, 
 * it returns strategy to solve the payment
 * https://www.mercadopago.com.ar/developers/es/guides/payments/api/receiving-payment-by-card
 * @author jloscalzo
 *
 */
@Component
public class PaymentResolutor {

    @Autowired
    private MercadoPagoStrategy mps;
    
    @Autowired
    private AnotherPaymentStrategy aps;
    
    public PaymentStrategy getPaymentStrategy(PaymentType key) {
	switch (key) {
	case MERCADOPAGO:
	    return mps;
	case ANOTHERPAYMENT:
	    return aps;
	default:
	    return null;
	}
    }
}
