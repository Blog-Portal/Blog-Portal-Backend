package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class CommentReportOutDTOTest {
    private CommentReportOutDTO comment1;
    private CommentReportOutDTO comment2;
    private CommentReportOutDTO comment3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        comment1 = new CommentReportOutDTO();
        comment1.setId("efdjsbhsdfsfsfwew");
        comment1.setFirstName("XXXXXXX");
        comment1.setLastName("XXXXXXX");
        comment1.setBody("Body");
        comment2 = new CommentReportOutDTO();
        comment2.setId("efdjsbhsdfsfsfwew");
        comment2.setFirstName("XXXXXXX");
        comment2.setLastName("XXXXXXX");
        comment2.setBody("Body");
    }

    @Test
    void testGetterSetter() {
        assertEquals("efdjsbhsdfsfsfwew", comment1.getId());
        assertEquals("XXXXXXX", comment1.getFirstName());
        assertEquals("XXXXXXX", comment1.getLastName());
        assertEquals("Body", comment1.getBody());
    }

    @Test
    void testToString() {
        String expected = "CommentReportOutDTO [id=efdjsbhsdfsfsfwew, firstName=XXXXXXX, lastName=XXXXXXX, body=Body]";
        assertEquals(expected, comment1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(comment1, comment1);
        assertEquals(comment1.hashCode(), comment1.hashCode());
        assertNotEquals(comment1, new Object());
        assertEquals(comment1, comment2);
        assertEquals(comment1.hashCode(), comment2.hashCode());
        comment2.setId("fdgsd345gfdgd");
        comment2.setFirstName("xxxx");
        comment2.setLastName("hhhhh");
        comment2.setBody("Body2");
        comment3 = new CommentReportOutDTO();
        assertNotEquals(comment1, comment2);
        assertNotEquals(comment1.hashCode(), comment2.hashCode());
        assertFalse(comment3.equals(null));
    }
}
