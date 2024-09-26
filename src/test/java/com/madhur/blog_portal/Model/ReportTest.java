package com.madhur.blog_portal.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Role;
import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

public class ReportTest {
    @Autowired
    private Report report1;
    private Report report2;
    private Report report3;
    private Post post1;
    private Post post2;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User();
        user1.setUserId("fdsfjsduf432432uhsd");
        user1.setFirstName("XXXXXX");
        user1.setLastName("YYYYY");
        user1.setEmail("XYZ@nucleusteq.com");
        user1.setPassword("abc@12345");
        user1.setMobile("9000000000");
        user1.setDesignation(Designation.ARCHITECT);
        user1.setGender(Gender.MALE);
        user1.setRole(Role.ADMIN);
        user1.setCreatedAt("createdAt=26-08-2023 07:27:19");
        post1 = new Post();
        post1.setPostId("efdjsbhsdfsfsfwew");
        post1.setHeading("Post 1");
        post1.setParagraph("Content of Post 1");
        post1.setCreatedAt("26-08-2023 07:27:19");
        post1.setUpdatedAt("26-08-2023 17:27:19");
        post1.setStatus(Status.APPROVED);
        post1.setTechnology(Technology.DATA_SCIENCE);
        post1.setUser(user1);
        report1 = new Report();
        report1.setReportId("fsdguyfshdufi8232");
        report1.setPost(post1);
        report1.setUser(user1);
        user2 = new User();
        user2.setUserId("fdsfjsduf432432uhsd");
        user2.setFirstName("XXXXXX");
        user2.setLastName("YYYYY");
        user2.setEmail("XYZ@nucleusteq.com");
        user2.setPassword("abc@12345");
        user2.setMobile("9000000000");
        user2.setDesignation(Designation.ARCHITECT);
        user2.setGender(Gender.MALE);
        user2.setRole(Role.ADMIN);
        user2.setCreatedAt("createdAt=26-08-2023 07:27:19");
        post2 = new Post();
        post2.setPostId("efdjsbhsdfsfsfwew");
        post2.setHeading("Post 1");
        post2.setParagraph("Content of Post 1");
        post2.setCreatedAt("26-08-2023 07:27:19");
        post2.setUpdatedAt("26-08-2023 17:27:19");
        post2.setStatus(Status.APPROVED);
        post2.setTechnology(Technology.DATA_SCIENCE);
        post2.setUser(user2);
        report2 = new Report();
        report2.setReportId("fsdguyfshdufi8232");
        report2.setPost(post1);
        report2.setUser(user1);
    }

    @Test
    void testGetterSetter() {
        assertEquals("fsdguyfshdufi8232", report1.getReportId());
        assertEquals(post1, report1.getPost());
        assertEquals(user1, report1.getUser());
    }

    @Test
    void testToString() {
        String expected = "Report [reportId=fsdguyfshdufi8232, user=User [userId=fdsfjsduf432432uhsd, firstName=XXXXXX, lastName=YYYYY, email=XYZ@nucleusteq.com, password=abc@12345, mobile=9000000000, designation=ARCHITECT, gender=MALE, role=ADMIN, createdAt=createdAt=26-08-2023 07:27:19], post=Post [postId=efdjsbhsdfsfsfwew, user=User [userId=fdsfjsduf432432uhsd, firstName=XXXXXX, lastName=YYYYY, email=XYZ@nucleusteq.com, password=abc@12345, mobile=9000000000, designation=ARCHITECT, gender=MALE, role=ADMIN, createdAt=createdAt=26-08-2023 07:27:19], technology=DATA_SCIENCE, heading=Post 1, paragraph=Content of Post 1, createdAt=26-08-2023 07:27:19, updatedAt=26-08-2023 17:27:19, status=APPROVED]]";
        assertEquals(expected, report1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(report1, report1);
        assertEquals(report1.hashCode(), report1.hashCode());
        assertNotEquals(report1, new Object());
        assertEquals(report1, report2);
        assertEquals(report1.hashCode(), report2.hashCode());
        user2.setUserId("fdsj3jkb2jh54687");
        user2.setFirstName("SDGSDSD");
        user2.setLastName("GSDFGSDG");
        user2.setEmail("ASFDS@nucleusteq.com");
        user2.setPassword("GDSGsd@12345");
        user2.setMobile("1000000000");
        user2.setDesignation(Designation.DELIVERY_HEAD);
        user2.setGender(Gender.OTHER);
        user2.setRole(Role.EMPLOYEE);
        post2.setPostId("2");
        post2.setHeading("Post 2");
        post2.setParagraph("Content of Post 2");
        post2.setCreatedAt("2023-09-13");
        post2.setUpdatedAt("2023-09-14");
        post2.setPostId("jhfguytfuu564786");
        post2.setStatus(Status.PENDING);
        post2.setTechnology(Technology.DATA_ENGINEERING);
        post2.setUser(user2);
        report2.setReportId("hgsdjhuy7328u23");
        report2.setPost(post2);
        report2.setUser(user2);
        assertNotEquals(report1, report2);
        assertNotEquals(report1.hashCode(), report2.hashCode());
        report3 = new Report();
        assertFalse(report3.equals(null));
    }
}
