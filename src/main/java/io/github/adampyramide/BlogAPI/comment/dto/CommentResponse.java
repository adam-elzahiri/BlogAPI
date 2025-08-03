package io.github.adam_elzahiri.BlogAPI.comment.dto;

import io.github.adam_elzahiri.BlogAPI.user.dto.PublicUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private Long postId;
    private PublicUserResponse author;
    private Long parentCommentId;
    private String body;
    private boolean hasReplies;

}