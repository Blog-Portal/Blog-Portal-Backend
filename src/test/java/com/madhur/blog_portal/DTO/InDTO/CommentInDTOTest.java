package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class CommentInDTOTest {
    private CommentInDTO comment1;
    private CommentInDTO comment2;
    private CommentInDTO comment3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        comment1 = new CommentInDTO();
        comment1.setPostId("fsdguyfshdufi8232");
        comment1.setUserId("grfeghxc543556fg3");
        comment1.setMessage("XXXXXXX");
        comment2 = new CommentInDTO();
        comment2.setPostId("fsdguyfshdufi8232");
        comment2.setUserId("grfeghxc543556fg3");
        comment2.setMessage("XXXXXXX");
    }

    @Test
    void testGetterSetter() {
        assertEquals("fsdguyfshdufi8232", comment1.getPostId());
        assertEquals("grfeghxc543556fg3", comment1.getUserId());
        assertEquals("XXXXXXX", comment1.getMessage());
    }

    @Test
    void testToString() {
        String expected = "CommentInDTO [userId=grfeghxc543556fg3, postId=fsdguyfshdufi8232, message=XXXXXXX]";
        assertEquals(expected, comment1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(comment1, comment1);
        assertEquals(comment1.hashCode(), comment1.hashCode());
        assertNotEquals(comment1, new Object());
        assertEquals(comment1, comment2);
        assertEquals(comment1.hashCode(), comment2.hashCode());
        comment2.setPostId("gdgfghrtytry54654");
        comment2.setUserId("kuyjds5434tgdfsg4");
        comment2.setMessage("WWWWWWWW");
        comment3 = new CommentInDTO();
        assertNotEquals(comment1, comment2);
        assertNotEquals(comment1.hashCode(), comment2.hashCode());
        assertFalse(comment3.equals(null));
    }
}
