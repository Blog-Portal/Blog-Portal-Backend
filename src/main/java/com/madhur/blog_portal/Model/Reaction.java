package com.madhur.blog_portal.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reactions")
public class Reaction {
    /**
     * This is Reaction ID Field.
     */
    @Id
    private String reactionId;
    /**
     * This is User Reference Field.
     */
    @DBRef
    private User user;
    /**
     * This is Post Reference Field.
     */
    @DBRef
    private Post post;
    /**
     * This is Reaction Field.
     */
    private boolean reaction;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(post, reaction, reactionId, user);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Reaction other = (Reaction) obj;
        return Objects.equals(post, other.post) && reaction == other.reaction
                && Objects.equals(reactionId, other.reactionId)
                && Objects.equals(user, other.user);
    }

    /**
     * @return the reactionId
     */
    public String getReactionId() {
        return reactionId;
    }

    /**
     * @param reactionId the reactionId to set
     */
    public void setReactionId(final String reactionId) {
        this.reactionId = reactionId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(final Post post) {
        this.post = post;
    }

    /**
     * @return the reaction
     */
    public boolean isReaction() {
        return reaction;
    }

    /**
     * @param reaction the reaction to set
     */
    public void setReaction(final boolean reaction) {
        this.reaction = reaction;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Reaction [reactionId=" + reactionId + ", user=" + user
                + ", post=" + post + ", reaction=" + reaction + "]";
    }
}
