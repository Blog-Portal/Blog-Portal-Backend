package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

public class MyPostInDTO {
    /**
     * This is user ID.
     */
    @NotBlank(message = "UserID must not be empty or blank")
    private String userId;
    /**
     * This is Status field.
     */
    private Status status;
    /**
     * This is Technology field.
     */
    private Technology technology;
    /**
     * This is Heading field.
     */
    private String heading;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(heading, status, technology, userId);
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
        MyPostInDTO other = (MyPostInDTO) obj;
        return Objects.equals(heading, other.heading) && status == other.status
                && technology == other.technology
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "MyPostInDTO [userId=" + userId + ", status=" + status
                + ", technology=" + technology + ", heading=" + heading + "]";
    }
}
