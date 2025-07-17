package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.comment.CommentService;
import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import io.github.adam_elzahiri.BlogAPI.reaction.ReactionService;
import io.github.adam_elzahiri.BlogAPI.reaction.ReactionType;
import io.github.adam_elzahiri.BlogAPI.security.SecurityUtils;
import io.github.adam_elzahiri.BlogAPI.user.User;
import io.github.adam_elzahiri.BlogAPI.user.UserService;
import io.github.adam_elzahiri.BlogAPI.util.OwnershipValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BlogPostService {

    private final BlogPostRepository repo;
    private final BlogPostValidator validator;
    private final BlogPostMapper mapper;

    private final UserService userService;
    private final ReactionService reactionService;

    private final SecurityUtils securityUtils;

    public BlogPostService(BlogPostRepository repo, BlogPostValidator validator, BlogPostMapper mapper, UserService userService, ReactionService reactionService, SecurityUtils securityUtils) {
        this.repo = repo;
        this.validator = validator;
        this.mapper = mapper;
        this.userService = userService;
        this.reactionService = reactionService;
        this.securityUtils = securityUtils;
    }

    // ====================
    // Public methods
    // ====================

    public List<BlogPostResponseDTO> getBlogPosts() {
        return repo.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public BlogPostResponseDTO getBlogPostById(Long id) {
        BlogPostResponseDTO blogPostDTO = mapper.toResponseDTO(validator.getByIdOrThrow(id));

        Map<ReactionType, Long> reactionCounts = reactionService.getReactionCountsByPostId(id);
        blogPostDTO.setLikeCount(reactionCounts.getOrDefault(ReactionType.LIKE, 0L));
        blogPostDTO.setDislikeCount(reactionCounts.getOrDefault(ReactionType.DISLIKE, 0L));

        return blogPostDTO;
    }

    public void createBlogPost(BlogPostRequestDTO blogPostDTO) {
        BlogPost blogPost = mapper.toEntity(blogPostDTO);
        blogPost.setAuthor(securityUtils.getAuthenticatedUser());
        blogPost.setCreateTime(LocalDateTime.now());

        repo.save(blogPost);
    }

    public void editBlogPostById(Long id, BlogPostRequestDTO blogPostDTO) {
        BlogPost blogPost = validator.getByIdOrThrow(id);

        OwnershipValidator.authorizeAuthor(
                blogPost.getAuthor(),
                securityUtils.getAuthenticatedUser(),
                "blogpost"
        );

        mapper.updateEntityWithDto(blogPostDTO, blogPost);
        repo.save(blogPost);
    }

    public void deleteBlogPostById(Long id) {
        BlogPost blogPost = validator.getByIdOrThrow(id);

        OwnershipValidator.authorizeAuthor(
                blogPost.getAuthor(),
                securityUtils.getAuthenticatedUser(),
                "blogpost"
        );

        repo.deleteById(id);
    }

    public void bulkDeletePostsByIds(List<Long> ids) {
        List<BlogPost> blogPosts = repo.findAllById(ids);

        if (blogPosts.size() != ids.size())
            throw new CustomException("Zero posts found", HttpStatus.NOT_FOUND);

        User authenticatedUser = securityUtils.getAuthenticatedUser();
        for (BlogPost blogPost : blogPosts) {
            OwnershipValidator.authorizeAuthor(
                    blogPost.getAuthor(),
                    authenticatedUser,
                    "blogpost"
            );
        }

        repo.deleteAll(blogPosts);
    }

    public List<BlogPostResponseDTO> getBlogPostsByUserId(Long userId) {
        userService.getUserOrThrow(userId);

        return repo.findAllByAuthor_Id(userId).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

}
