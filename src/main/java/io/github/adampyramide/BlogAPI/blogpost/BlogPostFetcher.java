package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostFetcher {

    private final BlogPostRepository repo;

    public BlogPost getByIdOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomException("Blogpost not found", HttpStatus.NOT_FOUND));
    }

}
