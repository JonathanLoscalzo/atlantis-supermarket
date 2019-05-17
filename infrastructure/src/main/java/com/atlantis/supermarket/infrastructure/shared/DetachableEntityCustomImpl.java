package com.atlantis.supermarket.infrastructure.shared;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.atlantis.supermarket.core.shared.BaseEntity;

@Repository
public class DetachableEntityCustomImpl implements  DetachableEntityCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(BaseEntity u) {
        entityManager.detach(u);
    }

}