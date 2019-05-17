package com.atlantis.supermarket.core.sale.exceptions;

import com.atlantis.supermarket.core.sale.Payment;

/**
 * when some strategy payment has an error, or the payment could not be done
 * @author jloscalzo
 *
 */

public class ExternalPaymentException extends RuntimeException {

    private Payment payment;
    
    public ExternalPaymentException(Payment payment) {
	this.setPayment(payment);
    }

    public Payment getPayment() {
	return payment;
    }

    public void setPayment(Payment payment) {
	this.payment = payment;
    }
}
