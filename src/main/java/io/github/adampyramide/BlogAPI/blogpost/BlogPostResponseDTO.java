package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;

import java.time.LocalDateTime;

public record BlogPostResponseDTO(

        long id,
        PublicUserDTO author,
        String title,
        String body,
        LocalDateTime createTime

) {}