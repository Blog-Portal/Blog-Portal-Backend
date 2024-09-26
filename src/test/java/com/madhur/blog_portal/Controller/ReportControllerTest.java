package com.madhur.blog_portal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.madhur.blog_portal.DTO.InDTO.ReportInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Service.ReportService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

@WebMvcTest(controllers = ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportService reportService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUpdateReportSuccess() throws Exception {
        ReportInDTO reportInDTO = new ReportInDTO();
        reportInDTO.setUserId("bdjs78348bkfdjn");
        reportInDTO.setPostId("bdjs78348bkfdjn");
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REPORT);
        when(reportService.updateReport(reportInDTO))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(put("/report/", reportInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"blog reported successfully\"}", result);
    }

    @Test
    public void testUpdateReportSuccessFailUserNotFound() throws Exception {
        ReportInDTO reportInDTO = new ReportInDTO();
        reportInDTO.setUserId("bdjs78348bkfdjn");
        reportInDTO.setPostId("bdjs78348bkfdjn");
        when(reportService.updateReport(reportInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/report/", reportInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testUpdateReportSuccessFailPostNotFound() throws Exception {
        ReportInDTO reportInDTO = new ReportInDTO();
        reportInDTO.setUserId("bdjs78348bkfdjn");
        reportInDTO.setPostId("bdjs78348bkfdjn");
        when(reportService.updateReport(reportInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/report/", reportInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }

    @Test
    public void testDeleteReportSuccess() throws Exception {
        String postId = "bdjs78348bkfdjn";
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.DELETE_REPORT);
        when(reportService.deleteReport(postId)).thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(delete("/report/delete/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"report deleted successfully\"}", result);
    }

    @Test
    public void testDeleteReportFailUserNotFound() throws Exception {
        String postId = "bdjs78348bkfdjn";
        when(reportService.deleteReport(postId)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(delete("/report/delete/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }
}
