package com.atlantis.supermarket.core.sale;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.data.util.Pair;

import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="method_id")
    private PaymentMethod method;
    
    @Column
    private BigDecimal payment;
    
    @ManyToOne()
    @JoinColumn(name="sale_id")
    @JsonBackReference
    private Sale sale;
    
    @Column
    private Boolean canceled;
    
    @Column
    private String externalId;
    
    @Transient
    private List<Pair<String, String>> data;
    
    public Payment() {
	this.canceled = true;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public Payment setMethod(PaymentMethod method) {
        this.method = method;
        return this;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public Payment setPayment(BigDecimal payment) {
        this.payment = payment;
        return this;
    }

    public Sale getSale() {
        return sale;
    }

    public Payment setSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public List<Pair<String, String>> getData() {
        return data;
    }

    public Payment setData(List<Pair<String, String>> data) {
        this.data = data;
        return this;
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

    public Payment setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }
}
