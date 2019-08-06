package com.atlantis.supermarket.core.sale.dto;

import java.util.Collection;
import java.util.Date;

import com.atlantis.supermarket.core.client.dto.ClientDto;
import com.atlantis.supermarket.core.sale.Invoice;
import com.atlantis.supermarket.core.sale.Sale.State;
import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class SaleDto extends BaseEntityDto {
    private ClientDto client;
    private Invoice invoice;
    private Collection<SaleItemDto> items;
    private Collection<PaymentDto> paymentMethods;
    private State state;
    private Date createdAt;
    
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public Collection<SaleItemDto> getItems() {
        return items;
    }
    public void setItems(Collection<SaleItemDto> items) {
        this.items = items;
    }
    public Collection<PaymentDto> getPaymentMethods() {
        return paymentMethods;
    }
    public void setPaymentMethods(Collection<PaymentDto> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public ClientDto getClient() {
	return client;
    }
    public void setClient(ClientDto client) {
	this.client = client;
    }
    public void setCreatedAt(Date createdAt) {
	// TODO Auto-generated method stub
	this.createdAt = createdAt;
    }
    
    public Date getCreatedAt() {
	return this.createdAt;
    }
}
