package com.madhur.blog_portal.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
import com.madhur.blog_portal.Service.PostService;

/**
 * Post Controller class to handle Post related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    /**
     * LOGGER for track log reports.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(PostController.class);
    /**
     * PostService instance.
     */
    @Autowired
    private PostService postService;

    /**
     * Get all the Approved Posts.
     * @param getPostInDTO instance of Get Post
     * @return A StatusManager indicating the result of retrieving approved
     *         posts.
     */
    @PostMapping("/approve")
    public List<GetAllPostOutDTO> getAllApprovePosts(
            @Valid @RequestBody final GetPostInDTO getPostInDTO) {
        LOGGER.info(
                "Request for get all approved posts by following criteria {}",
                getPostInDTO.toString());
        List<GetAllPostOutDTO> allApprovePosts = postService
                .getAllApprovePosts(getPostInDTO);
        LOGGER.info(
                "Successfully retrieved list of all approved posts by following criteria: {}",
                getPostInDTO.toString());
        return allApprovePosts;
    }

    /**
     * Get all the UnApproved Posts.
     * @return A StatusManager indicating the result of retrieving unApproved
     *         posts.
     */
    @GetMapping("/unapprove")
    public List<UserPostOutDTO> getAllUnapprovedPost() {
        LOGGER.info("Request for get all unapproved posts");
        List<UserPostOutDTO> allUnapprovedPost = postService
                .getAllUnapprovedPost();
        LOGGER.info("Successfully retrieved list of all unapproved posts");
        return allUnapprovedPost;
    }

    /**
     * Get all reported posts.
     * @return A StatusManager indicating the result of retrieving reported
     *         posts.
     */
    @GetMapping("/reported")
    public List<ReportedPostOutDTO> getAllReportedPost() {
        LOGGER.info("Request for get all reported posts");
        List<ReportedPostOutDTO> allReportedPost = postService
                .getAllReportedPost();
        LOGGER.info("Successfully retrieved list of all reported posts");
        return allReportedPost;
    }

    /**
     * Get a post by its postId.
     * @param postId The postId to retrieve.
     * @return A StatusManager indicating the result of retrieving a post by
     *         postId.
     */
    @GetMapping("/{postId}")
    public UpdatePostOutDTO getPostByPostId(
            @Valid @PathVariable final String postId) {
        LOGGER.info("Request for get post by postId {}", postId);
        UpdatePostOutDTO postByPostId = postService.getPostByPostId(postId);
        LOGGER.info("Successfully retrieved post by postId {}", postId);
        return postByPostId;
    }

    /**
     * Get all posts by a specific userId.
     * @param myPostInDTO instance of My Post In DTO
     * @return A StatusManager indicating the result of retrieving posts by
     *         userId.
     */
    @PostMapping("/userId")
    public List<GetAllMyPostOutDTO> getAllPostByUserId(
            @Valid @RequestBody final MyPostInDTO myPostInDTO) {
        LOGGER.info(
                "Request for get all posts by userId and following criteria {}",
                myPostInDTO.toString());
        List<GetAllMyPostOutDTO> allPostByUserId = postService
                .getAllPostByUserId(myPostInDTO);
        LOGGER.info(
                "Successfully retrieved list of all posts by userId and following criteria {}",
                myPostInDTO.toString());
        return allPostByUserId;
    }

    /**
     * Add a new post.
     * @param postInDTO The AddPostInDTO containing post information.
     * @return A StatusManager indicating the result of adding a new post.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseOutDTO addPost(
            @Valid @RequestBody final AddPostInDTO postInDTO) {
        LOGGER.info("Requst for add new post {}", postInDTO.toString());
        ResponseOutDTO addPost = postService.addPost(postInDTO);
        LOGGER.info("Successfully new post added {}", postInDTO.toString());
        return addPost;
    }

    /**
     * Update an existing post.
     * @param postInDTO The UpdatePostInDTO containing updated post information.
     * @return A StatusManager indicating the result of updating a post.
     */
    @PutMapping("/update")
    public ResponseOutDTO updatePost(
            @Valid @RequestBody final UpdatePostInDTO postInDTO) {
        LOGGER.info("Request for update post {}", postInDTO.toString());
        ResponseOutDTO updatePost = postService.updatePost(postInDTO);
        LOGGER.info("Successfully post updated {}", postInDTO.toString());
        return updatePost;
    }

    /**
     * Approve or reject a post.
     * @param postApprovalInDTO The PostApprovalInDTO containing approval
     *            information.
     * @return A StatusManager indicating the result of post approval or
     *         disapproval.
     */
    @PutMapping("/update/status")
    public ResponseOutDTO postApproval(
            @Valid @RequestBody final PostApprovalInDTO postApprovalInDTO) {
        LOGGER.info("Request for update post status {}",
                postApprovalInDTO.toString());
        ResponseOutDTO postApproval = postService
                .postApproval(postApprovalInDTO);
        LOGGER.info("Successfully update post status {}",
                postApprovalInDTO.toString());
        return postApproval;
    }

    /**
     * Delete a post by its postId.
     * @param postId The postId to delete.
     * @return A StatusManager indicating the result of deleting a post.
     */
    @DeleteMapping("/delete/{postId}")
    public ResponseOutDTO deletePost(@PathVariable final String postId) {
        LOGGER.info("Request for delete Post with postId {}", postId);
        ResponseOutDTO deletePost = postService.deletePost(postId);
        LOGGER.info("Successfully delete Post with postId {}", postId);
        return deletePost;
    }
}
