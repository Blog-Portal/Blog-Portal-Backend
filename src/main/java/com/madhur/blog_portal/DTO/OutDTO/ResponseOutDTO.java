package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

/**
 * Response Output Data Transfer Object(OutDTO) particular fields are send to
 * FrontEnd.
 */
public class ResponseOutDTO {
    /**
     * Error Message.
     */
    private String message;

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
        return "ResponseOutDTO [message=" + message + "]";
    }

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResponseOutDTO)) {
            return false;
        }
        ResponseOutDTO other = (ResponseOutDTO) obj;
        return Objects.equals(message, other.message);
    }
}
