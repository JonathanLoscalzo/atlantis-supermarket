package com.atlantis.supermarket.core.sale.exceptions;

import java.math.BigDecimal;

public class NeedExactChangeMoneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private BigDecimal total;
    private BigDecimal payment;

    public NeedExactChangeMoneyException(BigDecimal total, BigDecimal payment) {
	this.total = total;
	this.payment = payment;
    }

    public BigDecimal getTotal() {
	return total;
    }

    public BigDecimal getPayment() {
	return payment;
    }

}
