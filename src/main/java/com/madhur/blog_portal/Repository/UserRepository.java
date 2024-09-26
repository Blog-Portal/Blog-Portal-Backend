package com.madhur.blog_portal.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madhur.blog_portal.Model.User;

/**
 * Repository for managing User.
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds a list of users by their email and password.
     * @param email The email of the user
     * @param password The password of the user
     * @return A list of users matching the given email and password
     */
    User getByEmailIgnoreCaseAndPassword(String email, String password);
    /**
     * @param email The email of the user
     * @param mobile The mobile number of the user
     * @return true false according presence of mobile number or email address
     */
    boolean existsByEmailIgnoreCaseOrMobile(String email, String mobile);
    /**
     * Finds a user by their email address.
     * @param email the email of the user to find
     * @return an {@link Optional} containing the user if found, or empty if not
     *         found
     */
    Optional<User> findByEmail(String email);
}
