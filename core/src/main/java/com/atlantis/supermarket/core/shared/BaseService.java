package com.atlantis.supermarket.core.shared;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<Entity extends BaseEntity, Identity> {
    Entity save(Entity entity);

    Boolean exist(Identity id);

    Entity retrieve(Identity identifier);

    void delete(Identity identifier);
    
    void delete(String identifier);

    Entity retrieve(UUID identifier, Class<?> klass);

    Collection<Entity> find();

    Page<Entity> find(Pageable pageable);
}