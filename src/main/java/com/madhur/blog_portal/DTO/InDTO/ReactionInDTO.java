package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReactionInDTO {
    /**
     * This is User Id Field.
     */
    @NotBlank(message = "UserId must not be empty or blank")
    private String userId;
    /**
     * This is Post Id Field.
     */
    @NotBlank(message = "PostId must not be empty or blank")
    private String postId;
    /**
     * This is Current Reaction Field.
     */
    @NotNull(message = "Reaction must not be null")
    private Boolean currentReaction;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(currentReaction, postId, userId);
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
        ReactionInDTO other = (ReactionInDTO) obj;
        return Objects.equals(currentReaction, other.currentReaction)
                && Objects.equals(postId, other.postId)
                && Objects.equals(userId, other.userId);
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * @return the postId
     */
    public String getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(final String postId) {
        this.postId = postId;
    }

    /**
     * @return the currentReaction
     */
    public Boolean isCurrentReaction() {
        return currentReaction;
    }

    /**
     * @param currentReaction the currentReaction to set
     */
    public void setCurrentReaction(final Boolean currentReaction) {
        this.currentReaction = currentReaction;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "ReactionInDTO [userId=" + userId + ", postId=" + postId
                + ", currentReaction=" + currentReaction + "]";
    }
}
