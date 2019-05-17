package com.atlantis.supermarket.infrastructure.payment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.payment.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>{

}
