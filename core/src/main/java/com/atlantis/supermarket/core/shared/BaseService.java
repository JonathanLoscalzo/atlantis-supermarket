package com.atlantis.supermarket.core.shared;

import java.util.List;

public interface BaseService<Entity extends BaseEntity, Identity> {
    Entity save(Entity entity);

    Boolean exist(Identity id);

    Entity retrieve(Identity identifier);

    void delete(Identity identifier);
}