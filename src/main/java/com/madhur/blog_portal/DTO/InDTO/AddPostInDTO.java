package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.madhur.blog_portal.Utilities.Technology;

public class AddPostInDTO {
    /**
     * This is User Reference.
     */
    @NotBlank(message = "UserID must not be empty or blank")
    private String userId;
    /**
     * This is Technology field.
     */
    @NotNull(message = "Technology must not be empty or blank")
    private Technology technology;
    /**
     * This is heading field.
     */
    @NotBlank(message = "Heading must not be empty or blank")
    @Pattern(regexp = ".{3,}", message = "Minimum size of the heading must be 3 and only alphabets")
    private String heading;
    /**
     * This is paragraph field.
     */
    @NotBlank(message = "Paragraph must not be empty or blank")
    private String paragraph;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(heading, paragraph, technology, userId);
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
        AddPostInDTO other = (AddPostInDTO) obj;
        return Objects.equals(heading, other.heading)
                && Objects.equals(paragraph, other.paragraph)
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "AddPostInDTO [userId=" + userId + ", technology=" + technology
                + ", heading=" + heading + ", paragraph=" + paragraph + "]";
    }
}
