package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Role;

public class LoginUserOutDTOTest {
    private LoginUserOutDTO user1;
    private LoginUserOutDTO user2;
    private LoginUserOutDTO user3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new LoginUserOutDTO();
        user1.setUserId("452763yrhdfsdjbgfyu");
        user1.setFirstName("XXXXX");
        user1.setRole(Role.EMPLOYEE);
        user2 = new LoginUserOutDTO();
        user2.setUserId("452763yrhdfsdjbgfyu");
        user2.setFirstName("XXXXX");
        user2.setRole(Role.EMPLOYEE);
    }

    @Test
    public void testGetterSetter() {
        assertEquals("452763yrhdfsdjbgfyu", user1.getUserId());
        assertEquals("XXXXX", user1.getFirstName());
        assertEquals(Role.EMPLOYEE, user1.getRole());
    }

    @Test
    public void testToString() {
        String expected = "LoginUserOutDTO [userId=452763yrhdfsdjbgfyu, firstName=XXXXX, role=EMPLOYEE]";
        assertEquals(expected, user1.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        assertEquals(user1, user1);
        assertEquals(user1.hashCode(), user1.hashCode());
        assertNotEquals(user1, new Object());
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        user2.setUserId("fdsfjsduf432432uhsdI");
        user2.setFirstName("XXXXX");
        user2.setRole(Role.ADMIN);
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
        user3 = new LoginUserOutDTO();
        assertFalse(user3.equals(null));
    }
}
