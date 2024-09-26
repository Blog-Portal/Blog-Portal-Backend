package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ReactionInDTOTest {
    private ReactionInDTO reaction1;
    private ReactionInDTO reaction2;
    private ReactionInDTO reaction3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reaction1 = new ReactionInDTO();
        reaction1.setPostId("fsdguyfshdufi8232");
        reaction1.setUserId("grfeghxc543556fg3");
        reaction1.setCurrentReaction(true);
        reaction2 = new ReactionInDTO();
        reaction2.setPostId("fsdguyfshdufi8232");
        reaction2.setUserId("grfeghxc543556fg3");
        reaction2.setCurrentReaction(true);
    }

    @Test
    void testGetterSetter() {
        assertEquals("fsdguyfshdufi8232", reaction1.getPostId());
        assertEquals("grfeghxc543556fg3", reaction1.getUserId());
        assertEquals(true, reaction1.isCurrentReaction());
    }

    @Test
    void testToString() {
        String expected = "ReactionInDTO [userId=grfeghxc543556fg3, postId=fsdguyfshdufi8232, currentReaction=true]";
        assertEquals(expected, reaction1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(reaction1, reaction1);
        assertEquals(reaction1.hashCode(), reaction1.hashCode());
        assertNotEquals(reaction1, new Object());
        assertEquals(reaction1, reaction2);
        assertEquals(reaction1.hashCode(), reaction2.hashCode());
        reaction2.setPostId("gdgfghrtytry54654");
        reaction2.setUserId("kuyjds5434tgdfsg4");
        reaction2.setCurrentReaction(false);
        reaction3 = new ReactionInDTO();
        assertNotEquals(reaction1, reaction2);
        assertNotEquals(reaction1.hashCode(), reaction2.hashCode());
        assertFalse(reaction3.equals(null));
    }
}
