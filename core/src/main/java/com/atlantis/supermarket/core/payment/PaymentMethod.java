package com.atlantis.supermarket.core.payment;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.user.User.UserRole;

@Entity
public class PaymentMethod extends BaseEntity {
    
    @Column
    private String name;

    @Column
    private Boolean allowChange;
    
    @Column
    private Boolean externalPayment;
    
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowChange() {
        return allowChange;
    }

    public void setAllowChange(Boolean allowChange) {
        this.allowChange = allowChange;
    }

    public Boolean getExternalPayment() {
        return externalPayment;
    }

    public void setExternalPayment(Boolean externalPayment) {
        this.externalPayment = externalPayment;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

}
