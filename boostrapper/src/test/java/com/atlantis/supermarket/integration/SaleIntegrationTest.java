package com.atlantis.supermarket.integration;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantis.supermarket.business.sale.exceptions.ExternalPaymentFailure;
import com.atlantis.supermarket.business.sale.useCases.generateSale.Input;
import com.atlantis.supermarket.business.sale.useCases.generateSale.Output;
import com.atlantis.supermarket.business.sale.useCases.generateSale.PaymentInput;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.payment.PaymentType;
import com.atlantis.supermarket.core.payment.exception.PaymentMethodNotFound;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.factory.ProductFactory;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.exceptions.ExternalPaymentException;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.infrastructure.client.ClientRepository;
import com.atlantis.supermarket.infrastructure.external.email.EmailAdapter;
import com.atlantis.supermarket.infrastructure.external.message.WhatsappAdapter;
import com.atlantis.supermarket.infrastructure.external.payment.MercadoPagoStrategy;
import com.atlantis.supermarket.infrastructure.payment.PaymentMethodRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;
import com.atlantis.supermarket.infrastructure.sale.PaymentRepository;
import com.atlantis.supermarket.infrastructure.sale.SaleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SaleIntegrationTest extends SupermarketIntegrationTests {

    @Autowired
    private SaveUser userRepository;
    
    @Autowired
    private SaleRepository sales;

    @Autowired
    private PaymentMethodRepository payments;

    @Autowired
    private ProductRepository products;

    @Autowired
    private ClientRepository clients;

    @Autowired
    private PaymentRepository paymentRepo;

    @MockBean
    private EmailAdapter emailAdapter;

    @MockBean
    private WhatsappAdapter messageAdapter;
    
    @Autowired
    private ProductFactory factory;
    
    @MockBean
    private MercadoPagoStrategy mps;

    @Autowired
    private com.atlantis.supermarket.business.sale.useCases.generateSale.GenerateSale generateSale;

    private Map<String, UUID> uuids = new HashMap<>();
    
    @After
    public void tearDown() {
	paymentRepo.deleteAll();
	clients.deleteAll();
	products.deleteAll();
	payments.deleteAll();
	sales.deleteAll();
    }

    @Before
    public void setup() {
	// this.emailAdapter = Mockito.mock(EmailAdapter.class);
	User user = new User();
	user.setUsername("pepo2");
	user.setPassword("asdfgasdf");
	userRepository.save(user);
	
	uuids.putIfAbsent("userId", user.getId());
	
	Client c = new Client();
	c.setDocument(12345);
	c.setName("name");
	c.setSurname("surname");
	c.setUser(user);
	
	clients.save(c);
	uuids.putIfAbsent("clientId", c.getId());
	
	PaymentMethod pm = new PaymentMethod();
	pm.setAllowChange(false);
	pm.setExternalPayment(true);
	pm.setPaymentType(PaymentType.MERCADOPAGO);
	pm.setName("Mercado pago strategy");
	payments.save(pm);
	
	uuids.putIfAbsent("mercadopago", pm.getId());
	
	pm = new PaymentMethod();
	pm.setAllowChange(false);
	pm.setExternalPayment(true);
	pm.setPaymentType(PaymentType.ANOTHERPAYMENT);
	pm.setName("anotHer pago strategy");
	payments.save(pm);
	
	uuids.putIfAbsent("anotherpago", pm.getId());
	
	pm = new PaymentMethod();
	pm.setAllowChange(true);
	pm.setExternalPayment(false);
	pm.setPaymentType(PaymentType.CASH);
	pm.setName("efectivo");
	payments.save(pm);
	
	uuids.putIfAbsent("efectivo", pm.getId());
	
	Date expiration = new Date();
	expiration.setYear(2020);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);
	
	uuids.putIfAbsent("producto1", p2.getId());
	
	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	uuids.putIfAbsent("batch1", b.getId());
	
	Date anotherExp = new Date();
	anotherExp.setMonth(expiration.getMonth() + 2);
	
	b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(anotherExp)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);
	p2.addBatch(b);
	uuids.putIfAbsent("batch2", b.getId());
	
	products.save(p2);
	
	p2 = factory.createPerishableProduct(
		"pepsi", "cola", "estoesuncódigo333",
		100.0, new BigDecimal(200.0), new BigDecimal(250.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);
	
	uuids.putIfAbsent("producto2", p2.getId());
	products.save(p2);
    }
    
    @Test
    public void whencreateSaleWasPersisted() {
	
	Input input = new Input();
	input.clientId = uuids.get("clientId").toString();
	input.payments = new ArrayList<>();
	
	PaymentInput pi = new PaymentInput();
	pi.pay = new BigDecimal(5*150);
	pi.paymentId = uuids.get("mercadopago").toString();
	input.payments.add(pi);
	
	input.consume = new HashMap<>();
	input.consume.put(uuids.get("producto1").toString(), new Double(5.0));
	
	Output output = generateSale.handle(input);
	
	Sale s = sales.findById(output.sale.getId()).orElseGet(null);
	
	assertThat(s, is(notNullValue()));
	
	Product p = products.findById(uuids.get("producto1")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(4100.0 - 5)));
    }
    
    @Test
    public void whencreateSalewithManyProductsWasPersisted() {
	
	Input input = new Input();
	input.clientId = uuids.get("clientId").toString();
	input.payments = new ArrayList<>();
	
	PaymentInput pi = new PaymentInput();
	pi.pay = new BigDecimal(5*150+5*200);
	pi.paymentId = uuids.get("mercadopago").toString();
	input.payments.add(pi);
	
	input.consume = new HashMap<>();
	input.consume.put(uuids.get("producto1").toString(), new Double(5.0));
	input.consume.put(uuids.get("producto2").toString(), new Double(5.0));
	
	Output output = generateSale.handle(input);
	
	Sale s = sales.findById(output.sale.getId()).orElseGet(null);
	
	assertThat(s, is(notNullValue()));
	
	Product p = products.findById(uuids.get("producto1")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(4100.0 - 5)));
	
	p = products.findById(uuids.get("producto2")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(1500.0 - 5.0)));
    }
    
    @SuppressWarnings("deprecation")
    @Test(expected = ExternalPaymentFailure.class)
    public void whencreateSaleWithExternalThenThrowException() {
	
	when(mps.pay(Matchers.<Map<String,String>>any())).thenThrow(ExternalPaymentException.class);
	
	Input input = new Input();
	input.clientId = uuids.get("clientId").toString();
	input.payments = new ArrayList<>();
	
	PaymentInput pi = new PaymentInput();
	pi.pay = new BigDecimal(5*150+5*200);
	pi.paymentId = uuids.get("mercadopago").toString();
	input.payments.add(pi);
	
	input.consume = new HashMap<>();
	input.consume.put(uuids.get("producto1").toString(), new Double(5.0));
	input.consume.put(uuids.get("producto2").toString(), new Double(5.0));
	
	Output output = generateSale.handle(input);
	
	Sale s = sales.findById(output.sale.getId()).orElseGet(null);
	
	assertThat(s, is(notNullValue()));
	
	Product p = products.findById(uuids.get("producto1")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(4100.0 - 5)));
	
	p = products.findById(uuids.get("producto2")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(1500.0 - 5.0)));
    }
    
    @Test(expected = PaymentMethodNotFound.class)
    public void whencreateSaleThenPaymentNotFound() {
	Input input = new Input();
	input.clientId = uuids.get("clientId").toString();
	input.payments = new ArrayList<>();
	
	PaymentInput pi = new PaymentInput();
	pi.pay = new BigDecimal(5*150+5*200);
	pi.paymentId = UUID.randomUUID().toString();
	input.payments.add(pi);
	
	input.consume = new HashMap<>();
	input.consume.put(uuids.get("producto1").toString(), new Double(5.0));
	input.consume.put(uuids.get("producto2").toString(), new Double(5.0));
	
	Output output = generateSale.handle(input);
	
	Sale s = sales.findById(output.sale.getId()).orElseGet(null);
	
	assertThat(s, is(notNullValue()));
	
	Product p = products.findById(uuids.get("producto1")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(4100.0 - 5)));
	
	p = products.findById(uuids.get("producto2")).orElse(null);
	assertThat(p.getCurrentUnits(), is(equalTo(1500.0 - 5.0)));
    }
}
