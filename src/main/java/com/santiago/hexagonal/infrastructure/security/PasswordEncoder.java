package com.santiago.hexagonal.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.santiago.hexagonal.application.port.out.IPasswordEncoder;

@Service
public class PasswordEncoder implements IPasswordEncoder {

    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(String raw, String hash) {
        return encoder.matches(raw, hash);
    }

}
