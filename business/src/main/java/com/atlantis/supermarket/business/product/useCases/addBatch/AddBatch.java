package com.atlantis.supermarket.business.product.useCases.addBatch;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.shared.business.UseCaseInput;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class AddBatch implements UseCaseInput<AddBatchInput> {

    @Autowired
    private ProductRepository products;

    @Override
    public void handle(AddBatchInput input) {
	Product p = products.findById(input.productId).orElseThrow(() -> new ProductNotFoundException(input.productId));
	Calendar calendar = Calendar.getInstance();

	// Set the time and date information and display it.
	calendar.setTimeZone(TimeZone.getTimeZone("GMT-03"));
	/*calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	calendar.set(Calendar.HOUR_OF_DAY,0);*/
	Date date = calendar.getTime();

	Batch b = new Batch()
		.setDetail(input.detail)
		.setEntry(date)
		.setExpiration(input.expiration)
		.setRemainingUnits(input.remainingUnits)
		.setProduct(p);

	p.addBatch(b);

	products.save(p);

    }

}
