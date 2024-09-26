package com.madhur.blog_portal.Service;

import com.madhur.blog_portal.DTO.InDTO.LoginInDTO;
import com.madhur.blog_portal.DTO.InDTO.RegisterUserInDTO;
import com.madhur.blog_portal.DTO.OutDTO.LoginUserOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;

/**
 * Service interface for managing User operations.
 */
public interface UserService {
    /**
     * Registers a new user.
     * @param userDTO The user DTO instance to be registered.
     * @return A string indicating whether the user was registered successfully
     *         or not.
     */
    ResponseOutDTO registerUser(RegisterUserInDTO userDTO);
    /**
     * LogIn a user with the provided credentials.
     * @param authRequest The login credentials DTO instance.
     * @return The user DTO representing the details of loggedIn user.
     */
    LoginUserOutDTO loginUser(LoginInDTO authRequest);
}
