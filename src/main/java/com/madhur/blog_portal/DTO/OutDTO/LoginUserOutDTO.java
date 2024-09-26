package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

import com.madhur.blog_portal.Utilities.Role;

/**
 * User Output Data Transfer Object(OutDTO) particular fields are send to
 * FrontEnd.
 */
public class LoginUserOutDTO {
    /**
     * user ID.
     */
    private String userId;
    /**
     * First Name of User.
     */
    private String firstName;
    /**
     * Role of User.
     */
    private Role role;

    /**
     * HashCode Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, role, userId);
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
        LoginUserOutDTO other = (LoginUserOutDTO) obj;
        return Objects.equals(firstName, other.firstName)
                && Objects.equals(role, other.role)
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
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "LoginUserOutDTO [userId=" + userId + ", firstName=" + firstName
                + ", role=" + role + "]";
    }
}
