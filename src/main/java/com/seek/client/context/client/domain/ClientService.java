package com.seek.client.context.client.domain;

import com.seek.client.context.client.domain.dto.ClientDto;
import com.seek.client.context.client.domain.dto.ClientSaveDto;
import com.seek.client.context.client.domain.dto.PageResponse;
import org.springframework.data.domain.Pageable;


public interface ClientService {
    ClientDto save(Client client);
    PageResponse<ClientDto> findAll(Pageable pageable);
    ClientDto finById(Integer id);
}
