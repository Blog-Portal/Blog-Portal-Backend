package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class CommentInDTO {
    /**
     * This is User Id Field.
     */
    @NotBlank(message = "UserID must not be empty or blank")
    private String userId;
    /**
     * This is Post Id Field.
     */
    @NotBlank(message = "PostID must not be empty or blank")
    private String postId;
    /**
     * This is Comment Field.
     */
    @NotBlank(message = "Message must not be empty or blank")
    private String message;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(message, postId, userId);
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
        CommentInDTO other = (CommentInDTO) obj;
        return Objects.equals(message, other.message)
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "CommentInDTO [userId=" + userId + ", postId=" + postId
                + ", message=" + message + "]";
    }
}
