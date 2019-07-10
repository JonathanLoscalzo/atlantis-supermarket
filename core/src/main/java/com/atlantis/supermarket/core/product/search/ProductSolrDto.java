package com.atlantis.supermarket.core.product.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.atlantis.supermarket.core.shared.search.SolrDto;

@SolrDocument(collection = "mycore")
public class ProductSolrDto extends SolrDto {
    
    @Field
    private String name;

    @Field
    private String brand;

    @Field
    private String description;

    @Field
    private Collection<String> categories = new ArrayList<>();
    
    @Field
    private Long retailPrice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<String> getCategories() {
	return categories;
    }

    public void setCategories(Collection<String> categories) {
	this.categories = categories;
    }

    public BigDecimal getRetailPrice() {
        return new BigDecimal(retailPrice);
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice.longValue();
    }
}
