package com.atlantis.supermarket.core.payment.exception;

import java.util.Collection;
import java.util.List;
import com.atlantis.supermarket.core.payment.PaymentMethod;

public class PaymentMethodNotFound extends RuntimeException {

    public PaymentMethodNotFound(Collection<PaymentMethod> methods, List<String> ids) {
	// TODO Auto-generated constructor stub
    }

}
