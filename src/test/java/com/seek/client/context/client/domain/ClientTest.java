package com.seek.client.context.client.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testGetAge_onBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate birthdate = today.minusYears(30);

        Client client = Client.builder()
                .birthdate(birthdate)
                .build();

        assertEquals(30, client.getAge());
    }

    @Test
    void testGetAge_beforeBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate birthdate = today.minusYears(30).plusDays(1);

        Client client = Client.builder()
                .birthdate(birthdate)
                .build();

        assertEquals(29, client.getAge());
    }

    @Test
    void testGetAge_afterBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate birthdate = today.minusYears(30).minusDays(1);

        Client client = Client.builder()
                .birthdate(birthdate)
                .build();

        assertEquals(30, client.getAge());
    }

    @Test
    void testGetAge_futureBirthdate() {
        LocalDate futureDate = LocalDate.now().plusYears(1);
        Client client = Client.builder()
                .birthdate(futureDate)
                .build();

        int age = client.getAge();
        assertTrue(age < 0);
    }

    @Test
    void testGetAge_nullBirthdate() {
        Client client = Client.builder()
                .birthdate(null)
                .build();

        assertThrows(NullPointerException.class, client::getAge);
    }
}