package com.santiago.hexagonal.modules.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santiago.hexagonal.modules.user.application.repository.IUserRepository;
import com.santiago.hexagonal.modules.user.domain.User;
import com.santiago.hexagonal.modules.user.infrastructure.entity.UserEntity;
import com.santiago.hexagonal.modules.user.infrastructure.mapper.UserMapper;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toDomain(jpaUserRepository.save(userEntity));
    }

}
