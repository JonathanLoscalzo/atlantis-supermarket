package com.atlantis.supermarket.business.product.useCases.supplyBatch;

import java.util.UUID;

import com.atlantis.supermarket.core.shared.business.InputPort;

public class SupplyBatchInput extends InputPort {
    public UUID batchId;
    public double quantity;
}
