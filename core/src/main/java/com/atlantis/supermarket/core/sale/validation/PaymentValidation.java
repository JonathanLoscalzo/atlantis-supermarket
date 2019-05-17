package com.atlantis.supermarket.core.sale.validation;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.exceptions.NeedExactChangeMoneyException;
import com.atlantis.supermarket.core.sale.exceptions.NotEnoughMoneyException;
import com.atlantis.supermarket.core.sale.exceptions.PaymentCalculationErrorException;

@Component
public class PaymentValidation {

    public void validate(BigDecimal total, Collection<Payment> paymentMethods) throws PaymentCalculationErrorException, NotEnoughMoneyException, NeedExactChangeMoneyException {
	BigDecimal payment = paymentMethods.stream()
		.map(Payment::getPayment)
		.reduce(BigDecimal::add)
		.orElseThrow(() -> new PaymentCalculationErrorException());

	if (payment.compareTo(total) < 0) {
	    throw new NotEnoughMoneyException(total.toString(), payment.toString());
	}

	if (payment.compareTo(total) > 0 && !paymentMethods.stream().anyMatch(x -> x.getMethod().getAllowChange())) {
	    throw new NeedExactChangeMoneyException(total, payment);
	}
    }
}
