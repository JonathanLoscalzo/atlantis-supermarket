package com.atlantis.supermarket.core.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.sale.events.SaleCreatedEvent;
import com.atlantis.supermarket.core.sale.exceptions.NeedExactChangeMoneyException;
import com.atlantis.supermarket.core.sale.exceptions.NotEnoughMoneyException;
import com.atlantis.supermarket.core.sale.exceptions.PaymentCalculationErrorException;
import com.atlantis.supermarket.core.sale.exceptions.SaleObtainTotalErrorException;
import com.atlantis.supermarket.core.sale.factory.SaleFactory;
import com.atlantis.supermarket.core.sale.validation.PaymentValidation;
import com.atlantis.supermarket.core.shared.BaseEntityAuditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sale")
public class Sale extends BaseEntityAuditable {

    public enum State {
	GENERATED, BILLED
    }

    @Autowired
    @Transient
    private SaleFactory factory;

    @Autowired
    @Transient
    private PaymentValidation paymentValidation;

    @Transient
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<SaleItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Payment> paymentMethods = new ArrayList<>();

    @Enumerated
    private State state = State.GENERATED;

    public Sale() {
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    public Invoice getInvoice() {
	return invoice;
    }

    public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
    }

    public Collection<SaleItem> getItems() {
	return items;
    }

    public void setItems(Collection<SaleItem> items) {
	this.items = items;
    }

    public Collection<Payment> getPaymentMethods() {
	return paymentMethods;
    }

    public void setPaymentMethods(Collection<Payment> paymentMethods) {
	this.paymentMethods = paymentMethods;
    }

    public State getState() {
	return state;
    }

    public void setState(State state) {
	this.state = state;
    }

    public BigDecimal total() throws SaleObtainTotalErrorException {
	return Sale.total(this.getItems());
    }

    public static BigDecimal total(Collection<SaleItem> items) throws SaleObtainTotalErrorException {
	return items.stream()
		.map(SaleItem::totalPrice)
		.reduce(BigDecimal::add)
		.orElseThrow(() -> new SaleObtainTotalErrorException());
    }

    public Sale createSale(Client client, Collection<SaleItem> items, Collection<Payment> paymentMethods)
	    throws PaymentCalculationErrorException, SaleObtainTotalErrorException, NotEnoughMoneyException,
	    NeedExactChangeMoneyException {
	return factory.createSale(client, items, paymentMethods);

    }

    public void setSaleEvent() {
	this.registerEvent(new SaleCreatedEvent(this));
    }
}
