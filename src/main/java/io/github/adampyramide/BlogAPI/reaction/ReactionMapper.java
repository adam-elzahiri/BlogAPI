package io.github.adam_elzahiri.BlogAPI.reaction;

import io.github.adam_elzahiri.BlogAPI.reaction.dto.ReactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReactionMapper {

    ReactionResponse toResponse(Reaction entity);

}
