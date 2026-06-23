package com.santiago.hexagonal.modules.user.application.port.in;

public record RegisterUserCommand(
                String firstName,
                String lastName,
                String email,
                String password) {

}
