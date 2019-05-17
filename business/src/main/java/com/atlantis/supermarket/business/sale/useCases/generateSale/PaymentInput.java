package com.atlantis.supermarket.business.sale.useCases.generateSale;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentInput {
    public String paymentId;
    public BigDecimal pay;
    //property pay has to be in properties map
    public Map<String, String> properties;
}
