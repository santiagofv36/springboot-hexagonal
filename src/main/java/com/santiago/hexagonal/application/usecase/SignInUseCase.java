package com.santiago.hexagonal.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.port.in.SignInCommand;
import com.santiago.hexagonal.application.port.out.IPasswordEncoder;
import com.santiago.hexagonal.application.port.out.ITokenService;
import com.santiago.hexagonal.modules.user.application.repository.IUserRepository;
import com.santiago.hexagonal.modules.user.domain.User;
import com.santiago.hexagonal.modules.user.domain.exceptions.InvalidCredentials;

import com.santiago.hexagonal.application.interfaces.UseCase;

@UseCase
public class SignInUseCase implements IApplicationService<SignInCommand, String> {

    private final IUserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    public SignInUseCase(IUserRepository userRepository, IPasswordEncoder passwordEncoder,
            ITokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String execute(SignInCommand request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentials("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.password())) {
            throw new InvalidCredentials("Invalid credentials");
        }

        return tokenService.generateToken(user.id());
    }

}
