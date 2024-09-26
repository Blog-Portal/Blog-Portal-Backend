package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class LoginInDTOTest {
    private LoginInDTO user1;
    private LoginInDTO user2;
    private LoginInDTO user3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new LoginInDTO();
        user1.setEmail("XXXX@nucleusteq.com");
        user1.setPassword("Abc@12345");
        user2 = new LoginInDTO();
        user2.setEmail("XXXX@nucleusteq.com");
        user2.setPassword("Abc@12345");
    }

    @Test
    public void testGetterSetter() {
        assertEquals("XXXX@nucleusteq.com", user1.getEmail());
        assertEquals("Abc@12345", user1.getPassword());
    }

    @Test
    public void testToString() {
        String expected = "LoginInDTO [email=XXXX@nucleusteq.com, password=Abc@12345]";
        assertEquals(expected, user1.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        assertEquals(user1, user1);
        assertEquals(user1.hashCode(), user1.hashCode());
        assertNotEquals(user1, new Object());
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        user2.setEmail("YYYY@nucleusteq.com");
        user2.setPassword("Xyz@98765");
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
        user3 = new LoginInDTO();
        assertFalse(user3.equals(null));
    }
}
