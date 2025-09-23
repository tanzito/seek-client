package com.seek.client.context.security.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByUsername(String username);
}
