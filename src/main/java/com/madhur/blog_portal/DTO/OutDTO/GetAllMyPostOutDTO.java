package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

import com.madhur.blog_portal.Utilities.Status;

public class GetAllMyPostOutDTO {
    /**
     * This is UserPostOutDTO instance.
     */
    private UserPostOutDTO userPostOutDTO;
    /**
     * This is Status field.
     */
    private Status status;
    /**
     * This is Like field.
     */
    private boolean like;
    /**
     * This is Dislike field.
     */
    private boolean dislike;
    /**
     * This is Like Count field.
     */
    private int likeCount;
    /**
     * This is Dislike Count field.
     */
    private int dislikeCount;
    /**
     * This is Comment Count field.
     */
    private int commentCount;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commentCount, dislike, dislikeCount, like,
                likeCount, status, userPostOutDTO);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetAllMyPostOutDTO)) {
            return false;
        }
        GetAllMyPostOutDTO other = (GetAllMyPostOutDTO) obj;
        return commentCount == other.commentCount && dislike == other.dislike
                && dislikeCount == other.dislikeCount && like == other.like
                && likeCount == other.likeCount && status == other.status
                && Objects.equals(userPostOutDTO, other.userPostOutDTO);
    }

    /**
     * @return the userPostOutDTO
     */
    public UserPostOutDTO getUserPostOutDTO() {
        return userPostOutDTO;
    }

    /**
     * @param userPostOutDTO the userPostOutDTO to set
     */
    public void setUserPostOutDTO(final UserPostOutDTO userPostOutDTO) {
        this.userPostOutDTO = userPostOutDTO;
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

    /**
     * @return the like
     */
    public boolean isLike() {
        return like;
    }

    /**
     * @param like the like to set
     */
    public void setLike(final boolean like) {
        this.like = like;
    }

    /**
     * @return the dislike
     */
    public boolean isDislike() {
        return dislike;
    }

    /**
     * @param dislike the dislike to set
     */
    public void setDislike(final boolean dislike) {
        this.dislike = dislike;
    }

    /**
     * @return the likeCount
     */
    public int getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount the likeCount to set
     */
    public void setLikeCount(final int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return the dislikeCount
     */
    public int getDislikeCount() {
        return dislikeCount;
    }

    /**
     * @param dislikeCount the dislikeCount to set
     */
    public void setDislikeCount(final int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * @return the commentCount
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(final int commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "GetAllMyPostOutDTO [userPostOutDTO=" + userPostOutDTO
                + ", status=" + status + ", like=" + like + ", dislike="
                + dislike + ", likeCount=" + likeCount + ", dislikeCount="
                + dislikeCount + ", commentCount=" + commentCount + "]";
    }
}
