package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
