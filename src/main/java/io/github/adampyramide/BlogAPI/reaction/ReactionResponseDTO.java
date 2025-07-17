package io.github.adam_elzahiri.BlogAPI.reaction;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserDTO;

public record ReactionResponseDTO(

        PublicUserDTO author,
        ReactionType reactionType

) {}