package io.github.adam_elzahiri.BlogAPI.reaction;

import io.github.adam_elzahiri.BlogAPI.reaction.dto.ReactionResponse;
import io.github.adam_elzahiri.BlogAPI.user.UserMapper;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = UserMapper.class
)
public interface ReactionMapper {

    ReactionResponse toResponse(Reaction entity);

}
