package com.atlantis.supermarket.business.sale.useCases.generateSale;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.sale.exceptions.ConsumeProductFailure;
import com.atlantis.supermarket.business.sale.exceptions.ExternalPaymentFailure;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.exceptions.ClientNotExists;
import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.payment.exception.PaymentMethodNotFound;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.BatchNotAvailableException;
import com.atlantis.supermarket.core.product.exception.BatchNotExistException;
import com.atlantis.supermarket.core.product.exception.ProductNotSatisfiedStockException;
import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.SaleItem;
import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;
import com.atlantis.supermarket.core.sale.factory.PaymentFactory;
import com.atlantis.supermarket.core.sale.factory.SaleFactory;
import com.atlantis.supermarket.core.sale.factory.SaleItemFactory;
import com.atlantis.supermarket.core.shared.business.UseCaseOutput;
import com.atlantis.supermarket.infrastructure.client.ClientRepository;
import com.atlantis.supermarket.infrastructure.external.payment.PaymentResolutor;
import com.atlantis.supermarket.infrastructure.payment.PaymentMethodRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;
import com.atlantis.supermarket.infrastructure.sale.PaymentRepository;
import com.atlantis.supermarket.infrastructure.sale.SaleRepository;

@Service
public class GenerateSale implements UseCaseOutput<Input, Output> {

    @Autowired
    private SaleFactory factory;

    @Autowired
    private PaymentFactory paymentFactory;

    @Autowired
    private SaleItemFactory itemFactory;

    @Autowired
    private PaymentResolutor resolutor;

    @Autowired
    private SaleRepository sales;

    @Autowired
    private PaymentMethodRepository payments;

    @Autowired
    private ProductRepository productsRepo;

    @Autowired
    private ClientRepository clients;

    @Autowired
    private PaymentRepository paymentRepo;

    @Override
    @Transactional
    public Output handle(Input input) {
	Client client = clients
		.findById(UUID.fromString(input.clientId))
		.orElseThrow(() -> new ClientNotExists(UUID.fromString(input.clientId)));

	// could consume

	Collection<Payment> paymentMethods = generatePayments(input);
	List<SaleItem> items = null;

	try {
	    items = consumeProducts(input);
	} catch (ConsumeProductFailure e) {
	    cancelPayments(paymentMethods);
	    throw e;
	}

	Sale sale = factory.createSale(client, items, paymentMethods);

	sales.save(sale);

	return new Output(sale);
    }

    private List<SaleItem> consumeProducts(Input input) {
	Collection<UUID> productsId = input.consume
		.keySet()
		.stream()
		.map(x -> UUID.fromString(x))
		.collect(Collectors.toList());

	Collection<Product> products = productsRepo.findAllById(productsId);

	if (products.size() != productsId.size()) {
	    // throw some exception
	}

	List<Pair<Product, List<Pair<Batch, Double>>>> batches = new ArrayList<>();
	List<SaleItem> items = new ArrayList<>();

	int row = 0;

	try {
	    for (Product p : products) {
		// TODO: SI FALLA CONSUME IGUAL? NO DEBERÍA. VER COMO ROLLBACKEAR ESTE CAMBIO
		Double quantity = input.consume.get(p.getId().toString());
		batches.add(Pair.of(p, p.consume(quantity)));
	    }
	} catch (ProductNotSatisfiedStockException e) {
	    cancelProducts(products, e);
	} catch (BatchNotExistException e) {
	    cancelProducts(products, e);
	} catch (BatchNotAvailableException e) {
	    cancelProducts(products, e);
	} catch (Exception e) {
	    // TODO: ACA TAMBIEN, PERO ACA ES UN ERROR FEO YA
	    cancelProducts(products, e);
	}

	for (Pair<Product, List<Pair<Batch, Double>>> b : batches) {
	    items.add(itemFactory.createSaleItem(b.getFirst(), row++, b.getSecond()));
	}

	return items;
    }

    // podría estar en un servicio externo para deployarse separado
    private Collection<Payment> generatePayments(Input input) {
	Collection<PaymentMethod> methods = payments
		.findAllById(input.payments
			.stream()
			.map(x -> UUID.fromString(x.paymentId))
			.collect(Collectors.toList()));

	if (methods.size() != input.payments.size() || methods.size() == 0) {
	    throw new PaymentMethodNotFound(methods,
		    input.payments.stream().map(x -> x.paymentId).collect(Collectors.toList()));
	}

	Collection<Payment> paymentMethods = new ArrayList<>();

	int items = 0;

	try {
	    for (PaymentMethod m : methods) {
		if (m.getExternalPayment()) {
		    PaymentInput pay = input.payments
			    .stream()
			    .filter(x -> UUID.fromString(x.paymentId).equals(m.getId())).findFirst()
			    .orElseThrow(() -> new RuntimeException());

		    Map<String, String> result = resolutor.getPaymentStrategy(m.getPaymentType()).pay(pay.properties);
		    paymentMethods.add(paymentFactory.createPayment(m, pay.pay, result.get("id")));
		    items++;
		}
	    }
	    paymentMethods.forEach(x -> x.setCanceled(false));
	} catch (ExternalPaymentException e) {
	    cancelPayments(paymentMethods);
	    throw new ExternalPaymentFailure(e.getPayment(), paymentMethods, e);
	}

	return paymentMethods;
    }

    private void cancelPayments(Collection<Payment> paymentMethods) {
	for (Payment m : paymentMethods) {
	    m.setCanceled(true);
	    resolutor.getPaymentStrategy(m.getMethod().getPaymentType()).cancel(m.getExternalId());
	}

	paymentRepo.saveAll(paymentMethods);
    }

    private void cancelProducts(Collection<Product> products, Exception e) {
	products.forEach(productsRepo::detach);
	throw new ConsumeProductFailure("", e);
    }

}
