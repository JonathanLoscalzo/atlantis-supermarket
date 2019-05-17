package com.atlantis.supermarket.business.sale.exceptions;

import java.util.Collection;
import java.util.Map;

import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;
/**
 * when some strategy payment has an error, or the payment could not be done. <Business Exception>
 * @author jloscalzo
 *
 */
public class ExternalPaymentFailure extends RuntimeException {

    /**
     * 
     * @param payment that fails
     * @param paymentMethods that executes succed
     * @param e
     */
    public ExternalPaymentFailure(Payment payment, Collection<Payment> paymentMethods, ExternalPaymentException e) {
	// TODO Auto-generated constructor stub
	super(e);
    }
}
