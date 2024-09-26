package com.madhur.blog_portal.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

@Document(collection = "Posts")
public class Post {
    /**
     * This is post ID.
     */
    @Id
    private String postId;
    /**
     * This is User Reference.
     */
    @DBRef
    private User user;
    /**
     * This is Technology field.
     */
    private Technology technology;
    /**
     * This is heading field.
     */
    private String heading;
    /**
     * This is paragraph field.
     */
    private String paragraph;
    /**
     * This is created date field.
     */
    private String createdAt;
    /**
     * This is updated date field.
     */
    private String updatedAt;
    /**
     * This is Status field.
     */
    private Status status;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(createdAt, heading, paragraph, postId, status,
                technology, updatedAt, user);
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
        Post other = (Post) obj;
        return Objects.equals(createdAt, other.createdAt)
                && Objects.equals(heading, other.heading)
                && Objects.equals(paragraph, other.paragraph)
                && Objects.equals(postId, other.postId)
                && status == other.status && technology == other.technology
                && Objects.equals(updatedAt, other.updatedAt)
                && Objects.equals(user, other.user);
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Post [postId=" + postId + ", user=" + user + ", technology="
                + technology + ", heading=" + heading + ", paragraph="
                + paragraph + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", status=" + status + "]";
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
     * @return the technology
     */
    public Technology getTechnology() {
        return technology;
    }

    /**
     * @param technology the technology to set
     */
    public void setTechnology(final Technology technology) {
        this.technology = technology;
    }

    /**
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(final String heading) {
        this.heading = heading;
    }

    /**
     * @return the paragraph
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * @param paragraph the paragraph to set
     */
    public void setParagraph(final String paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
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
