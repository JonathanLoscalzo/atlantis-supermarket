package com.atlantis.supermarket.core.shared;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.atlantis.supermarket.common.parser.JsonParser;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = 0")
public abstract class BaseEntity extends AbstractAggregateRoot<BaseEntity> implements Serializable {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private static final long serialVersionUID = 1L;

    private Boolean deleted;

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public Boolean getDeleted() {
	return deleted;
    }

    public BaseEntity setDeleted(Boolean deleted) {
	this.deleted = deleted;
	return this;
    }

    // standard getters and setters
    @Override
    public String toString() {
	return JsonParser.writeValue(this);
    }

    public BaseEntity() {
	this.id = UUID.randomUUID();
	this.deleted = false;
    }

    @Override
    public int hashCode() {
	return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof BaseEntity)) {
	    return false;
	}
	
	BaseEntity other = (BaseEntity) obj;
	return getId().equals(other.getId());
    }
}
