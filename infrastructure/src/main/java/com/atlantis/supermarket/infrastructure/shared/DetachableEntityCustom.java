package com.atlantis.supermarket.infrastructure.shared;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.shared.BaseEntity;

public interface DetachableEntityCustom {
    void detach(BaseEntity entity);
}
