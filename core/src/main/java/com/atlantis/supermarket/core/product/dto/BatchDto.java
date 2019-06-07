package com.atlantis.supermarket.core.product.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class BatchDto extends BaseEntityDto {

    private Date expiration;

    private Date entry;

    private String detail;

    private Double remainingUnits;

    private ProductDto product;

    public Date getExpiration() {
        return expiration;
    }

    public Date getEntry() {
        return entry;
    }

    public String getDetail() {
        return detail;
    }

    public Double getRemainingUnits() {
        return remainingUnits;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setRemainingUnits(Double remainingUnits) {
        this.remainingUnits = remainingUnits;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
