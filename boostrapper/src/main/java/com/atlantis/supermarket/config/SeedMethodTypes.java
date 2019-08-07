package com.atlantis.supermarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.payment.PaymentType;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.infrastructure.payment.PaymentMethodRepository;

@Component
public class SeedMethodTypes {
    @Autowired
    PaymentMethodRepository repo;

    public void addMercadoPago() {

	if (repo.findAll().size() == 0) {
	    PaymentMethod pm = new PaymentMethod();
	    pm.setAllowChange(false);
	    pm.setExternalPayment(true);
	    pm.setName("Mercado Pago");
	    pm.setPaymentType(PaymentType.MERCADOPAGO);
	    repo.save(pm);
	}

    }
    
    public void addAnotherPayment() {

	if (repo.findByName("Acepta cambio y es externo").size() == 0) {
	    PaymentMethod pm = new PaymentMethod();
	    pm.setAllowChange(true);
	    pm.setExternalPayment(true);
	    pm.setName("Acepta cambio y es externo");
	    pm.setPaymentType(PaymentType.ANOTHERPAYMENT);
	    repo.save(pm);
	}

    }
    
    public void addCash() {

	if (repo.findByName("Efectivo").size() == 0) {
	    PaymentMethod pm = new PaymentMethod();
	    pm.setAllowChange(true);
	    pm.setExternalPayment(false);
	    pm.setName("Efectivo");
	    pm.setPaymentType(PaymentType.CASH);
	    repo.save(pm);
	}

    }
}
