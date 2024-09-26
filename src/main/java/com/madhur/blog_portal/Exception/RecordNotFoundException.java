package com.madhur.blog_portal.Exception;

/**
 * Custom exception class for handling custom exception.
 */
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs of custom exception class with the specified error message.
     * @param errorMessage reason of exception why it occur
     */
    public RecordNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
