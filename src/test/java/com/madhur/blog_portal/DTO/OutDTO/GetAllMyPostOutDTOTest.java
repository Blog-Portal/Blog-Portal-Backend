package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Status;

public class GetAllMyPostOutDTOTest {
    private GetAllMyPostOutDTO post1;
    private GetAllMyPostOutDTO post2;
    private GetAllMyPostOutDTO post3;
    private UserPostOutDTO userPostOutDTO;
    private UserPostOutDTO userPostOutDTO2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        post1 = new GetAllMyPostOutDTO();
        userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setPostId("efdjsbhsdfsfsfwew");
        userPostOutDTO.setHeading("Post 1");
        userPostOutDTO.setParagraph("Content of Post 1");
        userPostOutDTO.setUpdatedAt("26-08-2023 17:27:19");
        userPostOutDTO.setFirstName("XXXXXXX");
        userPostOutDTO.setLastName("XXXXXXX");
        userPostOutDTO.setDesignation("INTERN");
        userPostOutDTO.setTechnology("Java");
        post1.setUserPostOutDTO(userPostOutDTO);
        post1.setStatus(Status.PENDING);
        post1.setLike(true);
        post1.setDislike(false);
        post1.setLikeCount(10);
        post1.setDislikeCount(2);
        post1.setCommentCount(5);
        post2 = new GetAllMyPostOutDTO();
        userPostOutDTO2 = new UserPostOutDTO();
        userPostOutDTO2.setPostId("efdjsbhsdfsfsfwew");
        userPostOutDTO2.setHeading("Post 1");
        userPostOutDTO2.setParagraph("Content of Post 1");
        userPostOutDTO2.setUpdatedAt("26-08-2023 17:27:19");
        userPostOutDTO2.setFirstName("XXXXXXX");
        userPostOutDTO2.setLastName("XXXXXXX");
        userPostOutDTO2.setDesignation("INTERN");
        userPostOutDTO2.setTechnology("Java");
        post2.setUserPostOutDTO(userPostOutDTO2);
        post2.setLike(true);
        post2.setDislike(false);
        post2.setLikeCount(10);
        post2.setDislikeCount(2);
        post2.setCommentCount(5);
        post2.setStatus(Status.PENDING);
    }

    @Test
    void testGetterSetter() {
        assertEquals(userPostOutDTO, post1.getUserPostOutDTO());
        assertTrue(post1.isLike());
        assertFalse(post1.isDislike());
        assertEquals(Status.PENDING, post1.getStatus());
        assertEquals(10, post1.getLikeCount());
        assertEquals(2, post1.getDislikeCount());
        assertEquals(5, post1.getCommentCount());
    }

    @Test
    void testToString() {
        String expected = "GetAllMyPostOutDTO [userPostOutDTO=UserPostOutDTO [postId=efdjsbhsdfsfsfwew, heading=Post 1, paragraph=Content of Post 1, technology=Java, updatedAt=26-08-2023 17:27:19, firstName=XXXXXXX, lastName=XXXXXXX, designation=INTERN], status=PENDING, like=true, dislike=false, likeCount=10, dislikeCount=2, commentCount=5]";
        assertEquals(expected, post1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(post1, post1);
        assertEquals(post1.hashCode(), post1.hashCode());
        assertNotEquals(post1, new Object());
        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
        userPostOutDTO2 = new UserPostOutDTO();
        userPostOutDTO2.setPostId("fbysdgu6723454fd");
        userPostOutDTO2.setHeading("Post 3");
        userPostOutDTO2.setParagraph("Content of Post 3");
        userPostOutDTO2.setUpdatedAt("16-08-2023 27:27:19");
        userPostOutDTO2.setFirstName("AAAAAAA");
        userPostOutDTO2.setLastName("BBBBBBBBB");
        userPostOutDTO2.setDesignation("HR");
        userPostOutDTO2.setTechnology("Microservice");
        post2.setUserPostOutDTO(userPostOutDTO2);
        post2.setLike(false);
        post2.setDislike(true);
        post2.setLikeCount(5);
        post2.setDislikeCount(3);
        post2.setCommentCount(8);
        post2.setStatus(Status.APPROVED);
        post3 = new GetAllMyPostOutDTO();
        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
        assertFalse(post3.equals(null));
    }
}
