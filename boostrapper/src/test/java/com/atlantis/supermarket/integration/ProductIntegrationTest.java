package com.atlantis.supermarket.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantis.supermarket.api.product.ProductController;
import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.BatchNotAvailableException;
import com.atlantis.supermarket.core.product.exception.BatchNotExistException;
import com.atlantis.supermarket.core.product.exception.ProductNotSatisfiedStockException;
import com.atlantis.supermarket.core.product.factory.ProductFactory;
import com.atlantis.supermarket.infrastructure.external.email.EmailAdapter;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest extends SupermarketIntegrationTests {

    @Mock
    private ProductController controller;
    @Mock
    private ProductService service;
    
    @MockBean
    SolrClient client;
    
    @Autowired
    private ProductRepository products;

    @Autowired
    private ProductFactory factory;

    @After
    public void tearDown() {
	//this.emailAdapter = Mockito.mock(EmailAdapter.class);
	products.deleteAll();
    }
    
    @MockBean
    private EmailAdapter emailAdapter;
    
    @Test
    public void whenProducHasStockCouldDiscount() {

	Date expiration = new Date();
	expiration.setYear(2020);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);

	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	assertThat(products.findById(p2.getId()).get().getCurrentUnits(), is(equalTo(2800.0)));

	try {
	    p2.consume(2750);
	    products.save(p2);
	} catch (ProductNotSatisfiedStockException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	assertThat(products.findById(p2.getId()).get().getCurrentUnits(), is(equalTo(2800.0 - 2750.0)));
    }

    @Test(expected = BatchNotExistException.class)
    public void whenBatchNotExistsThrowException() throws ProductNotSatisfiedStockException, BatchNotExistException, BatchNotAvailableException {

	Date expiration = new Date();
	expiration.setYear(2020);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote","detalle producto", expiration);

	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	UUID random = UUID.randomUUID();

	p2.consume(1000, random);
	products.save(p2);

	assertThat(products.findById(p2.getId()).get().getCurrentUnits(), is(equalTo(2800.0 - 1000.0)));
    }
    
    @Test(expected = BatchNotAvailableException.class)
    public void whenBatchExpiredThrowException() throws ProductNotSatisfiedStockException, BatchNotExistException, BatchNotAvailableException {

	Date expiration = new Date();
	expiration.setYear(expiration.getYear() - 1);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);

	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	p2.consume(1000, b.getId());
	products.save(p2);
    }
    
    @Test(expected = ProductNotSatisfiedStockException.class)
    public void whenBatchHaveNotStockThrowException() throws ProductNotSatisfiedStockException, BatchNotExistException, BatchNotAvailableException {

	Date expiration = new Date();
	expiration.setYear(expiration.getYear() + 1);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);

	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	p2.consume(1301, b.getId());
	products.save(p2);
    }
    
    @Test(expected = ProductNotSatisfiedStockException.class)
    public void whenProductHaveNotStockThrowException() throws ProductNotSatisfiedStockException, BatchNotExistException, BatchNotAvailableException {

	Date expiration = new Date();
	expiration.setYear(expiration.getYear() + 1);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);

	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProducto(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	p2.consume(2801);
	products.save(p2);
    }
}
