package com.atlantis.supermarket.business.product.useCases.addBatch;

import java.util.Date;
import java.util.UUID;

import com.atlantis.supermarket.core.shared.business.InputPort;

public class AddBatchInput extends InputPort {

    public UUID productId;
    public String detail;
    public Date expiration;
    public Double remainingUnits;

}
