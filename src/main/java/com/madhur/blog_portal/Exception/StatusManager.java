package com.madhur.blog_portal.Exception;

import org.springframework.http.HttpStatus;

/**
 * This class return all the exception in proper format.
 */
public class StatusManager {
    /**
     * It shows status of error.
     */
    private HttpStatus status = HttpStatus.OK;
    /**
     * It shows message of the error.
     */
    private Object message;

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final Object message) {
        this.message = message;
    }
}
