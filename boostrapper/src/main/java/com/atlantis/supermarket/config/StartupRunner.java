package com.atlantis.supermarket.config;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.exception.ProductNotSatisfiedStockException;
import com.atlantis.supermarket.core.product.factory.ProductFactory;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;


@Component
public class StartupRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);
    
    //@Autowired
    //private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ProductRepository products;

    @Autowired
    private ProviderRepository providers;
    
    @Autowired
    private ProductFactory factory;
    
    @Autowired
    public SeedUsers seedUsers;
    
    @Autowired
    private SeedMethodTypes types;
    
    @Autowired
    private SeedProviders provSeed;
    
    @Autowired
    private SeedCategories categories;
    
    @Override
    public void run(String... args) throws Exception {
	//whenProducHasStockCouldDiscount();
	seedUsers.addAdmin();
	types.addMercadoPago();
	types.addAnotherPayment();
	types.addCash();
	provSeed.set();
	categories.set();
    }
    
    public void whenProducHasStockCouldDiscount() {

	Date expiration = new Date();
	expiration.setYear(2020);
	Product p2 = factory.createPerishableProduct(
		"Coca Cola", "Gaseosa", "estoesuncódigo222",
		100.0, new BigDecimal(150.0), new BigDecimal(200.0), 1500.0,
		"esto es un detalle de un lote", "detalle producto", expiration);
	Provider provider = new Provider();
	provider.setEmail("loscalzo.jony@gmail.com");
	provider.setName("papito");
	
	providers.save(provider);
	
	p2.setProvider(provider);
	provider.getProducts().add(p2);
	
	products.save(p2);

	Batch b = new Batch()
		.setDetail("some-detail")
		.setProduct(p2)
		.setExpiration(expiration)
		.setEntry(new Date())
		.setRemainingUnits(1300.0);

	p2.addBatch(b);
	products.save(p2);

	try {
	    //p2.consume(2750);
	    //products.save(p2);
	} catch (ProductNotSatisfiedStockException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}