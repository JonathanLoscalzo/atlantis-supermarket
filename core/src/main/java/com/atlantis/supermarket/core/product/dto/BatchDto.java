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

    private Product product;
}
