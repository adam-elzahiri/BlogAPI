package io.github.adam_elzahiri.BlogAPI.post;

import io.github.adam_elzahiri.BlogAPI.user.User;

import java.time.LocalDateTime;

public record PostDTO (
        String title,
        String content,
        LocalDateTime createTime,
        User author
) {}