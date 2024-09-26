package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class UserPostOutDTOTest {
    private UserPostOutDTO post1;
    private UserPostOutDTO post2;
    private UserPostOutDTO post3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        post1 = new UserPostOutDTO();
        post1.setPostId("efdjsbhsdfsfsfwew");
        post1.setHeading("Post 1");
        post1.setParagraph("Content of Post 1");
        post1.setUpdatedAt("26-08-2023 17:27:19");
        post1.setFirstName("XXXXXXX");
        post1.setLastName("XXXXXXX");
        post1.setDesignation("Intern");
        post1.setTechnology("Java");
        post2 = new UserPostOutDTO();
        post2.setPostId("efdjsbhsdfsfsfwew");
        post2.setHeading("Post 1");
        post2.setParagraph("Content of Post 1");
        post2.setUpdatedAt("26-08-2023 17:27:19");
        post2.setFirstName("XXXXXXX");
        post2.setLastName("XXXXXXX");
        post2.setDesignation("Intern");
        post2.setTechnology("Java");
    }

    @Test
    void testGetterSetter() {
        assertEquals("efdjsbhsdfsfsfwew", post1.getPostId());
        assertEquals("Post 1", post1.getHeading());
        assertEquals("Content of Post 1", post1.getParagraph());
        assertEquals("XXXXXXX", post1.getFirstName());
        assertEquals("XXXXXXX", post1.getLastName());
        assertEquals("Intern", post1.getDesignation());
        assertEquals("Java", post1.getTechnology());
        assertEquals("26-08-2023 17:27:19", post1.getUpdatedAt());
    }

    @Test
    void testToString() {
        String expected = "UserPostOutDTO [postId=efdjsbhsdfsfsfwew, heading=Post 1, paragraph=Content of Post 1, technology=Java, updatedAt=26-08-2023 17:27:19, firstName=XXXXXXX, lastName=XXXXXXX, designation=Intern]";
        String actual = post1.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(post1, post1);
        assertEquals(post1.hashCode(), post1.hashCode());
        assertNotEquals(post1, new Object());
        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
        post2.setPostId("2");
        post2.setHeading("Post 2");
        post2.setParagraph("Content of Post 2");
        post2.setUpdatedAt("2023-09-14");
        post2.setFirstName("Jane");
        post2.setLastName("Smith");
        post2.setDesignation("Editor");
        post2.setTechnology("Python");
        post3 = new UserPostOutDTO();
        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
        assertFalse(post3.equals(null));
    }
}
