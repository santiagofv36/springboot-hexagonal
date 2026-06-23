package com.santiago.hexagonal.infrastructure.web.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.port.in.SignInCommand;
import com.santiago.hexagonal.modules.user.application.port.in.RegisterUserCommand;
import com.santiago.hexagonal.modules.user.domain.User;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IApplicationService<SignInCommand, String> signInUseCase;
    @Autowired
    private IApplicationService<RegisterUserCommand, User> registerUseCase;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserCommand command) {
        User user = registerUseCase.execute(command);
        return ResponseEntity.created(URI.create("/api/v1/auth/register/" + user.id().toString())).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInCommand command, HttpServletResponse response) {
        try {
            String token = signInUseCase.execute(command);
            response.addHeader("Set-Cookie", "AUTH_TOKEN=" + token + "; HttpOnly; Secure; Path=/; Max-Age=3600;");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/sign-out")
    public void signOut(HttpServletResponse response) {
        response.addHeader("Set-Cookie", "AUTH_TOKEN=; HttpOnly; Secure; Path=/; Max-Age=0");
    }

}
