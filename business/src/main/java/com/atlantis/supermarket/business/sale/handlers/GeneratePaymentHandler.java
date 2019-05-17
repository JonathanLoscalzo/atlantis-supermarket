package com.atlantis.supermarket.business.sale.handlers;

import java.util.concurrent.Delayed;

import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.sale.gateway.GeneratePayment;

@Service
public class GeneratePaymentHandler implements GeneratePayment {

    @Override
    public void to(String saleId) {
	System.out.print(saleId);

	// GENERA UN PAYMENT PARA ESTO
	
	// ENVÍA EL PDF POR CORREO
	
	
    }
    
}
