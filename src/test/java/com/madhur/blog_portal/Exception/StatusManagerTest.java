package com.madhur.blog_portal.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class StatusManagerTest {
    @Autowired
    StatusManager errorStatusManager;

    @BeforeEach
    public void setUp() {
        errorStatusManager = new StatusManager();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetterSetter() {
        errorStatusManager.setStatus(HttpStatus.BAD_REQUEST);
        HttpStatus status = errorStatusManager.getStatus();
        assertEquals(HttpStatus.BAD_REQUEST, status);
        errorStatusManager.setMessage("YYYYY");
        Object message = errorStatusManager.getMessage();
        assertEquals("YYYYY", message);
    }
}
