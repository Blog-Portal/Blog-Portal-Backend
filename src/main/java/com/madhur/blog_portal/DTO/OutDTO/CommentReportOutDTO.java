package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

public class CommentReportOutDTO {
    /**
     * This is Comment Id Field.
     */
    private String id;
    /**
     * This is first name Field.
     */
    private String firstName;
    /**
     * This is Last Name Field.
     */
    private String lastName;
    /**
     * This is Body Field.
     */
    private String body;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName, body, id, firstName);
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
        CommentReportOutDTO other = (CommentReportOutDTO) obj;
        return Objects.equals(lastName, other.lastName)
                && Objects.equals(body, other.body)
                && Objects.equals(id, other.id)
                && Objects.equals(firstName, other.firstName);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
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
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(final String body) {
        this.body = body;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "CommentReportOutDTO [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", body=" + body + "]";
    }
}
