package com.atlantis.supermarket.core.product.dto;

import com.atlantis.supermarket.core.shared.BaseEntityDto;

public class ProviderDto extends BaseEntityDto {
    private String name;
    private String email;
    private String phone;
   
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }
}
