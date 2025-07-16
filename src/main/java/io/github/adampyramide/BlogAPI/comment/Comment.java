package io.github.adam_elzahiri.BlogAPI.comment;

import io.github.adam_elzahiri.BlogAPI.blogpost.BlogPost;
import io.github.adam_elzahiri.BlogAPI.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPost post;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String body;
}
