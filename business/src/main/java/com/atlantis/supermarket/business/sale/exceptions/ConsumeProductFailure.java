package com.atlantis.supermarket.business.sale.exceptions;

/**
 * in case some product consume error trying to generate sale
 * @author jloscalzo
 *
 */
public class ConsumeProductFailure extends RuntimeException {

    public ConsumeProductFailure(String msg, Exception e) {
	super(msg, e);
    }
}
