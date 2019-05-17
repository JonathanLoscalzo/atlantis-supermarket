package com.atlantis.supermarket.core.product.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Batch.BatchType;

@Component
public class ProductFactory {

    /**
     * default create a product with one batch
     * 
     * @param brand
     * @param name
     * @param upc
     * @param minStock
     * @param retailPrice
     * @param supplierPrice
     * @param stock
     * @return
     */
    public Product createDefaultProduct(String brand, String name, String upc, Double minStock,
	    BigDecimal retailPrice, BigDecimal supplierPrice, Double stock, String batchDetails, String description) {

	Product p = new Product()
		.setBrand(brand)
		.setName(name)
		.setUpc(upc)
		.setBatchType(BatchType.DEFAULT)
		.setMinStock(minStock)
		.setRetailPrice(retailPrice)
		.setProviderPrice(supplierPrice)
		.setDescription(description);

	Batch b = createBatch(p, stock, batchDetails, null);
	p.addBatch(b);

	// TODO: sku estrategy generation
	p.setSku(p.getId().toString());
	
	return p;
    }

    /**
     * create a "producto con fecha de vencimiento: perecedero"
     * 
     * @param brand
     * @param name
     * @param upc
     * @param minStock
     * @param retailPrice
     * @param supplierPrice
     * @param stock
     * @param details
     * @param expiration
     * @return
     */
    public Product createPerishableProduct(String brand, String name, String upc, Double minStock,
	    BigDecimal retailPrice, BigDecimal supplierPrice, Double stock, String batchdetails, String description, Date expiration) {

	Product p = new Product()
		.setBrand(brand)
		.setName(name)
		.setUpc(upc)
		.setBatchType(BatchType.EXPIRATION)
		.setMinStock(minStock)
		.setRetailPrice(retailPrice)
		.setProviderPrice(supplierPrice)
		.setDescription(description);

	Batch b = createBatch(p, stock, batchdetails, expiration);
	p.addBatch(b);

	// TODO: sku estrategy generation
	p.setSku(p.getId().toString());

	return p;
    }

    private static Batch createBatch(Product p, Double stock, String details, Date expiration) {
	return new Batch().setProducto(p).setEntry(new Date()).setRemainingUnits(stock).setDetail(details).setExpiration(expiration);
    }
}
