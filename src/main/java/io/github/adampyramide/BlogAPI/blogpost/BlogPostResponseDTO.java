package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.reaction.ReactionType;
import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostResponseDTO {
    
    private Long id;
    private PublicUserDTO author;
    private String title;
    private String body;
    private LocalDateTime createTime;
    private long likeCount;
    private long dislikeCount;
    private ReactionType userReaction;

}