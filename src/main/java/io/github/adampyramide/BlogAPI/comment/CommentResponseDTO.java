package io.github.adam_elzahiri.BlogAPI.comment;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;

public record CommentResponseDTO(

        long id,
        long postId,
        Long parentCommentId,
        PublicUserDTO author,
        String body
        
) {}
