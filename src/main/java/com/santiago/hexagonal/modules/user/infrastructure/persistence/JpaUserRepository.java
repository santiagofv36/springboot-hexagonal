package com.santiago.hexagonal.modules.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiago.hexagonal.modules.user.infrastructure.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
