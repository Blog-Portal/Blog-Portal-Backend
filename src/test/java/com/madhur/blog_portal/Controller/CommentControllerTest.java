package com.madhur.blog_portal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhur.blog_portal.DTO.InDTO.CommentInDTO;
import com.madhur.blog_portal.DTO.OutDTO.CommentReportOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Service.CommentService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

@WebMvcTest(controllers = CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCommentsByPostIdSuccess() throws Exception {
        String postId = "fgvq34729tfv";
        CommentReportOutDTO commentOutDTO = new CommentReportOutDTO();
        commentOutDTO.setId("bdjs78348bkfdjn");
        commentOutDTO.setFirstName("Name");
        commentOutDTO.setLastName("Surname");
        commentOutDTO.setBody("Body");
        List<CommentReportOutDTO> commentList = new ArrayList<>();
        commentList.add(commentOutDTO);
        when(commentService.getCommentsByPostId(postId))
                .thenReturn(commentList);
        MvcResult mvc = mockMvc
                .perform(get("/comment/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "[{\"id\":\"bdjs78348bkfdjn\",\"firstName\":\"Name\",\"lastName\":\"Surname\",\"body\":\"Body\"}]",
                result);
    }

    @Test
    public void testGetAllCommentsByPostIdFail() throws Exception {
        String postId = "fgvq34729tfv";
        when(commentService.getCommentsByPostId(postId)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(get("/comment/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }

    @Test
    public void testAddCommentsSuccess() throws Exception {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("bdjs78348bkfdjn");
        commentInDTO.setPostId("bdjs78348bkfdjn");
        commentInDTO.setMessage("Body");
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.ADD_POST);
        when(commentService.addComments(commentInDTO))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(post("/comment/", commentInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"message\":\"post added successfully for admin review\"}",
                result);
    }

    @Test
    public void testAddCommentsFailUserNotFound() throws Exception {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("bdjs78348bkfdjn");
        commentInDTO.setPostId("bdjs78348bkfdjn");
        commentInDTO.setMessage("Body");
        when(commentService.addComments(commentInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/comment/", commentInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testAddCommentsFailPostNotFound() throws Exception {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("bdjs78348bkfdjn");
        commentInDTO.setPostId("bdjs78348bkfdjn");
        commentInDTO.setMessage("Body");
        when(commentService.addComments(commentInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/comment/", commentInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }
}
