package com.atlantis.supermarket.core.product.search;

import org.springframework.data.domain.Pageable;

public class SearchProduct {
    
    public String name;
    
    public Long minPrice; 

    public Long maxPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }
    
}
