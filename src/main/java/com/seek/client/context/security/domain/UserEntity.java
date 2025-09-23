package com.seek.client.context.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String role; // Ej: USER, ADMIN
}
