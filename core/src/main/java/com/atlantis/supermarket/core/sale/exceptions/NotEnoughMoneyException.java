package com.atlantis.supermarket.core.sale.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String total;
    private String payment;

    public NotEnoughMoneyException(String total, String payment) {
	this.total = total;
	this.payment = payment;
    }

    public String getTotal() {
        return total;
    }

    public String getPayment() {
        return payment;
    }
}
