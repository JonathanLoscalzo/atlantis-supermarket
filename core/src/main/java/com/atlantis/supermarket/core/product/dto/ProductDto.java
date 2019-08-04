package com.atlantis.supermarket.core.product.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.Batch.BatchType;
import com.atlantis.supermarket.core.shared.BaseEntityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto extends BaseEntityDto {

    private String sku;

    private String upc;

    private String name;

    private String brand;

    private Double minStock;

    private BigDecimal providerPrice;

    private BigDecimal retailPrice;

    private String description;

    private BatchType type = BatchType.DEFAULT;

    private Collection<BatchDto> batches;

    private ProviderDto provider;

    private Collection<CategoryDto> categories = new ArrayList<>();

    private boolean purchable;

    private double units;

    public String getSku() {
	return sku;
    }

    public String getUpc() {
	return upc;
    }

    public String getName() {
	return name;
    }

    public String getBrand() {
	return brand;
    }

    public Double getMinStock() {
	return minStock;
    }

    public BigDecimal getProviderPrice() {
	return providerPrice;
    }

    public BigDecimal getRetailPrice() {
	return retailPrice;
    }

    public String getDescription() {
	return description;
    }

    public BatchType getType() {
	return type;
    }

    public Collection<BatchDto> getBatches() {
	return batches;
    }

    public ProviderDto getProvider() {
	return provider;
    }

    public Collection<CategoryDto> getCategories() {
	return categories;
    }
    
    public boolean getIsPurchable() {
	return this.purchable;
    }
    
    public double getUnits() {
	return this.units;
    }

    public void setSku(String sku) {
	this.sku = sku;
    }

    public void setUpc(String upc) {
	this.upc = upc;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public void setMinStock(Double minStock) {
	this.minStock = minStock;
    }

    public void setProviderPrice(BigDecimal providerPrice) {
	this.providerPrice = providerPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
	this.retailPrice = retailPrice;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setType(BatchType type) {
	this.type = type;
    }

    public void setBatches(Collection<BatchDto> batches) {
	this.batches = batches;
    }

    public void setProvider(ProviderDto provider) {
	this.provider = provider;
    }

    public void setCategories(Collection<CategoryDto> categories) {
	this.categories = categories;
    }

    public void setIsPurchable(boolean purchable) {
	this.purchable = purchable;
    }

    public void setUnits(double currentUnits) {
	this.units = currentUnits;
	
    }

}
