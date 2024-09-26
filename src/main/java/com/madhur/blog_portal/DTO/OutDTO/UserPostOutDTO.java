package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

public class UserPostOutDTO {
    /**
     * This is post ID.
     */
    private String postId;
    /**
     * This is heading field.
     */
    private String heading;
    /**
     * This is paragraph field.
     */
    private String paragraph;
    /**
     * This is Technology field.
     */
    private String technology;
    /**
     * This is updated date field.
     */
    private String updatedAt;
    /**
     * This is firstName field.
     */
    private String firstName;
    /**
     * This is lastName field.
     */
    private String lastName;
    /**
     * This is DesignationEnum field.
     */
    private String designation;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation, firstName, heading, lastName,
                paragraph, postId, technology, updatedAt);
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
        UserPostOutDTO other = (UserPostOutDTO) obj;
        return Objects.equals(designation, other.designation)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(heading, other.heading)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(paragraph, other.paragraph)
                && Objects.equals(postId, other.postId)
                && Objects.equals(technology, other.technology)
                && Objects.equals(updatedAt, other.updatedAt);
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
     * @return the technology
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * @param technology the technology to set
     */
    public void setTechnology(final String technology) {
        this.technology = technology;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(final String designation) {
        this.designation = designation;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "UserPostOutDTO [postId=" + postId + ", heading=" + heading
                + ", paragraph=" + paragraph + ", technology=" + technology
                + ", updatedAt=" + updatedAt + ", firstName=" + firstName
                + ", lastName=" + lastName + ", designation=" + designation
                + "]";
    }
}
