package com.santiago.hexagonal.modules.user.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.santiago.hexagonal.modules.user.domain.enums.Roles;
import com.santiago.hexagonal.modules.user.domain.enums.UserStatus;

public record User(
                UUID id,
                String firstName,
                String lastName,
                String email,
                String password,
                Roles role,
                UserStatus status,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
