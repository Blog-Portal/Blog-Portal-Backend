package com.madhur.blog_portal.DTO.InDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Role;

/**
 * User Input Data Transfer Object(InDTO) layer where add default values of
 * fields.
 */
public class RegisterUserInDTO {
    /**
     * First Name of User.
     */
    @NotBlank(message = "First name must not be empty or blank")
    @Pattern(regexp = "[a-zA-Z]{3,15}", message = "first name must be between 3 to 15 characters and only alphabets")
    private String firstName;
    /**
     * Last Name of User.
     */
    @NotBlank(message = "Last name must not be empty or blank")
    @Pattern(regexp = "[a-zA-Z]{3,15}", message = "last name must be between 3 to 15 characters and only alphabets")
    private String lastName;
    /**
     * Email of User.
     */
    @NotBlank(message = "Email ID must not be empty or blank")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@nucleusteq.com", message = "email must ends with @nucleusteq.com")
    private String email;
    /**
     * Password of User.
     */
    @NotBlank(message = "Password must not be empty or blank")
    private String password;
    /**
     * Mobile Number of User.
     */
    @Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "mobile number must starts with 6,7,8,9 and length 10")
    @NotBlank(message = "Mobile number must not be empty or blank")
    private String mobile;
    /**
     * Role of User.
     */
    @NotNull(message = "Role must not be empty or blank")
    private Role role = Role.EMPLOYEE;
    /**
     * Gender of User.
     */
    @NotNull(message = "Gender must not be empty or blank")
    private Gender gender;
    /**
     * Designation of User.
     */
    @NotNull(message = "Designation must not be empty or blank")
    private Designation designation;

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation, email, firstName, gender, lastName,
                mobile, password, role);
    }

    /**
     * Equals method.
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
        RegisterUserInDTO other = (RegisterUserInDTO) obj;
        return Objects.equals(designation, other.designation)
                && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(gender, other.gender)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(mobile, other.mobile)
                && Objects.equals(password, other.password)
                && Objects.equals(role, other.role);
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "RegisterUserInDTO [firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", password=" + password
                + ", mobile=" + mobile + ", role=" + role + ", gender=" + gender
                + ", designation=" + designation + "]";
    }
}
