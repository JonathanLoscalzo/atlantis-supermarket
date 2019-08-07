package com.atlantis.supermarket.infrastructure.sale;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.client.mapper.ClientMapper;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.sale.Payment;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.SaleItem;
import com.atlantis.supermarket.core.sale.dto.PaymentDto;
import com.atlantis.supermarket.core.sale.dto.SaleDto;
import com.atlantis.supermarket.core.sale.dto.SaleItemDto;
import com.atlantis.supermarket.core.sale.mapper.SaleMapper;

@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public SaleDto toDto(Sale x) {
	SaleDto dto = new SaleDto();

	dto.setClient(clientMapper.toDto(x.getClient()));
	dto.setId(x.getId().toString());
	dto.setInvoice(x.getInvoice());
	dto.setItems(x.getItems().stream()
		.map(this::toDto).collect(Collectors.toList()));
	dto.setPaymentMethods(x.getPaymentMethods()
		.stream()
		.map(this::toDto)
		.collect(Collectors.toList()));
	dto.setState(x.getState());
	dto.setCreatedAt(new Date(x.createdDate));

	return dto;
    }

    @Override
    public SaleItemDto toDto(SaleItem x) {
	SaleItemDto dto = new SaleItemDto();

	dto.setLine(x.getRow());
	dto.setId(x.getId().toString());
	dto.setPricePerUnit(x.getPricePerUnit());
	dto.setProduct(productMapper.toDto(x.getProduct()));
	dto.setProviderPrice(x.getProviderPrice());
	dto.setUnits(x.getUnits());
	
	return dto;
    }

    @Override
    public PaymentDto toDto(Payment x) {
	PaymentDto dto = new PaymentDto();

	dto.setCanceled(x.getCanceled());
	dto.setExternalId(x.getExternalId());
	dto.setId(x.getId().toString());
	dto.setMethod(x.getMethod());
	dto.setPayment(x.getPayment());
	return dto;
    }

}
