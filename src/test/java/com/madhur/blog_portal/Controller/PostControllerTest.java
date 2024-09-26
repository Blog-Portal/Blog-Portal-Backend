package com.madhur.blog_portal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.madhur.blog_portal.DTO.InDTO.AddPostInDTO;
import com.madhur.blog_portal.DTO.InDTO.GetPostInDTO;
import com.madhur.blog_portal.DTO.InDTO.MyPostInDTO;
import com.madhur.blog_portal.DTO.InDTO.PostApprovalInDTO;
import com.madhur.blog_portal.DTO.InDTO.UpdatePostInDTO;
import com.madhur.blog_portal.DTO.OutDTO.GetAllMyPostOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.GetAllPostOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ReportedPostOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.UpdatePostOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.UserPostOutDTO;
import com.madhur.blog_portal.Exception.InvalidRecordException;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Service.PostService;
import com.madhur.blog_portal.Utilities.ConstantMessages;
import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllApprovePostsSucess() throws Exception {
        GetPostInDTO getPostInDTO = new GetPostInDTO();
        getPostInDTO.setUserId("nsdjgh2374grfsdg");
        getPostInDTO.setHeading("Heading");
        getPostInDTO.setTechnology(Technology.CSS);
        GetAllPostOutDTO postOutDTO = new GetAllPostOutDTO();
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName("Name");
        userPostOutDTO.setLastName("Surname");
        userPostOutDTO.setDesignation("HR");
        userPostOutDTO.setHeading("Heading");
        userPostOutDTO.setParagraph("Paragraph");
        userPostOutDTO.setTechnology("Java");
        userPostOutDTO.setPostId("dsfhg734fsd");
        userPostOutDTO.setUpdatedAt("07-09-2023 13:24:59");
        postOutDTO.setUserPostOutDTO(userPostOutDTO);
        postOutDTO.setLike(true);
        postOutDTO.setDislike(false);
        postOutDTO.setReport(true);
        postOutDTO.setLikeCount(10);
        postOutDTO.setDislikeCount(5);
        postOutDTO.setReportCount(3);
        postOutDTO.setCommentCount(15);
        postOutDTO.setMyPost(true);
        List<GetAllPostOutDTO> approvedPostList = new ArrayList<>();
        approvedPostList.add(postOutDTO);
        when(postService.getAllApprovePosts(getPostInDTO))
                .thenReturn(approvedPostList);
        MvcResult mvc = mockMvc
                .perform(post("/post/approve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "[{\"userPostOutDTO\":{\"postId\":\"dsfhg734fsd\",\"heading\":\"Heading\",\"paragraph\":\"Paragraph\",\"technology\":\"Java\",\"updatedAt\":\"07-09-2023 13:24:59\",\"firstName\":\"Name\",\"lastName\":\"Surname\",\"designation\":\"HR\"},\"like\":true,\"dislike\":false,\"report\":true,\"likeCount\":10,\"dislikeCount\":5,\"reportCount\":3,\"commentCount\":15,\"myPost\":true}]",
                result);
    }

    @Test
    public void testGetAllApprovePostsFail() throws Exception {
        GetPostInDTO getPostInDTO = new GetPostInDTO();
        getPostInDTO.setUserId("nsdjgh2374grfsdg");
        getPostInDTO.setHeading("Heading");
        getPostInDTO.setTechnology(Technology.CSS);
        when(postService.getAllApprovePosts(getPostInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/post/approve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testGetAllUnapprovePostsSucess() throws Exception {
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName("Name");
        userPostOutDTO.setLastName("Surname");
        userPostOutDTO.setDesignation("HR");
        userPostOutDTO.setHeading("Heading");
        userPostOutDTO.setParagraph("Paragraph");
        userPostOutDTO.setTechnology("Java");
        userPostOutDTO.setPostId("dsfhg734fsd");
        userPostOutDTO.setUpdatedAt("07-09-2023 13:24:59");
        List<UserPostOutDTO> unApprovedPostList = new ArrayList<>();
        unApprovedPostList.add(userPostOutDTO);
        when(postService.getAllUnapprovedPost()).thenReturn(unApprovedPostList);
        MvcResult mvc = mockMvc
                .perform(get("/post/unapprove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "[{\"postId\":\"dsfhg734fsd\",\"heading\":\"Heading\",\"paragraph\":\"Paragraph\",\"technology\":\"Java\",\"updatedAt\":\"07-09-2023 13:24:59\",\"firstName\":\"Name\",\"lastName\":\"Surname\",\"designation\":\"HR\"}]",
                result);
    }

    @Test
    public void testGetAllUnapprovePostsFail() throws Exception {
        GetPostInDTO getPostInDTO = new GetPostInDTO();
        getPostInDTO.setUserId("nsdjgh2374grfsdg");
        getPostInDTO.setHeading("Heading");
        getPostInDTO.setTechnology(Technology.CSS);
        when(postService.getAllApprovePosts(getPostInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/post/approve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testGetAllReportedPostsSucess() throws Exception {
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName("Name");
        userPostOutDTO.setLastName("Surname");
        userPostOutDTO.setDesignation("HR");
        userPostOutDTO.setHeading("Heading");
        userPostOutDTO.setParagraph("Paragraph");
        userPostOutDTO.setTechnology("Java");
        userPostOutDTO.setPostId("dsfhg734fsd");
        userPostOutDTO.setUpdatedAt("07-09-2023 13:24:59");
        ReportedPostOutDTO reportedPostOutDTO = new ReportedPostOutDTO();
        reportedPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        reportedPostOutDTO.setReportCount(43);
        List<ReportedPostOutDTO> reportedPostList = new ArrayList<>();
        reportedPostList.add(reportedPostOutDTO);
        when(postService.getAllReportedPost()).thenReturn(reportedPostList);
        MvcResult mvc = mockMvc
                .perform(get("/post/reported")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "[{\"userPostOutDTO\":{\"postId\":\"dsfhg734fsd\",\"heading\":\"Heading\",\"paragraph\":\"Paragraph\",\"technology\":\"Java\",\"updatedAt\":\"07-09-2023 13:24:59\",\"firstName\":\"Name\",\"lastName\":\"Surname\",\"designation\":\"HR\"},\"reportCount\":43}]",
                result);
    }

    @Test
    public void testGetPostByPostIdSuccess() throws Exception {
        String postId = "fdusyh23o4re436";
        UpdatePostOutDTO updatePostOutDTO = new UpdatePostOutDTO();
        updatePostOutDTO.setHeading("Heading");
        updatePostOutDTO.setParagraph("Paragraph");
        updatePostOutDTO.setTechnology(Technology.SQL_DATABASES);
        when(postService.getPostByPostId(postId)).thenReturn(updatePostOutDTO);
        MvcResult mvc = mockMvc
                .perform(get("/post/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"heading\":\"Heading\",\"paragraph\":\"Paragraph\",\"technology\":\"SQL_DATABASES\"}",
                result);
    }

    @Test
    public void testGetPostByPostIdFail() throws Exception {
        String postId = "fdusyh23o4re436";
        when(postService.getPostByPostId(postId)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(get("/post/{postId}", postId)
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
    public void testGetPostByUserIdSuccess() throws Exception {
        MyPostInDTO myPostInDTO = new MyPostInDTO();
        myPostInDTO.setUserId("dsghbrf3y6489yhgfv");
        myPostInDTO.setHeading("Heading");
        myPostInDTO.setStatus(Status.PENDING);
        myPostInDTO.setTechnology(Technology.SQL_DATABASES);
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName("Name");
        userPostOutDTO.setLastName("Surname");
        userPostOutDTO.setDesignation("HR");
        userPostOutDTO.setHeading("Heading");
        userPostOutDTO.setParagraph("Paragraph");
        userPostOutDTO.setTechnology("Java");
        userPostOutDTO.setPostId("dsfhg734fsd");
        userPostOutDTO.setUpdatedAt("07-09-2023 13:24:59");
        GetAllMyPostOutDTO getAllMyPostOutDTO = new GetAllMyPostOutDTO();
        getAllMyPostOutDTO.setCommentCount(12);
        getAllMyPostOutDTO.setLikeCount(32);
        getAllMyPostOutDTO.setDislikeCount(2);
        getAllMyPostOutDTO.setLike(false);
        getAllMyPostOutDTO.setDislike(true);
        getAllMyPostOutDTO.setStatus(Status.REJECTED);
        getAllMyPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        List<GetAllMyPostOutDTO> myPostList = new ArrayList<>();
        myPostList.add(getAllMyPostOutDTO);
        when(postService.getAllPostByUserId(myPostInDTO))
                .thenReturn(myPostList);
        MvcResult mvc = mockMvc
                .perform(post("/post/userId", myPostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(myPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "[{\"userPostOutDTO\":{\"postId\":\"dsfhg734fsd\",\"heading\":\"Heading\",\"paragraph\":\"Paragraph\",\"technology\":\"Java\",\"updatedAt\":\"07-09-2023 13:24:59\",\"firstName\":\"Name\",\"lastName\":\"Surname\",\"designation\":\"HR\"},\"status\":\"REJECTED\",\"like\":false,\"dislike\":true,\"likeCount\":32,\"dislikeCount\":2,\"commentCount\":12}]",
                result);
    }

    @Test
    public void testGetPostByUserIdFail() throws Exception {
        MyPostInDTO myPostInDTO = new MyPostInDTO();
        myPostInDTO.setUserId("dsghbrf3y6489yhgfv");
        myPostInDTO.setHeading("Heading");
        myPostInDTO.setStatus(Status.PENDING);
        myPostInDTO.setTechnology(Technology.SQL_DATABASES);
        when(postService.getAllPostByUserId(myPostInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/post/userId", myPostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(myPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testAddPostSuccess() throws Exception {
        AddPostInDTO addPostInDTO = new AddPostInDTO();
        addPostInDTO.setUserId("bhcsy7we6r79ee");
        addPostInDTO.setHeading("Heading");
        addPostInDTO.setParagraph("Paragraph");
        addPostInDTO.setTechnology(Technology.SQL_DATABASES);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.ADD_POST);
        when(postService.addPost(addPostInDTO)).thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(post("/post/add", addPostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"message\":\"post added successfully for admin review\"}",
                result);
    }

    @Test
    public void testAddPostFailPostNotFound() throws Exception {
        AddPostInDTO addPostInDTO = new AddPostInDTO();
        addPostInDTO.setUserId("bhcsy7we6r79ee");
        addPostInDTO.setHeading("Heading");
        addPostInDTO.setParagraph("Paragraph");
        addPostInDTO.setTechnology(Technology.SQL_DATABASES);
        when(postService.addPost(addPostInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.USER_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(post("/post/add", addPostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addPostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"user not found\"}",
                result);
    }

    @Test
    public void testUpdatePostSuccess() throws Exception {
        UpdatePostInDTO updatePostInDTO = new UpdatePostInDTO();
        updatePostInDTO.setPostId("bhcsy7we6r79ee");
        updatePostInDTO.setHeading("Heading");
        updatePostInDTO.setParagraph("Paragraph");
        updatePostInDTO.setTechnology(Technology.SQL_DATABASES);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_POST);
        when(postService.updatePost(updatePostInDTO))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(put("/post/update", updatePostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(updatePostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"message\":\"post updated successfully for admin review\"}",
                result);
    }

    @Test
    public void testUpdatePostFailPostNotFound() throws Exception {
        UpdatePostInDTO updatePostInDTO = new UpdatePostInDTO();
        updatePostInDTO.setPostId("bhcsy7we6r79ee");
        updatePostInDTO.setHeading("Heading");
        updatePostInDTO.setParagraph("Paragraph");
        updatePostInDTO.setTechnology(Technology.SQL_DATABASES);
        when(postService.updatePost(updatePostInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/post/update", updatePostInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(updatePostInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }

    @Test
    public void testPostApprovalSuccess() throws Exception {
        PostApprovalInDTO postApprovalInDTO = new PostApprovalInDTO();
        postApprovalInDTO.setPostId("bhcsy7we6r79ee");
        postApprovalInDTO.setStatus(Status.APPROVED);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.APPROVE_POST);
        when(postService.postApproval(postApprovalInDTO))
                .thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(put("/post/update/status", postApprovalInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(postApprovalInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"status updated successfully\"}", result);
    }

    @Test
    public void testPostApprovalFailPostNotFound() throws Exception {
        PostApprovalInDTO postApprovalInDTO = new PostApprovalInDTO();
        postApprovalInDTO.setPostId("bhcsy7we6r79ee");
        postApprovalInDTO.setStatus(Status.APPROVED);
        when(postService.postApproval(postApprovalInDTO)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(put("/post/update/status", postApprovalInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(postApprovalInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"NOT_FOUND\",\"message\":\"post not found\"}",
                result);
    }

    @Test
    public void testPostApprovalFailInvalidStatus() throws Exception {
        PostApprovalInDTO postApprovalInDTO = new PostApprovalInDTO();
        postApprovalInDTO.setPostId("bhcsy7we6r79ee");
        postApprovalInDTO.setStatus(Status.PENDING);
        when(postService.postApproval(postApprovalInDTO)).thenThrow(
                new InvalidRecordException(ConstantMessages.INVALID_STATUS));
        MvcResult mvc = mockMvc
                .perform(put("/post/update/status", postApprovalInDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(postApprovalInDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals(
                "{\"status\":\"BAD_REQUEST\",\"message\":\"invalid status\"}",
                result);
    }

    @Test
    public void testDeletePostSuccess() throws Exception {
        String postId = "bhcsy7we6r79ee";
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.DELETE_POST);
        when(postService.deletePost(postId)).thenReturn(responseOutDTO);
        MvcResult mvc = mockMvc
                .perform(delete("/post/delete/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        assertEquals("{\"message\":\"post deleted successfully\"}", result);
    }

    @Test
    public void testDeletePostFailPostNotFound() throws Exception {
        String postId = "bhcsy7we6r79ee";
        when(postService.deletePost(postId)).thenThrow(
                new RecordNotFoundException(ConstantMessages.POST_NOT_FOUND));
        MvcResult mvc = mockMvc
                .perform(delete("/post/delete/{postId}", postId)
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
