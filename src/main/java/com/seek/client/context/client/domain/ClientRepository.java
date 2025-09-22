package com.seek.client.context.client.domain;

import com.seek.client.context.client.domain.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> getById(Integer id);
    Page<Client> findAll(Pageable pageable);
}
