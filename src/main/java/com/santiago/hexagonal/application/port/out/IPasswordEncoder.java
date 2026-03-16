package com.santiago.hexagonal.application.port.out;

public interface IPasswordEncoder {
    String encode(String password);

    boolean matches(String raw, String hash);
}
