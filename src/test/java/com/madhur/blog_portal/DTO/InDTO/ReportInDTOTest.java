package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ReportInDTOTest {
    private ReportInDTO report1;
    private ReportInDTO report2;
    private ReportInDTO report3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        report1 = new ReportInDTO();
        report1.setPostId("fsdguyfshdufi8232");
        report1.setUserId("grfeghxc543556fg3");
        report2 = new ReportInDTO();
        report2.setPostId("fsdguyfshdufi8232");
        report2.setUserId("grfeghxc543556fg3");
    }

    @Test
    void testGetterSetter() {
        assertEquals("fsdguyfshdufi8232", report1.getPostId());
        assertEquals("grfeghxc543556fg3", report1.getUserId());
    }

    @Test
    void testToString() {
        String expected = "ReportInDTO [userId=grfeghxc543556fg3, postId=fsdguyfshdufi8232]";
        assertEquals(expected, report1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(report1, report1);
        assertEquals(report1.hashCode(), report1.hashCode());
        assertNotEquals(report1, new Object());
        assertEquals(report1, report2);
        assertEquals(report1.hashCode(), report2.hashCode());
        report2.setPostId("gdgfghrtytry54654");
        report2.setUserId("kuyjds5434tgdfsg4");
        report3 = new ReportInDTO();
        assertNotEquals(report1, report2);
        assertNotEquals(report1.hashCode(), report2.hashCode());
        assertFalse(report3.equals(null));
    }
}
