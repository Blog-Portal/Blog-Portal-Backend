package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

public class MyPostInDTOTest {
    private MyPostInDTO getPostInDTO1;
    private MyPostInDTO getPostInDTO2;
    private MyPostInDTO getPostInDTO3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getPostInDTO1 = new MyPostInDTO();
        getPostInDTO1.setUserId("fdshuihusd77we43");
        getPostInDTO1.setStatus(Status.APPROVED);
        getPostInDTO1.setTechnology(Technology.BLOCKCHAIN);
        getPostInDTO1.setHeading("DDDDDD");
        getPostInDTO2 = new MyPostInDTO();
        getPostInDTO2.setUserId("fdshuihusd77we43");
        getPostInDTO2.setStatus(Status.APPROVED);
        getPostInDTO2.setTechnology(Technology.BLOCKCHAIN);
        getPostInDTO2.setHeading("DDDDDD");
    }

    @Test
    void testGetterSetter() {
        assertEquals("fdshuihusd77we43", getPostInDTO1.getUserId());
        assertEquals(Status.APPROVED, getPostInDTO1.getStatus());
        assertEquals("DDDDDD", getPostInDTO1.getHeading());
        assertEquals(Technology.BLOCKCHAIN, getPostInDTO1.getTechnology());
    }

    @Test
    void testToString() {
        String expected = "MyPostInDTO [userId=fdshuihusd77we43, status=APPROVED, technology=BLOCKCHAIN, heading=DDDDDD]";
        assertEquals(expected, getPostInDTO1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(getPostInDTO1, getPostInDTO1);
        assertEquals(getPostInDTO1.hashCode(), getPostInDTO1.hashCode());
        assertNotEquals(getPostInDTO1, new Object());
        assertEquals(getPostInDTO1, getPostInDTO2);
        assertEquals(getPostInDTO1.hashCode(), getPostInDTO2.hashCode());
        getPostInDTO2.setUserId("regdfhrfdh5464");
        getPostInDTO2.setStatus(Status.PENDING);
        getPostInDTO3 = new MyPostInDTO();
        getPostInDTO2.setTechnology(Technology.DATA_ENGINEERING);
        getPostInDTO2.setHeading("EEEEEEE");
        assertNotEquals(getPostInDTO1, getPostInDTO2);
        assertNotEquals(getPostInDTO1.hashCode(), getPostInDTO2.hashCode());
        assertFalse(getPostInDTO3.equals(null));
    }
}
