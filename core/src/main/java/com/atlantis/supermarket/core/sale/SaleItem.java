package com.atlantis.supermarket.core.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.BaseEntity;

@Entity
@Table(name = "sale_item")
@Access(AccessType.FIELD)
public class SaleItem extends BaseEntity {

    @Column(nullable = false)
    private Integer line;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private BigDecimal pricePerUnit;
    
    @Column(nullable = false)
    private BigDecimal providerPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Collection<ProductConsumed> consumed = new ArrayList<>();

    public Double getUnits() {
	return this.consumed.stream().collect(Collectors.summingDouble(ProductConsumed::getQuantity));
    }

    public BigDecimal totalPrice() {
	return this.getPricePerUnit().multiply(new BigDecimal(this.getUnits()));
    }

    public Sale getSale() {
	return sale;
    }

    public void setSale(Sale sale) {
	this.sale = sale;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public BigDecimal getPricePerUnit() {
	return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
    }

    public Collection<ProductConsumed> getConsumed() {
	return consumed;
    }

    public void setConsumed(Collection<ProductConsumed> consumed) {
	this.consumed = consumed;
    }
    
    public Integer getRow() {
        return line;
    }

    public void setRow(Integer row) {
        this.line = row;
    }

    public BigDecimal getProviderPrice() {
        return providerPrice;
    }

    public void setProviderPrice(BigDecimal providerPrice) {
        this.providerPrice = providerPrice;
    }
}
