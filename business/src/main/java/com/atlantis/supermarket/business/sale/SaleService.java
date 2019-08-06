package com.atlantis.supermarket.business.sale;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.dto.SaleDto;
import com.atlantis.supermarket.core.sale.mapper.SaleMapper;
import com.atlantis.supermarket.infrastructure.sale.SaleRepository;

@Service
public class SaleService extends ServiceImpl<Sale> {

    SaleRepository saleRepo;

    SaleMapper saleMapper;

    protected SaleService(SaleRepository repo, SaleMapper saleMapper) {
	super(repo);
	saleRepo = repo;
	this.saleMapper = saleMapper;
    }

    public Page<SaleDto> findMySales(Client client, Pageable pageable) {
	return this.saleRepo
		.findByClient(client, pageable)
		.map(x -> saleMapper.toDto(x));
    }

}
