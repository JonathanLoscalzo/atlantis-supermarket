package com.atlantis.supermarket.core.sale.factory;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.sale.Payment;

@Component
public class PaymentFactory {

    public Payment createPayment(PaymentMethod method, BigDecimal payment, String id) {
	return new Payment().setMethod(method).setPayment(payment).setExternalId(id);
    }
}
