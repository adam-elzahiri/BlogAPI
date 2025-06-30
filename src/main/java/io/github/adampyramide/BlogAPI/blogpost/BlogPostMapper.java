package io.github.adam_elzahiri.BlogAPI.blogpost;

import io.github.adam_elzahiri.BlogAPI.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BlogPostMapper {

    BlogPost toEntity(BlogPostRequestDTO dto);

    BlogPostResponseDTO toResponseDTO(BlogPost blogPost);

    void updateBlogPostFromDto(BlogPostRequestDTO dto, @MappingTarget BlogPost entity);

}
