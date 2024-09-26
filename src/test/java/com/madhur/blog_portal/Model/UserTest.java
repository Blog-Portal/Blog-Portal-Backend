package com.madhur.blog_portal.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.madhur.blog_portal.Utilities.DateFormatUtility;
import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Role;

public class UserTest {
    @Autowired
    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetterSetter() {
        user.setUserId("452763yrhdfsdjbgfyu");
        String id = user.getUserId();
        assertEquals("452763yrhdfsdjbgfyu", id);
        user.setFirstName("XXXXX");
        String fname = user.getFirstName();
        assertEquals("XXXXX", fname);
        user.setLastName("YYYYY");
        String lname = user.getLastName();
        assertEquals("YYYYY", lname);
        user.setEmail("xyz@nucleusteq.com");
        String email = user.getEmail();
        assertEquals("xyz@nucleusteq.com", email);
        user.setPassword("Abc@12345");
        String password = user.getPassword();
        assertEquals("Abc@12345", password);
        user.setMobile("9000000000");
        String mobile = user.getMobile();
        assertEquals("9000000000", mobile);
        user.setRole(Role.ADMIN);
        Role role = user.getRole();
        assertEquals(Role.ADMIN, role);
        user.setGender(Gender.MALE);
        Gender gender = user.getGender();
        assertEquals(Gender.MALE, gender);
        user.setDesignation(Designation.ARCHITECT);
        Designation designation = user.getDesignation();
        assertEquals(Designation.ARCHITECT, designation);
        user.setCreatedAt(DateFormatUtility.newDate());
        String date = user.getCreatedAt();
        assertEquals(DateFormatUtility.newDate(), date);
    }

    @Test
    public void testToString() {
        String actual = "User [userId=fdsfjsduf432432uhsd, firstName=XXXXXX, lastName=YYYYY, email=XYZ@nucleusteq.com, password=abc@12345, mobile=9000000000, designation=ARCHITECT, gender=MALE, role=ADMIN, createdAt=createdAt=26-08-2023 07:27:19]";
        user.setUserId("fdsfjsduf432432uhsd");
        user.setFirstName("XXXXXX");
        user.setLastName("YYYYY");
        user.setEmail("XYZ@nucleusteq.com");
        user.setPassword("abc@12345");
        user.setMobile("9000000000");
        user.setDesignation(Designation.ARCHITECT);
        user.setGender(Gender.MALE);
        user.setRole(Role.ADMIN);
        user.setCreatedAt("createdAt=26-08-2023 07:27:19");
        assertEquals(actual, user.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        User user1 = new User();
        user1.setUserId("fdsfjsduf432432uhsd");
        user1.setFirstName("XXXXXX");
        user1.setLastName("YYYYY");
        user1.setEmail("XYZ@nucleusteq.com");
        user1.setPassword("abc@12345");
        user1.setMobile("9000000000");
        user1.setDesignation(Designation.ARCHITECT);
        user1.setGender(Gender.MALE);
        user1.setRole(Role.ADMIN);
        user1.setCreatedAt("createdAt=26-08-2023 07:27:19");
        assertEquals(user1, user1);
        assertEquals(user1.hashCode(), user1.hashCode());
        assertNotEquals(user1, new Object());
        User user2 = new User();
        user2.setUserId("fdsfjsduf432432uhsd");
        user2.setFirstName("XXXXXX");
        user2.setLastName("YYYYY");
        user2.setEmail("XYZ@nucleusteq.com");
        user2.setPassword("abc@12345");
        user2.setMobile("9000000000");
        user2.setDesignation(Designation.ARCHITECT);
        user2.setGender(Gender.MALE);
        user2.setRole(Role.ADMIN);
        user2.setCreatedAt("createdAt=26-08-2023 07:27:19");
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        user2.setUserId("fdsfjsduf432432uhsdI");
        user2.setFirstName("XXXXX");
        user2.setLastName("YYYYYY");
        user2.setEmail("XYZ@nucleusteq.com");
        user2.setPassword("xyz@12345");
        user2.setMobile("9000000009");
        user2.setDesignation(Designation.DELIVERY_HEAD);
        user2.setGender(Gender.FEMALE);
        user2.setRole(Role.EMPLOYEE);
        user2.setCreatedAt("createdAt=26-08-2023 02:17:39");
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
        User user3 = new User();
        assertFalse(user3.equals(null));
    }
}
