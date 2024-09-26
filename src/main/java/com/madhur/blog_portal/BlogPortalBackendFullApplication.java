package com.madhur.blog_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start the MyProject application.
 * @since 2023/08/25
 * @version 1.0.0
 * @author Madhur Jatiya
 */
@SpringBootApplication
public class BlogPortalBackendFullApplication {
    protected BlogPortalBackendFullApplication() {
    }

    /**
     * Main method to start the Spring Boot application.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(BlogPortalBackendFullApplication.class, args);
    }
}
