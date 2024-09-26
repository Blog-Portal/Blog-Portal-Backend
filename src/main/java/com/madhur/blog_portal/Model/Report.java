package com.madhur.blog_portal.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reports")
public class Report {
    /**
     * This is Report ID Field.
     */
    @Id
    private String reportId;
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
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(post, reportId, user);
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
        Report other = (Report) obj;
        return Objects.equals(post, other.post)
                && Objects.equals(reportId, other.reportId)
                && Objects.equals(user, other.user);
    }

    /**
     * @return the reportId
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(final String reportId) {
        this.reportId = reportId;
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Report [reportId=" + reportId + ", user=" + user + ", post="
                + post + "]";
    }
}
