package com.atlantis.supermarket.core.sale.dto;

import java.math.BigDecimal;

import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class PaymentDto extends BaseEntityDto {
    private PaymentMethod method;
    
    private BigDecimal payment;
    
    private Boolean canceled;
    
    private String externalId;

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
