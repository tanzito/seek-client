package com.seek.client.context.client.infrastructure.repository;

import com.seek.client.context.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<Client, Integer> {
}
