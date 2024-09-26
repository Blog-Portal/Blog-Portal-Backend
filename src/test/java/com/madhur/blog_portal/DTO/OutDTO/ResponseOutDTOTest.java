package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ResponseOutDTOTest {
    private ResponseOutDTO message1;
    private ResponseOutDTO message2;
    private ResponseOutDTO message3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        message1 = new ResponseOutDTO();
        message1.setMessage("message");
        message2 = new ResponseOutDTO();
        message2.setMessage("message");
    }

    @Test
    public void testGetterSetter() {
        assertEquals("message", message1.getMessage());
    }

    @Test
    public void testToString() {
        String expected = "ResponseOutDTO [message=message]";
        assertEquals(expected, message1.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        assertEquals(message1, message1);
        assertEquals(message1.hashCode(), message1.hashCode());
        assertNotEquals(message1, new Object());
        assertEquals(message1, message2);
        assertEquals(message1.hashCode(), message2.hashCode());
        message2.setMessage("message2");
        assertNotEquals(message1, message2);
        assertNotEquals(message1.hashCode(), message2.hashCode());
        message3 = new ResponseOutDTO();
        assertFalse(message3.equals(null));
    }
}
