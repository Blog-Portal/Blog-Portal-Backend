package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.madhur.blog_portal.Utilities.Technology;

public class UpdatePostInDTO {
    /**
     * This is post id field.
     */
    @NotBlank(message = "PostID must not be empty or blank")
    private String postId;
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
    private String paragraph;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(heading, paragraph, postId, technology);
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
        UpdatePostInDTO other = (UpdatePostInDTO) obj;
        return Objects.equals(heading, other.heading)
                && Objects.equals(paragraph, other.paragraph)
                && Objects.equals(postId, other.postId)
                && technology == other.technology;
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
        return "UpdatePostInDTO [postId=" + postId + ", technology="
                + technology + ", heading=" + heading + ", paragraph="
                + paragraph + "]";
    }
}
