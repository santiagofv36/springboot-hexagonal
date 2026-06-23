package com.santiago.hexagonal.application.port.out;

import java.util.UUID;

public interface ITokenService {
    String generateToken(UUID subject);

    UUID parseToken(String token);
}
