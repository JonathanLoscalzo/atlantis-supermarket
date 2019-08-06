package com.atlantis.supermarket.business.sale.handlers;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlantis.supermarket.core.external.EmailService;
import com.atlantis.supermarket.core.sale.Invoice;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.gateway.GeneratePayment;
import com.atlantis.supermarket.infrastructure.sale.InvoiceRepository;
import com.atlantis.supermarket.infrastructure.sale.SaleRepository;

@Service
public class GeneratePaymentHandler implements GeneratePayment {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public void to(String saleId) {

	Sale sale = saleRepository.findById(UUID.fromString(saleId)).orElse(null);

	if (sale != null) {
	    // GENERA UN INVOICE PARA VENTA ESTO SERÍA EL SERVICIO DE FACTURAS TIPO AFIP
	    Invoice invoice = new Invoice();
	    invoice.setSale(sale);
	    sale.setState(Sale.State.BILLED);
	    sale.setInvoice(invoice);
	    saleRepository.save(sale);

	    // ENVÍA FACTURA POR CORREO
	    emailService.sendText("atlantis@atlantis.com", sale.getClient().getUser().getEmail(), "Venta consolidada",
		    " Buenas tardes \n "
			    + "Se envía el número de factura para que compruebe según corresponda. "
			    + "Esto es una factura: " + invoice.getId().toString() + "\n"
			    + "La venta es esta: " + sale.getId().toString() +"\n"
			    + "Items: \n" + String.join("\n", sale.getItems().stream().map(x -> "· " + x.toString())
				    .collect(Collectors.toList())));
	}
    }

}
