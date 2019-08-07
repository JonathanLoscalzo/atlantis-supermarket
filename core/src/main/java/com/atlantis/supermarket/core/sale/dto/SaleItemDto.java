package com.atlantis.supermarket.core.sale.dto;

import java.math.BigDecimal;

import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class SaleItemDto extends BaseEntityDto {

    private Integer line;
    
    private ProductDto product;

    private BigDecimal pricePerUnit;

    private BigDecimal providerPrice;
    
    private Double units;

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getProviderPrice() {
        return providerPrice;
    }

    public void setProviderPrice(BigDecimal providerPrice) {
        this.providerPrice = providerPrice;
    }

    public void setUnits(Double units) {
	this.units = units;
    }
    
    public Double getUnits() {
	return this.units;
    }
    
    public BigDecimal getTotal() {
	return BigDecimal.valueOf(this.units).multiply(this.getPricePerUnit());
    }
}
