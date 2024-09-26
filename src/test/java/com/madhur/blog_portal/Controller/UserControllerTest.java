package com.madhur.blog_portal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhur.blog_portal.DTO.InDTO.LoginInDTO;
import com.madhur.blog_portal.DTO.InDTO.RegisterUserInDTO;
import com.madhur.blog_portal.DTO.OutDTO.LoginUserOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.InvalidCredentialsException;
import com.madhur.blog_portal.Exception.RecordAlreadyExistException;
import com.madhur.blog_portal.Service.UserService;
import com.madhur.blog_portal.Utilities.ConstantMessages;
import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.PasswordEncryption;
import com.madhur.blog_portal.Utilities.Role;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testResisterUserSuccess() throws Exception {
        RegisterUserInDTO userInDto = new RegisterUserInDTO();
        userInDto.setFirstName("XXXXX");
        userInDto.setLastName("YYYYY");
        userInDto.setEmail("QWERTY@nucleusteq.com");
        userInDto.setPassword(
                PasswordEncryption.getEncryptedPassword("KKKKKKK"));
        userInDto.setMobile("9000000000");
        userInDto.setRole(Role.ADMIN);
        userInDto.setDesignation(Designation.DELIVERY_HEAD);
        userInDto.setGender(Gender.MALE);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.REGISTER_USER);
        when(userService.registerUser(any(RegisterUserInDTO.class)))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"user register successfully\"}", result);
    }

    @Test
    public void testResisterUserFail() throws Exception {
        RegisterUserInDTO userInDto = new RegisterUserInDTO();
        userInDto.setFirstName("XXXXX");
        userInDto.setLastName("YYYYY");
        userInDto.setEmail("QWERTY@nucleusteq.com");
        userInDto
                .setPassword(PasswordEncryption.getEncryptedPassword("KKKKKK"));
        userInDto.setMobile("9000000000");
        userInDto.setRole(Role.ADMIN);
        userInDto.setDesignation(Designation.DELIVERY_HEAD);
        userInDto.setGender(Gender.MALE);
        when(userService.registerUser(userInDto))
                .thenThrow(new RecordAlreadyExistException(
                        ConstantMessages.USER_ALREADY_EXIST));
        MvcResult mvc = mockMvc
                .perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"CONFLICT\",\"message\":\"user already exist\"}",
                result);
    }

    @Test
    public void testResisterUserFailWrongEmail() throws Exception {
        RegisterUserInDTO userInDto = new RegisterUserInDTO();
        userInDto.setFirstName("XXXXX");
        userInDto.setLastName("YYYYY");
        userInDto.setEmail("QWERTY@gmail.com");
        userInDto
                .setPassword(PasswordEncryption.getEncryptedPassword("KKKKKK"));
        userInDto.setMobile("9000000000");
        userInDto.setRole(Role.ADMIN);
        userInDto.setDesignation(Designation.DELIVERY_HEAD);
        userInDto.setGender(Gender.MALE);
        MvcResult mvc = mockMvc
                .perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"BAD_REQUEST\",\"message\":[{\"email\":\"email must ends with @nucleusteq.com\"}]}",
                result);
    }

    @Test
    public void testLoginUserSuccess() throws Exception {
        LoginInDTO authRequest = new LoginInDTO();
        authRequest.setEmail("QWERTY@nucleusteq.com");
        authRequest
                .setPassword(PasswordEncryption.getEncryptedPassword("KKKKKK"));
        LoginUserOutDTO user = new LoginUserOutDTO();
        user.setUserId("fdsfjsduf432432uhsd");
        user.setFirstName("XXXX");
        user.setRole(Role.EMPLOYEE);
        when(userService.loginUser(authRequest)).thenReturn(user);
        MvcResult mvc = mockMvc
                .perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String actual = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"userId\":\"fdsfjsduf432432uhsd\",\"firstName\":\"XXXX\",\"role\":\"EMPLOYEE\"}",
                actual);
    }

    @Test
    public void testLoginUserFail() throws Exception {
        LoginInDTO authRequest = new LoginInDTO();
        authRequest.setEmail("QWERTY@nucleusteq.com");
        authRequest
                .setPassword(PasswordEncryption.getEncryptedPassword("KKKKKK"));
        when(userService.loginUser(authRequest))
                .thenThrow(new InvalidCredentialsException(
                        ConstantMessages.INVALID_CREDENTIALS));
        MvcResult mvc = mockMvc
                .perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();
        String actual = mvc.getResponse().getContentAsString();
        System.out.println(actual);
        assertEquals(
                "{\"status\":\"UNAUTHORIZED\",\"message\":\"invalid credentials\"}",
                actual);
    }
}
