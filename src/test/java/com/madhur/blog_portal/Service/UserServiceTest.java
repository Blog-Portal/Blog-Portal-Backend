package com.madhur.blog_portal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.madhur.blog_portal.DTO.InDTO.LoginInDTO;
import com.madhur.blog_portal.DTO.InDTO.RegisterUserInDTO;
import com.madhur.blog_portal.DTO.OutDTO.LoginUserOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.InvalidCredentialsException;
import com.madhur.blog_portal.Exception.RecordAlreadyExistException;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.ServiceImpl.UserServiceImpl;
import com.madhur.blog_portal.Utilities.ConstantMessages;
import com.madhur.blog_portal.Utilities.DateFormatUtility;
import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Role;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void registerUserTest() {
        RegisterUserInDTO userDTO = new RegisterUserInDTO();
        userDTO.setFirstName("XXXXX");
        userDTO.setLastName("XXXXX");
        userDTO.setEmail("xxxxx@nucleusteq.com");
        userDTO.setPassword("XXXXX");
        userDTO.setMobile("9000000000");
        userDTO.setGender(Gender.MALE);
        userDTO.setDesignation(Designation.OTHER);
        userDTO.setRole(Role.EMPLOYEE);
        when(userRepository.existsByEmailIgnoreCaseOrMobile(
                userDTO.getEmail(), userDTO.getMobile())).thenReturn(false);
        User user = new User();
        user.setFirstName(user.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDesignation(userDTO.getDesignation());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setGender(userDTO.getGender());
        user.setRole(userDTO.getRole());
        user.setCreatedAt(DateFormatUtility.newDate());
        when(userRepository.save(user)).thenReturn(user);
        ResponseOutDTO result = userServiceImpl.registerUser(userDTO);
        assertEquals(ConstantMessages.REGISTER_USER, result.getMessage());
    }

    @Test
    public void registerUserTestFail() {
        RegisterUserInDTO userDTO = new RegisterUserInDTO();
        userDTO.setFirstName("XXXXX");
        userDTO.setLastName("XXXXX");
        userDTO.setEmail("xxxxx@nucleusteq.com");
        userDTO.setPassword("XXXXX");
        userDTO.setMobile("9000000000");
        userDTO.setGender(Gender.MALE);
        userDTO.setDesignation(Designation.OTHER);
        userDTO.setRole(Role.EMPLOYEE);
        when(userRepository.existsByEmailIgnoreCaseOrMobile(
                userDTO.getEmail(), userDTO.getMobile())).thenReturn(true);
        RecordAlreadyExistException result = assertThrows(
                RecordAlreadyExistException.class, () -> {
                    userServiceImpl.registerUser(userDTO);
                });
        assertEquals(ConstantMessages.USER_ALREADY_EXIST, result.getMessage());
    }

    @Test
    public void testUserLogin() {
        LoginInDTO loginInDTO = new LoginInDTO();
        loginInDTO.setEmail("xyz@nucleusteq.com");
        loginInDTO.setPassword("Abc12345");
        User user = new User();
        user.setFirstName("XXXXX");
        user.setLastName("XXXXX");
        user.setEmail("xyz@nucleusteq.com");
        user.setPassword("Abc12345");
        user.setMobile("9000000000");
        user.setGender(Gender.MALE);
        user.setDesignation(Designation.OTHER);
        user.setRole(Role.EMPLOYEE);
        when(userRepository.getByEmailIgnoreCaseAndPassword(
                loginInDTO.getEmail(), loginInDTO.getPassword()))
                .thenReturn(user);
        LoginUserOutDTO userOutDTO = new LoginUserOutDTO();
        userOutDTO.setUserId(user.getUserId());
        userOutDTO.setFirstName(user.getFirstName());
        userOutDTO.setRole(user.getRole());
        LoginUserOutDTO result = userServiceImpl.loginUser(loginInDTO);
        assertEquals(userOutDTO, result);
    }

    @Test
    public void testUserLoginFail() {
        LoginInDTO auth = new LoginInDTO();
        auth.setEmail("xyz@nucleusteq.com");
        auth.setPassword("Abc12345");
        when(userRepository.getByEmailIgnoreCaseAndPassword(auth.getEmail(),
                auth.getPassword())).thenReturn(null);
        InvalidCredentialsException result = assertThrows(
                InvalidCredentialsException.class, () -> {
                    userServiceImpl.loginUser(auth);
                });
        assertEquals(ConstantMessages.INVALID_CREDENTIALS, result.getMessage());
    }
}
