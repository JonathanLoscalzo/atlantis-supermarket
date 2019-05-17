package com.atlantis.supermarket.core.sale.factory;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.sale.ProductConsumed;
import com.atlantis.supermarket.core.sale.SaleItem;

@Component
public class SaleItemFactory {

    public SaleItem createSaleItem(Product product, Integer row, Collection<Pair<Batch, Double>> consumed_batch) {
	SaleItem s = new SaleItem();
	s.setProduct(product);
	s.setPricePerUnit(product.getRetailPrice());
	s.setProviderPrice(product.getProviderPrice());
	s.setRow(row);
	Collection<ProductConsumed> consumed;

	consumed = consumed_batch.stream().map(x -> {
	    ProductConsumed p = new ProductConsumed();
	    p.setExpiration(x.getFirst().getExpiration());
	    p.setBatch(x.getFirst());
	    p.setItem(s);
	    p.setQuantity(x.getSecond());
	    return p;
	}).collect(Collectors.toList());

	s.setConsumed(consumed);

	return s;
    }

}
