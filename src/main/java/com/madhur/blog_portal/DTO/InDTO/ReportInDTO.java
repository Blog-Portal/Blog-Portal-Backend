package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class ReportInDTO {
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
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
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
        ReportInDTO other = (ReportInDTO) obj;
        return Objects.equals(postId, other.postId)
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "ReportInDTO [userId=" + userId + ", postId=" + postId + "]";
    }
}
