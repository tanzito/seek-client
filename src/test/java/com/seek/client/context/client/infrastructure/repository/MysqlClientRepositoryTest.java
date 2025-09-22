package com.seek.client.context.client.infrastructure.repository;

import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.ClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MysqlClientRepositoryTest {

    @Mock
    private JpaClientRepository jpaClientRepository;

    private ClientRepository mysqlClientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mysqlClientRepository = new MysqlClientRepository(jpaClientRepository);
    }

    @Test
    void testSave() {
        Client client = new Client();
        when(jpaClientRepository.save(client)).thenReturn(client);

        Client saved = mysqlClientRepository.save(client);

        assertNotNull(saved);
        verify(jpaClientRepository, times(1)).save(client);
    }

    @Test
    void testGetById_found() {
        Client client = new Client();
        when(jpaClientRepository.findById(1)).thenReturn(Optional.of(client));

        Optional<Client> result = mysqlClientRepository.getById(1);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
        verify(jpaClientRepository, times(1)).findById(1);
    }

    @Test
    void testGetById_notFound() {
        when(jpaClientRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Client> result = mysqlClientRepository.getById(999);

        assertFalse(result.isPresent());
        verify(jpaClientRepository, times(1)).findById(999);
    }

    @Test
    void testFindAll() {
        Client client = new Client();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> clientPage = new PageImpl<>(List.of(client));

        when(jpaClientRepository.findAll(pageable)).thenReturn(clientPage);

        Page<Client> result = mysqlClientRepository.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(jpaClientRepository, times(1)).findAll(pageable);
    }
}