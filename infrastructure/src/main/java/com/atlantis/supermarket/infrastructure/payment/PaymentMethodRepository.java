package com.atlantis.supermarket.infrastructure.payment;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.payment.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>{
    public List<PaymentMethod> findByName(String name);
}
