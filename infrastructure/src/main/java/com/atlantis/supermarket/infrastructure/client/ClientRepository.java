package com.atlantis.supermarket.infrastructure.client;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.client.Client;

public interface ClientRepository extends JpaRepository<Client, UUID>{

}

