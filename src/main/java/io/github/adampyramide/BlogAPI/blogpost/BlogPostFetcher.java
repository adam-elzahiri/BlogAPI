package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BlogPostFetcher {

    private final BlogPostRepository repo;

    public BlogPostFetcher(BlogPostRepository repo) {
        this.repo = repo;
    }

    public BlogPost getByIdOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomException("Blogpost not found", HttpStatus.NOT_FOUND));
    }

}
