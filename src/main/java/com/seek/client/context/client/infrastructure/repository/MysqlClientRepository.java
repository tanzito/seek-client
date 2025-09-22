package com.seek.client.context.client.infrastructure.repository;

import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MysqlClientRepository implements ClientRepository {
    private final JpaClientRepository jpaClientRepository;

    public MysqlClientRepository(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client save(Client client) {
        return this.jpaClientRepository.save(client);
    }

    @Override
    public Optional<Client> getById(Integer id) {
       return this.jpaClientRepository.findById(id);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
       return this.jpaClientRepository.findAll(pageable);
    }
}
