package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) handle login request.
 */
public class LoginInDTO {
    /**
     * User Email Address for Authentication.
     */
    @NotBlank(message = "Email ID must not be empty or blank")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@nucleusteq.com", message = "email must ends with @nucleusteq.com")
    private String email;
    /**
     * User Password for Authentication.
     */
    @NotBlank(message = "Password must not be empty or blank")
    private String password;

    /**
     * HashCode Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    /**
     * Equals Method.
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
        LoginInDTO other = (LoginInDTO) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(password, other.password);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "LoginInDTO [email=" + email + ", password=" + password + "]";
    }
}
