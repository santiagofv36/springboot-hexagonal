package com.santiago.hexagonal.infrastructure.security;

import org.springframework.stereotype.Component;

import com.santiago.hexagonal.modules.user.domain.User;

@Component
public class SecurityContext {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void set(User user) {
        currentUser.set(user);
    }

    public static User get() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
