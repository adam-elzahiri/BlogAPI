package io.github.adam_elzahiri.BlogAPI.user;

import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository repo;
    UserMapper mapper;

    public UserService(UserRepository userRepo, UserMapper mapper) {
        this.repo = userRepo;
        this.mapper = mapper;
    }

    // ====================
    // Public methods
    // ====================

    public PublicUserDTO getUserById(Long id) {
        return mapper.toPublicDTO(getUserOrThrow(id));
    }

    // ====================
    // Internal methods
    // ====================

    public User getUserOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

}