package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Technology;

public class UpdatePostInDTOTest {
    private UpdatePostOutDTO post1;
    private UpdatePostOutDTO post2;
    private UpdatePostOutDTO post3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        post1 = new UpdatePostOutDTO();
        post1.setHeading("XXXXXXX");
        post1.setParagraph("XXXXXXX");
        post1.setTechnology(Technology.CSS);
        post2 = new UpdatePostOutDTO();
        post2.setHeading("XXXXXXX");
        post2.setParagraph("XXXXXXX");
        post2.setTechnology(Technology.CSS);
    }

    @Test
    void testGetterSetter() {
        assertEquals("XXXXXXX", post1.getHeading());
        assertEquals("XXXXXXX", post1.getParagraph());
        assertEquals(Technology.CSS, post1.getTechnology());
    }

    @Test
    void testToString() {
        String expected = "UpdatePostOutDTO [heading=XXXXXXX, paragraph=XXXXXXX, technology=CSS]";
        assertEquals(expected, post1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(post1, post1);
        assertEquals(post1.hashCode(), post1.hashCode());
        assertNotEquals(post1, new Object());
        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
        post2.setHeading("xxxx");
        post2.setParagraph("hhhhh");
        post2.setTechnology(Technology.DATA_SCIENCE);
        post3 = new UpdatePostOutDTO();
        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
        assertFalse(post3.equals(null));
    }
}
