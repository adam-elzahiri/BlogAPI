package io.github.adam_elzahiri.BlogAPI.comment;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {

    private Long id;
    private Long postId;
    private Long parentCommentId;
    private PublicUserDTO author;
    private String body;
    private boolean hasReplies;

}