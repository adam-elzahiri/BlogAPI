package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.reaction.ReactionType;
import io.github.adam_elzahiri.BlogAPI.user.PublicUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostResponse {
    
    private Long id;
    private PublicUserResponse author;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private long likeCount;
    private long dislikeCount;
    private ReactionType userReaction;

}