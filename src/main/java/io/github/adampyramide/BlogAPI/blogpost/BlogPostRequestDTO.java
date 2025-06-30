package io.github.adam_elzahiri.BlogAPI.blogpost;

public record BlogPostRequestDTO(
        String title,
        String body
) {}