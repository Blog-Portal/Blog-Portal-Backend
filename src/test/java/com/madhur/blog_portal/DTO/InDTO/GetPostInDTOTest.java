package com.madhur.blog_portal.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.Utilities.Technology;

public class GetPostInDTOTest {
    private GetPostInDTO getPostInDTO1;
    private GetPostInDTO getPostInDTO2;
    private GetPostInDTO getPostInDTO3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getPostInDTO1 = new GetPostInDTO();
        getPostInDTO1.setUserId("fdusgyjb673824");
        getPostInDTO1.setHeading("heading");
        getPostInDTO1.setTechnology(Technology.CSS);
        getPostInDTO2 = new GetPostInDTO();
        getPostInDTO2.setUserId("fdusgyjb673824");
        getPostInDTO2.setHeading("heading");
        getPostInDTO2.setTechnology(Technology.CSS);
    }

    @Test
    void testGetterSetter() {
        assertEquals("heading", getPostInDTO1.getHeading());
        assertEquals("fdusgyjb673824", getPostInDTO1.getUserId());
        assertEquals(Technology.CSS, getPostInDTO1.getTechnology());
    }

    @Test
    void testToString() {
        String expected = "GetPostInDTO [userId=fdusgyjb673824, technology=CSS, heading=heading]";
        assertEquals(expected, getPostInDTO1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(getPostInDTO1, getPostInDTO1);
        assertEquals(getPostInDTO1.hashCode(), getPostInDTO1.hashCode());
        assertNotEquals(getPostInDTO1, new Object());
        assertEquals(getPostInDTO1, getPostInDTO2);
        assertEquals(getPostInDTO1.hashCode(), getPostInDTO2.hashCode());
        getPostInDTO2.setHeading("heading2");
        getPostInDTO2.setTechnology(Technology.PYTHON);
        getPostInDTO2.setUserId("fdusgyjb67dgsh24");
        getPostInDTO3 = new GetPostInDTO();
        assertNotEquals(getPostInDTO1, getPostInDTO2);
        assertNotEquals(getPostInDTO1.hashCode(), getPostInDTO2.hashCode());
        assertFalse(getPostInDTO3.equals(null));
    }
}
