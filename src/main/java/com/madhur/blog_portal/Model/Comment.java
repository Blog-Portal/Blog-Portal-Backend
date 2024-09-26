package com.madhur.blog_portal.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comments")
public class Comment {
    /**
     * This is Comment ID Field.
     */
    @Id
    private String commentId;
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
     * This is Message Field.
     */
    private String message;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commentId, message, post, user);
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
        Comment other = (Comment) obj;
        return Objects.equals(commentId, other.commentId)
                && Objects.equals(message, other.message)
                && Objects.equals(post, other.post)
                && Objects.equals(user, other.user);
    }

    /**
     * @return the commentId
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(final String commentId) {
        this.commentId = commentId;
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
        return "Comment [commentId=" + commentId + ", user=" + user + ", post="
                + post + ", message=" + message + "]";
    }
}
