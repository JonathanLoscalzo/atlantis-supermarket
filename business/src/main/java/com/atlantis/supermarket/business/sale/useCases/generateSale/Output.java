package com.atlantis.supermarket.business.sale.useCases.generateSale;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.dto.SaleDto;
import com.atlantis.supermarket.core.shared.business.OutputPort;

public class Output extends OutputPort {

    public SaleDto sale;

    public Output(SaleDto sale) {
	this.sale = sale;
    }

}
