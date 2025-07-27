package io.github.adam_elzahiri.BlogAPI.reaction;

import io.github.adam_elzahiri.BlogAPI.user.PublicUserResponse;

public record ReactionResponse(

        PublicUserResponse author,
        ReactionType reactionType

) {}