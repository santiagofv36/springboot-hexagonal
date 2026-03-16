package com.santiago.hexagonal.modules.user.application.usecase;

import java.time.LocalDateTime;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.port.out.IPasswordEncoder;
import com.santiago.hexagonal.modules.user.application.port.in.RegisterUserCommand;
import com.santiago.hexagonal.modules.user.application.repository.IUserRepository;
import com.santiago.hexagonal.modules.user.domain.User;
import com.santiago.hexagonal.modules.user.domain.enums.*;
import com.santiago.hexagonal.modules.user.domain.exceptions.UserExistsException;

import com.santiago.hexagonal.application.interfaces.UseCase;

@UseCase
public class RegisterUseCase implements IApplicationService<RegisterUserCommand, User> {

    private final IUserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;

    public RegisterUseCase(IUserRepository userRepository, IPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(RegisterUserCommand request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserExistsException("User already exists");
        }

        String hash = passwordEncoder.encode(request.password());

        User user = new User(
                null,
                request.firstName(),
                request.lastName(),
                request.email(),
                hash,
                Roles.CLIENT,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now());
        return userRepository.save(user);
    }

}
