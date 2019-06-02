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

    private Provider provider;

    private Collection<Category> categories = new ArrayList<>();

}
