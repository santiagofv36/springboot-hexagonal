package com.santiago.hexagonal.infrastructure.security.aspect;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

import com.santiago.hexagonal.application.exception.UnauthorizedException;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.application.port.out.ITokenService;
import com.santiago.hexagonal.infrastructure.security.SecurityContext;
import com.santiago.hexagonal.modules.user.application.repository.IUserRepository;
import com.santiago.hexagonal.modules.user.domain.User;

@Aspect
@Component
public class AuthAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IUserRepository userRepository;

    @Around("@annotation(authenticated)")
    public Object authenticate(ProceedingJoinPoint pjp,
            Authenticated authenticated) throws Throwable {

        String token = extractToken();

        if (token == null) {
            throw new UnauthorizedException();
        }

        UUID userId = tokenService.parseToken(token);

        User user = userRepository.findById(userId).orElseThrow(UnauthorizedException::new);

        SecurityContext.set(user);

        try {
            return pjp.proceed();
        } finally {
            SecurityContext.clear();
        }
    }

    private String extractToken() {

        if (request.getCookies() == null)
            return null;

        for (var cookie : request.getCookies()) {
            if (cookie.getName().equals("AUTH_TOKEN")) {
                return cookie.getValue();
            }
        }

        return null;
    }

}
