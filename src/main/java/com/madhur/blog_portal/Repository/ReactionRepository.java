package com.madhur.blog_portal.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Reaction;
import com.madhur.blog_portal.Model.User;

/**
 * Repository for managing reactions.
 */
public interface ReactionRepository extends MongoRepository<Reaction, String> {
    /**
     * Get a reaction by user and post.
     * @param user The user who reacted to the post.
     * @param post The post to which the reaction belongs.
     * @return The reaction associated with the user and post.
     */
    Reaction getByUserAndPost(User user, Post post);
    /**
     * Delete reactions associated with a specific post.
     * @param post The post for which reactions should be deleted.
     */
    void deleteByPost(Post post);
    /**
     * Count the number of reactions of a specific type (like/dislike) for a
     * given post.
     * @param post The post for which the reaction count is needed.
     * @param reaction The type of reaction (true for like, false for dislike).
     * @return The number of reactions of the specified type for the post.
     */
    int countByPostAndReaction(Post post, boolean reaction);
}
