package com.seek.client.context.security.infrastructure;

import com.seek.client.context.security.domain.UserEntity;
import com.seek.client.context.security.domain.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final List<UserEntity> users = List.of(
            UserEntity.builder()
                    .id(1L)
                    .role("ADMIN")
                    .password("$2a$10$TRWcUOvyAxlHyXBJ9WIrN.rBwFnLgEuqNYujH10xYz1Ugeli6pvPi")
                    .username("admin")
                    .build(),
            UserEntity.builder()
                    .id(2L)
                    .role("USER")
                    .password("$2a$10$4z21VfhO4U/0jmA37OVHyepaH1BB.9SNn/7HA8wyzoFIEs.917BLu")
                    .username("user")
                    .build()
    );

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }

}
