package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Status;

public class PostApprovalInDTOTest {
    private PostApprovalInDTO postApprovalInDTO1;
    private PostApprovalInDTO postApprovalInDTO2;
    private PostApprovalInDTO postApprovalInDTO3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postApprovalInDTO1 = new PostApprovalInDTO();
        postApprovalInDTO1.setPostId("fdshuihusd77we43");
        postApprovalInDTO1.setStatus(Status.APPROVED);
        postApprovalInDTO2 = new PostApprovalInDTO();
        postApprovalInDTO2.setPostId("fdshuihusd77we43");
        postApprovalInDTO2.setStatus(Status.APPROVED);
    }

    @Test
    void testGetterSetter() {
        assertEquals("fdshuihusd77we43", postApprovalInDTO1.getPostId());
        assertEquals(Status.APPROVED, postApprovalInDTO1.getStatus());
    }

    @Test
    void testToString() {
        String expected = "PostApprovalInDTO [postId=fdshuihusd77we43, status=APPROVED]";
        assertEquals(expected, postApprovalInDTO1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(postApprovalInDTO1, postApprovalInDTO1);
        assertEquals(postApprovalInDTO1.hashCode(),
                postApprovalInDTO1.hashCode());
        assertNotEquals(postApprovalInDTO1, new Object());
        assertEquals(postApprovalInDTO1, postApprovalInDTO2);
        assertEquals(postApprovalInDTO1.hashCode(),
                postApprovalInDTO2.hashCode());
        postApprovalInDTO2.setPostId("regdfhrfdh5464");
        postApprovalInDTO2.setStatus(Status.PENDING);
        postApprovalInDTO3 = new PostApprovalInDTO();
        assertNotEquals(postApprovalInDTO1, postApprovalInDTO2);
        assertNotEquals(postApprovalInDTO1.hashCode(),
                postApprovalInDTO2.hashCode());
        assertFalse(postApprovalInDTO3.equals(null));
    }
}
