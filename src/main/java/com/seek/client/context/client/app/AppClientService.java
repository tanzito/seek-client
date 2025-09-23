package com.seek.client.context.client.app;

import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.ClientPublisher;
import com.seek.client.context.client.domain.ClientRepository;
import com.seek.client.context.client.domain.ClientService;
import com.seek.client.context.client.domain.dto.ClientDto;
import com.seek.client.context.client.domain.dto.PageResponse;
import com.seek.client.context.client.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AppClientService implements ClientService {

    @Value("${client.fixedLifeExpectancy}")
    private Integer fixedLifeExpectancy;

    private final ClientRepository clientRepository;
    private final ClientPublisher clientPublisher;

    public AppClientService(ClientRepository clientRepository, ClientPublisher clientPublisher) {
        this.clientRepository = clientRepository;
        this.clientPublisher = clientPublisher;
    }


    @Override
    public ClientDto save(Client client) {
        log.info("save client {}", client);
        Client clientDb = this.clientRepository.save(client);
        this.clientPublisher.send(clientDb);
        return new ClientDto(clientDb, this.fixedLifeExpectancy);

    }

    @Override
    public PageResponse<ClientDto> findAll(Pageable pageable) {
        log.info("findAll pageable {}", pageable);
        Page<Client> clientPage = this.clientRepository.findAll(pageable);
        return new PageResponse<>(clientPage.map(p -> new ClientDto(p, this.fixedLifeExpectancy)));
    }

    @Override
    public ClientDto finById(Integer id) {
        log.info("finById {}", id);
        Optional<Client> byId = this.clientRepository.getById(id);
        if (byId.isEmpty()) {
            log.error("finById error, id not found {}", id);
            throw new NotFoundException("Client with id " + id + " not found");
        }
        return new ClientDto(byId.get(), this.fixedLifeExpectancy);
    }
}
