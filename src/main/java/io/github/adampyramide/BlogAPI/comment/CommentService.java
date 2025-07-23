package io.github.adam_elzahiri.BlogAPI.comment;

import io.github.adam_elzahiri.BlogAPI.blogpost.BlogPostFetcher;
import io.github.adam_elzahiri.BlogAPI.exception.CustomException;
import io.github.adam_elzahiri.BlogAPI.security.SecurityUtils;
import io.github.adam_elzahiri.BlogAPI.util.OwnershipValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository repo;
    private final CommentMapper mapper;

    private final BlogPostFetcher blogPostFetcher;

    private final SecurityUtils securityUtils;

    public CommentService(CommentRepository repo, CommentMapper mapper, BlogPostFetcher blogPostFetcher, SecurityUtils securityUtils) {
        this.repo = repo;
        this.mapper = mapper;
        this.blogPostFetcher = blogPostFetcher;
        this.securityUtils = securityUtils;
    }

    // ====================
    // Public methods
    // ====================

    public CommentResponseDTO getCommentById(Long id) {
        return toResponseDTO(getCommentOrThrow(id));
    }

    public Page<CommentResponseDTO> getCommentsByPostId(Long postId, Pageable pageable) {
        return repo.findAllByPostId(postId, pageable).map(this::toResponseDTO);
    }

    public Page<CommentResponseDTO> getCommentsByAuthorId(Long userId, Pageable pageable) {
        return repo.findAllByAuthor_Id(userId, pageable).map(this::toResponseDTO);
    }

    public void editCommentById(Long id, CommentRequestDTO commentDTO) {
        Comment comment = getCommentOrThrow(id);

        OwnershipValidator.authorizeAuthor(
                comment.getAuthor(),
                securityUtils.getAuthenticatedUser(),
                "comment"
        );

        mapper.updateEntityWithDto(commentDTO, comment);
        repo.save(comment);
    }

    public void deleteCommentById(Long id) {
        Comment comment = getCommentOrThrow(id);

        OwnershipValidator.authorizeAuthor(
                comment.getAuthor(),
                securityUtils.getAuthenticatedUser(),
                "comment"
        );

        repo.deleteById(id);
    }

    public void createComment(Long postId, CommentRequestDTO commentDTO) {
        Comment comment = mapper.toEntity(commentDTO);
        comment.setAuthor(securityUtils.getAuthenticatedUser());
        comment.setPost(blogPostFetcher.getByIdOrThrow(postId));

        repo.save(comment);
    }

    // ====================
    // Private methods
    // ====================

    private Comment getCommentOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomException("Comment not found", HttpStatus.NOT_FOUND));
    }

    private CommentResponseDTO toResponseDTO(Comment comment) {
        CommentResponseDTO dto = mapper.toResponseDTO(comment);
        dto.setHasReplies(repo.existsByParentCommentId(comment.getId()));
        return dto;
    }


}
