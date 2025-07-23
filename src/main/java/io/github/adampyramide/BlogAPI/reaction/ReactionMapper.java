package io.github.adam_elzahiri.BlogAPI.reaction;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReactionMapper {

    ReactionResponseDTO toResponseDTO(Reaction entity);

}
