package com.madhur.blog_portal.Controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.madhur.blog_portal.DTO.InDTO.LoginInDTO;
import com.madhur.blog_portal.DTO.InDTO.RegisterUserInDTO;
import com.madhur.blog_portal.DTO.OutDTO.LoginUserOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Service.UserService;

/**
 * User Controller class to handle User related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * Logger for track log reports.
     */
    private Logger logger = LogManager.getLogger(UserController.class);
    /**
     * UserService instance.
     */
    @Autowired
    private UserService userService;

    /**
     * New user can registers.
     * @param userDTO The UserInDTO containing user information.
     * @return result of the user registration.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseOutDTO registerUser(
            @Valid @RequestBody final RegisterUserInDTO userDTO) {
        logger.info("Request for register new user {}", userDTO.toString());
        ResponseOutDTO registerUser = userService.registerUser(userDTO);
        logger.info("Successfully registered new user {}", userDTO.toString());
        return registerUser;
    }

    /**
     * A user can login.
     * @param authRequest The LoginInDTO containing login credentials.
     * @return indicating the result of the user login.
     */
    @PostMapping("/login")
    public LoginUserOutDTO loginUser(
            @Valid @RequestBody final LoginInDTO authRequest) {
        logger.info("Request for login {}", authRequest.toString());
        LoginUserOutDTO loginUser = userService.loginUser(authRequest);
        logger.info("Successfully user loggedIn {}", authRequest.toString());
        return loginUser;
    }
}
