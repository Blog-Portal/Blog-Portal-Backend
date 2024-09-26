package com.madhur.blog_portal.Utilities;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class for password encryption using Base64.
 */
public final class PasswordEncryption {
    private PasswordEncryption() {
    }

    /**
     * Encrypts the provided password using Base64.
     * @param password The password to be encrypted.
     * @return The encrypted password.
     */
    public static String getEncryptedPassword(final String password) {
        String encryptedPassword;
        encryptedPassword = Base64.getEncoder()
                .encodeToString(password.getBytes(StandardCharsets.UTF_8));
        return encryptedPassword;
    }
}
