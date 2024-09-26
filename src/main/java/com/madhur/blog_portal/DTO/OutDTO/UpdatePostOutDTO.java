package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

import com.madhur.blog_portal.Utilities.Technology;

public class UpdatePostOutDTO {
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
    private Technology technology;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(heading, paragraph, technology);
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
        UpdatePostOutDTO other = (UpdatePostOutDTO) obj;
        return Objects.equals(heading, other.heading)
                && Objects.equals(paragraph, other.paragraph)
                && Objects.equals(technology, other.technology);
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "UpdatePostOutDTO [heading=" + heading + ", paragraph="
                + paragraph + ", technology=" + technology + "]";
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
    public Technology getTechnology() {
        return technology;
    }

    /**
     * @param technology the technology to set
     */
    public void setTechnology(final Technology technology) {
        this.technology = technology;
    }
}
