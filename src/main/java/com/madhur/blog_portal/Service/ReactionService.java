package com.madhur.blog_portal.Service;

import com.madhur.blog_portal.DTO.InDTO.ReactionInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;

/**
 * Reaction Service interface for managing Reaction operations.
 */
public interface ReactionService {
    /**
     * Add/Updates/Delete a reaction on a post.
     * @param reactionDTO The reaction DTO instance to be updated.
     * @return A string indicating whether the reaction was updated successfully
     *         or not.
     */
    ResponseOutDTO updateReaction(ReactionInDTO reactionDTO);
}
