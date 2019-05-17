package com.atlantis.supermarket.core.shared;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityAuditable extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /*@Column(name = "created_by")
    @CreatedBy
    public String createdBy = null;
 
    @Column(name = "modified_by")
    @LastModifiedBy
    public String modifiedBy = null;
    */
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    public Long createdDate = (new Date()).getTime();
 
    @Column(name = "modified_date")
    @LastModifiedDate
    public Long modifiedDate = null;
}
