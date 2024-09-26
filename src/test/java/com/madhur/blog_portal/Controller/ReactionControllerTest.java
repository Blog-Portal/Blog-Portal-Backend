package com.madhur.blog_portal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhur.blog_portal.DTO.InDTO.ReactionInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Service.ReactionService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

@WebMvcTest(controllers = ReactionController.class)
public class ReactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReactionService reactionService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUpdateReactionSuccess() throws Exception {
        ReactionInDTO reactionInDTO = new ReactionInDTO();
        reactionInDTO.setUserId("bdjs78348bkfdjn");
        reactionInDTO.setPostId("bdjs78348bkfdjn");
        reactionInDTO.setCurrentReaction(false);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REACTION);
        when(reactionService.updateReaction(reactionInDTO))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(put("/reaction/", reactionInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reactionInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"reaction updated successfully\"}", result);
    }

    @Test
    public void testUpdateReactionSuccessFailUserNotFound() throws Exception {
        ReactionInDTO reactionInDTO = new ReactionInDTO();
        reactionInDTO.setUserId("bdjs78348bkfdjn");
        reactionInDTO.setPostId("bdjs78348bkfdjn");
        reactionInDTO.setCurrentReaction(false);
        when(reactionService.updateReaction(reactionInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/reaction/", reactionInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reactionInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testUpdateReactionSuccessFailPostNotFound() throws Exception {
        ReactionInDTO reactionInDTO = new ReactionInDTO();
        reactionInDTO.setUserId("bdjs78348bkfdjn");
        reactionInDTO.setPostId("bdjs78348bkfdjn");
        reactionInDTO.setCurrentReaction(false);
        when(reactionService.updateReaction(reactionInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/reaction/", reactionInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reactionInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }
}
