package com.santiago.hexagonal.modules.user.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.santiago.hexagonal.modules.user.domain.User;
import com.santiago.hexagonal.modules.user.infrastructure.entity.UserEntity;

import com.santiago.hexagonal.infrastructure.IMapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<UserEntity, User> {
}
