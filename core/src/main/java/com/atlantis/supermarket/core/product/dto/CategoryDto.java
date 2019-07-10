package com.atlantis.supermarket.core.product.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class CategoryDto extends BaseEntityDto {

    private String description;

    public CategoryDto() {
	
    }
    
    public CategoryDto(String description) {
	this.description = description;
    }
    
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
