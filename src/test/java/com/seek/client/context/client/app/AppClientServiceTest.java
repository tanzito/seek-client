package com.seek.client.context.client.app;

import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.ClientPublisher;
import com.seek.client.context.client.domain.ClientRepository;
import com.seek.client.context.client.domain.dto.ClientDto;
import com.seek.client.context.client.domain.dto.PageResponse;
import com.seek.client.context.client.domain.exception.NotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientPublisher clientPublisher;

    @InjectMocks
    private AppClientService appClientService;

    private final Integer FIXED_LIFE_EXPECTANCY = 80;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appClientService = new AppClientService(clientRepository, clientPublisher);
        TestUtils.setField(appClientService, "fixedLifeExpectancy", FIXED_LIFE_EXPECTANCY);
    }

    @Test
    void testSave() {
        Client client = Client.builder()
                .name("pepe")
                .lastName("lopez")
                .birthdate(LocalDate.of(2000, 4, 5))
                .build();
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto result = appClientService.save(client);

        assertNotNull(result);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testFindAll() {
        Client client = Client.builder()
                .id(3)
                .name("neyer")
                .lastName("limon")
                .birthdate(LocalDate.of(1900, 5, 5))
                .build();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> page = new PageImpl<>(List.of(client));

        when(clientRepository.findAll(pageable)).thenReturn(page);

        PageResponse<ClientDto> response = appClientService.findAll(pageable);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
    }

    @Test
    void testFindById_found() {
        Client client = Client.builder()
                .id(1)
                .name("jonatan")
                .lastName("terrazas")
                .birthdate(LocalDate.of(2020, 5, 5))
                .build();
        when(clientRepository.getById(1)).thenReturn(Optional.of(client));
        ClientDto dto = appClientService.finById(1);
        assertNotNull(dto);
    }

    @Test
    void testFindById_notFound() {
        when(clientRepository.getById(99)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> appClientService.finById(99)
        );
        assertEquals("Client with id 99 not found", thrown.getMessage());
    }
}