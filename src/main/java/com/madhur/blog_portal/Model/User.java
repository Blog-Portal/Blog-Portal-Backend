package com.madhur.blog_portal.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Role;

/**
 * User entity with fields.
 */
@Document(collection = "Users")
public class User {
    /**
     * user ID.
     */
    @Id
    private String userId;
    /**
     * First Name of User.
     */
    private String firstName;
    /**
     * Last Name of User.
     */
    private String lastName;
    /**
     * Email of User.
     */
    private String email;
    /**
     * Password of User.
     */
    private String password;
    /**
     * Mobile Number of User.
     */
    private String mobile;
    /**
     * Designation of User.
     */
    private Designation designation;
    /**
     * Gender of User.
     */
    private Gender gender;
    /**
     * Role of User.
     */
    private Role role;
    /**
     * Creation data of User.
     */
    private String createdAt;

    /**
     * hashCode methods.
     */
    @Override
    public int hashCode() {
        return Objects.hash(createdAt, designation, email, firstName, gender,
                lastName, mobile, password, role, userId);
    }

    /**
     * equals methods.
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
        User other = (User) obj;
        return Objects.equals(createdAt, other.createdAt)
                && Objects.equals(designation, other.designation)
                && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(gender, other.gender)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(mobile, other.mobile)
                && Objects.equals(password, other.password)
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the designation
     */
    public Designation getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(final Designation designation) {
        this.designation = designation;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(final Gender gender) {
        this.gender = gender;
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
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", password="
                + password + ", mobile=" + mobile + ", designation="
                + designation + ", gender=" + gender + ", role=" + role
                + ", createdAt=" + createdAt + "]";
    }
}
