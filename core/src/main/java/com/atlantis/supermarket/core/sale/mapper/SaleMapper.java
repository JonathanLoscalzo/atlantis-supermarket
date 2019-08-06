package com.atlantis.supermarket.core.sale.mapper;

import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.SaleItem;
import com.atlantis.supermarket.core.sale.dto.PaymentDto;
import com.atlantis.supermarket.core.sale.dto.SaleDto;
import com.atlantis.supermarket.core.sale.dto.SaleItemDto;

public interface SaleMapper {
    public SaleDto toDto(Sale x);
    public SaleItemDto toDto(SaleItem x);
    public PaymentDto toDto(Payment x);
}
