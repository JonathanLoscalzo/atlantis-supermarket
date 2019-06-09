package com.atlantis.supermarket.business.product.useCases.createProduct;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;

import com.atlantis.supermarket.core.product.Batch.BatchType;
import com.atlantis.supermarket.core.shared.business.InputPort;

public class CreateProductInput extends InputPort {
    
    private String id;
    private String sku;
    private String upc;
    private String name;
    private String brand;
    private Double minStock;
    private Double stock;
    private BigDecimal providerPrice;
    private BigDecimal retailPrice;
    private String description;
    private String batchDetails;
    private Date expiration;
    private String providerId;
    private Collection<String> categories;

    // TODO: unit type
    private BatchType type;

    public String getSku() {
	return sku;
    }

    public void setSku(String sku) {
	this.sku = sku;
    }

    public String getUpc() {
	return upc;
    }

    public void setUpc(String upc) {
	this.upc = upc;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBrand() {
	return brand;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public Double getStock() {
	return stock;
    }

    public void setStock(Double stock) {
	this.stock = stock;
    }

    public Double getMinStock() {
	return minStock;
    }

    public void setMinStock(Double minStock) {
	this.minStock = minStock;
    }

    public BigDecimal getProviderPrice() {
	return providerPrice;
    }

    public void setProviderPrice(BigDecimal providerPrice) {
	this.providerPrice = providerPrice;
    }

    public BigDecimal getRetailPrice() {
	return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
	this.retailPrice = retailPrice;
    }

    public BatchType getType() {
	return type;
    }

    public void setType(BatchType type) {
	this.type = type;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getBatchDetails() {
	return batchDetails;
    }

    public void setBatchDetails(String batchDetails) {
	this.batchDetails = batchDetails;
    }

    public Date getExpiration() {
	return expiration;
    }

    public void setExpiration(Date expiration) {
	this.expiration = expiration;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
    }

    @Override
    public boolean equals(Object arg0) {
	// TODO Auto-generated method stub
	return super.equals(arg0);
    }

    @Override
    protected void finalize() throws Throwable {
	// TODO Auto-generated method stub
	super.finalize();
    }

    @Override
    public int hashCode() {
	// TODO Auto-generated method stub
	return super.hashCode();
    }

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
    }

    public String getProviderId() {
	return providerId;
    }

    public void setProviderId(String providerId) {
	this.providerId = providerId;
    }

    public Collection<String> getCategories() {
	return categories;
    }

    public void setCategories(Collection<String> categories) {
	this.categories = categories;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
}
