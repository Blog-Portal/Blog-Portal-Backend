package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.madhur.blog_portal.Utilities.Status;

public class PostApprovalInDTO {
    /**
     * This is Post Id Field.
     */
    @NotBlank(message = "PostID must not be empty or blank")
    private String postId;
    /**
     * This is Status Field.
     */
    @NotNull(message = "Status must not be empty or blank")
    private Status status;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(postId, status);
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
        PostApprovalInDTO other = (PostApprovalInDTO) obj;
        return Objects.equals(postId, other.postId) && status == other.status;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "PostApprovalInDTO [postId=" + postId + ", status=" + status
                + "]";
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
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final Status status) {
        this.status = status;
    }
}
