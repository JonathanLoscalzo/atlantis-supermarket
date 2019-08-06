package com.atlantis.supermarket.infrastructure.sale;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.sale.Invoice;
import com.atlantis.supermarket.core.sale.Payment;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

}
