package io.github.adam_elzahiri.BlogAPI.security;

import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import io.github.adam_elzahiri.BlogAPI.user.User;
import io.github.adam_elzahiri.BlogAPI.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepo;

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new CustomException("Authenticated user not found", HttpStatus.NOT_FOUND));
    }

}
