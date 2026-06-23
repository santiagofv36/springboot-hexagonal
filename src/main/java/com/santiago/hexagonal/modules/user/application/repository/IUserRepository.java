package com.santiago.hexagonal.modules.user.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.santiago.hexagonal.modules.user.domain.User;

public interface IUserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);

    User save(User user);
}
