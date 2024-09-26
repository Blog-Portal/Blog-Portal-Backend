package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Technology;

public class AddPostInDTOTest {
    private AddPostInDTO post1;
    private AddPostInDTO post2;
    private AddPostInDTO post3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        post1 = new AddPostInDTO();
        post1.setUserId("fsdguyfshdufi8232");
        post1.setHeading("XXXXXXX");
        post1.setParagraph("XXXXXXX");
        post1.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        post2 = new AddPostInDTO();
        post2.setUserId("fsdguyfshdufi8232");
        post2.setHeading("XXXXXXX");
        post2.setParagraph("XXXXXXX");
        post2.setTechnology(Technology.BUSINESS_INTELLIGENCE);
    }

    @Test
    void testGetterSetter() {
        assertEquals("fsdguyfshdufi8232", post1.getUserId());
        assertEquals("XXXXXXX", post1.getHeading());
        assertEquals("XXXXXXX", post1.getParagraph());
        assertEquals(Technology.BUSINESS_INTELLIGENCE, post1.getTechnology());
    }

    @Test
    void testToString() {
        String expected = "AddPostInDTO [userId=fsdguyfshdufi8232, technology=BUSINESS_INTELLIGENCE, heading=XXXXXXX, paragraph=XXXXXXX]";
        assertEquals(expected, post1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(post1, post1);
        assertEquals(post1.hashCode(), post1.hashCode());
        assertNotEquals(post1, new Object());
        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
        post2.setUserId("fsdguyfshdufi8232");
        post2.setHeading("xxxx");
        post2.setParagraph("hhhhh");
        post2.setTechnology(Technology.BLOCKCHAIN);
        post3 = new AddPostInDTO();
        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
        assertFalse(post3.equals(null));
    }
}
