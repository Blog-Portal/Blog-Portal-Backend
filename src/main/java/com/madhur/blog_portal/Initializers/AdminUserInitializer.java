package com.madhur.blog_portal.Initializers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.madhur.blog_portal.Controller.UserController;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.Utilities.DateFormatUtility;
import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.PasswordEncryption;
import com.madhur.blog_portal.Utilities.Role;

/**
 * This component initializes the admin user in the MongoDB database.
 */
@Component
public class AdminUserInitializer implements CommandLineRunner {
    /**
     * Logger for track log reports.
     */
    private Logger logger = LogManager.getLogger(UserController.class);
    /**
     * User Repository Object.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Email address of the admin user. This value is injected from the.
     * environment with a default value of {@code admin@nucleusteq.com}.
     */
    @Value("${ADMIN_EMAIL:admin@nucleusteq.com}")
    private String email;
    /**
     * Password of the admin user. This value is injected from the environment.
     * with a default value of {@code Admin@12345}.
     */
    @Value("${ADMIN_PASSWORD:Admin@12345}")
    private String password;
    /**
     * First Name of the admin user. This value is injected from the.
     * environment with a default value of {@code Admin}.
     */
    @Value("${ADMIN_FIRST_NAME:Admin}")
    private String firstName;
    /**
     * Last Name of the admin user. This value is injected from the environment.
     * with a default value of {@code Admin}.
     */
    @Value("${ADMIN_LAST_NAME:Admin}")
    private String lastName;

    /**
     * Create a new admin user if not exit when application stated.
     */
    @Override
    public void run(final String... args) throws Exception {
        logger.info("Request for create admin user with email: {}", email);
        try {
            if (!userRepository.findByEmail(email).isPresent()) {
                User adminUser = new User();
                adminUser.setFirstName(firstName);
                adminUser.setLastName(lastName);
                adminUser.setEmail(email);
                adminUser.setPassword(
                        PasswordEncryption.getEncryptedPassword(password));
                adminUser.setMobile("9407192414");
                adminUser.setDesignation(Designation.OTHER);
                adminUser.setGender(Gender.MALE);
                adminUser.setRole(Role.ADMIN);
                adminUser.setCreatedAt(DateFormatUtility.newDate());
                userRepository.save(adminUser);
                logger.info("admin user created");
            } else {
                logger.error("Admin user already exists");
            }
        } catch (Exception e) {
            logger.error("error while creating new admin user");
        }
    }
}
