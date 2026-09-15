package io.github.adam_elzahiri.BlogAPI.reaction.dto;

import io.github.adam_elzahiri.BlogAPI.reaction.ReactionType;

public record ReactionRequest(

        ReactionType reactionType

) {}
