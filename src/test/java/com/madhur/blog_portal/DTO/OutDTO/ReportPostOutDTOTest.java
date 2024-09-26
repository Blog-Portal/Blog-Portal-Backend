package com.madhur.blog_portal.DTO.OutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ReportPostOutDTOTest {
    private ReportedPostOutDTO post1;
    private ReportedPostOutDTO post2;
    private ReportedPostOutDTO post3;
    private UserPostOutDTO userPostOutDTO;
    private UserPostOutDTO userPostOutDTO2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        post1 = new ReportedPostOutDTO();
        userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setPostId("efdjsbhsdfsfsfwew");
        userPostOutDTO.setHeading("Post 1");
        userPostOutDTO.setParagraph("Content of Post 1");
        userPostOutDTO.setUpdatedAt("26-08-2023 17:27:19");
        userPostOutDTO.setFirstName("XXXXXXX");
        userPostOutDTO.setLastName("XXXXXXX");
        userPostOutDTO.setDesignation("Intern");
        userPostOutDTO.setTechnology("Java");
        post1.setUserPostOutDTO(userPostOutDTO);
        post1.setReportCount(1);
        post2 = new ReportedPostOutDTO();
        userPostOutDTO2 = new UserPostOutDTO();
        userPostOutDTO2.setPostId("efdjsbhsdfsfsfwew");
        userPostOutDTO2.setHeading("Post 1");
        userPostOutDTO2.setParagraph("Content of Post 1");
        userPostOutDTO2.setUpdatedAt("26-08-2023 17:27:19");
        userPostOutDTO2.setFirstName("XXXXXXX");
        userPostOutDTO2.setLastName("XXXXXXX");
        userPostOutDTO2.setDesignation("Intern");
        userPostOutDTO2.setTechnology("Java");
        post2.setUserPostOutDTO(userPostOutDTO2);
        post2.setReportCount(1);
    }

    @Test
    void testGetterSetter() {
        assertEquals(userPostOutDTO, post1.getUserPostOutDTO());
        assertEquals(1, post1.getReportCount());
    }

    @Test
    void testToString() {
        String expected = "ReportedPostOutDTO [userPostOutDTO=UserPostOutDTO [postId=efdjsbhsdfsfsfwew, heading=Post 1, paragraph=Content of Post 1, technology=Java, updatedAt=26-08-2023 17:27:19, firstName=XXXXXXX, lastName=XXXXXXX, designation=Intern], reportCount=1]";
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
        post2.setReportCount(2);
        post3 = new ReportedPostOutDTO();
        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
        assertFalse(post3.equals(null));
    }
}
