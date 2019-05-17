package com.atlantis.supermarket.business.sale.useCases.generateSale;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.shared.business.OutputPort;

public class Output extends OutputPort {

    public Sale sale;

    public Output(Sale sale) {
	this.sale = sale;
    }

}
