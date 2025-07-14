package io.github.adam_elzahiri.BlogAPI.comment;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;

public record CommentResponseDTO(

        Long id,
        Long postId,
        Long parentCommentId,
        PublicUserDTO author,
        String body
        
) {}
