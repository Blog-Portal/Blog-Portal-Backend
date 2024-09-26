package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

public class GetAllPostOutDTO {
    /**
     * This is UserPostOutDTO instance.
     */
    private UserPostOutDTO userPostOutDTO;
    /**
     * This is Like field.
     */
    private boolean like;
    /**
     * This is Dislike field.
     */
    private boolean dislike;
    /**
     * This is Report field.
     */
    private boolean report;
    /**
     * This is Like Count field.
     */
    private int likeCount;
    /**
     * This is Dislike Count field.
     */
    private int dislikeCount;
    /**
     * This is Report Count field.
     */
    private int reportCount;
    /**
     * This is Comment Count field.
     */
    private int commentCount;
    /**
     * This is boolean variable that checks this is my field or not.
     */
    private boolean myPost;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commentCount, dislike, dislikeCount, myPost, like,
                likeCount, report, reportCount, userPostOutDTO);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetAllPostOutDTO)) {
            return false;
        }
        GetAllPostOutDTO other = (GetAllPostOutDTO) obj;
        return commentCount == other.commentCount && dislike == other.dislike
                && dislikeCount == other.dislikeCount && myPost == other.myPost
                && like == other.like && likeCount == other.likeCount
                && report == other.report && reportCount == other.reportCount
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
     * @return the report
     */
    public boolean isReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(final boolean report) {
        this.report = report;
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
     * @return the reportCount
     */
    public int getReportCount() {
        return reportCount;
    }

    /**
     * @param reportCount the reportCount to set
     */
    public void setReportCount(final int reportCount) {
        this.reportCount = reportCount;
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
     * @return the isMyPost
     */
    public boolean isMyPost() {
        return myPost;
    }

    /**
     * @param myPost the isMyPost to set
     */
    public void setMyPost(final boolean myPost) {
        this.myPost = myPost;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "GetAllPostOutDTO [userPostOutDTO=" + userPostOutDTO + ", like="
                + like + ", dislike=" + dislike + ", report=" + report
                + ", likeCount=" + likeCount + ", dislikeCount=" + dislikeCount
                + ", reportCount=" + reportCount + ", commentCount="
                + commentCount + ", isMyPost=" + myPost + "]";
    }
}
