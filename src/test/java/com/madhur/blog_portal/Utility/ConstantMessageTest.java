package com.madhur.blog_portal.Utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.madhur.blog_portal.Utilities.ConstantMessages;

@SpringBootTest
public class ConstantMessageTest extends ConstantMessages {
    @Test
    public void testConstantValues() {
        assertEquals("post not found", ConstantMessages.POST_NOT_FOUND);
        assertEquals("user not found", ConstantMessages.USER_NOT_FOUND);
        assertEquals("invalid status", ConstantMessages.INVALID_STATUS);
        assertEquals("invalid credentials",
                ConstantMessages.INVALID_CREDENTIALS);
        assertEquals("user already exist", ConstantMessages.USER_ALREADY_EXIST);
        assertEquals("comment added successfully",
                ConstantMessages.COMMENT_ADDED);
        assertEquals("reaction updated successfully",
                ConstantMessages.UPDATE_REACTION);
        assertEquals("blog reported successfully",
                ConstantMessages.UPDATE_REPORT);
        assertEquals("report deleted successfully",
                ConstantMessages.DELETE_REPORT);
        assertEquals("user register successfully",
                ConstantMessages.REGISTER_USER);
        assertEquals("post added successfully for admin review",
                ConstantMessages.ADD_POST);
        assertEquals("post updated successfully for admin review",
                ConstantMessages.UPDATE_POST);
        assertEquals("post deleted successfully", ConstantMessages.DELETE_POST);
        assertEquals("status updated successfully",
                ConstantMessages.APPROVE_POST);
    }
}
