package io.github.adam_elzahiri.BlogAPI.user;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserRequest(

        String username,
        MultipartFile profilePicture,
        String description

) {}
