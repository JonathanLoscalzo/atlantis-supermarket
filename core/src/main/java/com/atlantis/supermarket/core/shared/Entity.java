package com.atlantis.supermarket.core.shared;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Entity extends AbstractAggregateRoot<BaseEntity>{
    @Id
    @Column(columnDefinition = "BINARY(16)")
    public UUID id;

    private static final long serialVersionUID = 1L;

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }
}
