package com.atlantis.supermarket.core.product;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.atlantis.supermarket.core.shared.BaseEntity;

//@Entity
//@Table(name = "price")
//@Access(AccessType.FIELD)
public class Price {//extends BaseEntity {

    //@OneToMany
    private Product product;

    //@Column
    private BigDecimal price;

    //@Column
    private Boolean actual;

}
