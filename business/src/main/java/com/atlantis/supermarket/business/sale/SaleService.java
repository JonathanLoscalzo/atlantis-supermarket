package com.atlantis.supermarket.business.sale;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.infrastructure.sale.SaleRepository;

public class SaleService extends ServiceImpl<Sale>{

    protected SaleService(SaleRepository repo) {
	super(repo);
    }

}
