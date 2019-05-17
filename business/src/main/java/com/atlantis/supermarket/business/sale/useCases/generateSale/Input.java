package com.atlantis.supermarket.business.sale.useCases.generateSale;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import com.atlantis.supermarket.core.shared.business.InputPort;

public class Input extends InputPort {

    public String clientId;
    //public String userId;
    public Map<String, Double> consume;
    public Collection<PaymentInput> payments;
}
