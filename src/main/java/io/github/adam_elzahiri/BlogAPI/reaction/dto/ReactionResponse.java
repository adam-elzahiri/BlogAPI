package io.github.adam_elzahiri.BlogAPI.reaction.dto;

import io.github.adam_elzahiri.BlogAPI.reaction.ReactionType;
import io.github.adam_elzahiri.BlogAPI.user.dto.UserPreviewResponse;

public record ReactionResponse(

        UserPreviewResponse author,
        ReactionType reactionType

) {}