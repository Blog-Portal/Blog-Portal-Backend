package com.madhur.blog_portal.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.madhur.blog_portal.DTO.InDTO.CommentInDTO;
import com.madhur.blog_portal.DTO.OutDTO.CommentReportOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Service.CommentService;

/**
 * Comment Controller class to handle Comment related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * Logger for track log reports.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(CommentController.class);
    /**
     * CommentService instance.
     */
    @Autowired
    private CommentService commentService;

    /**
     * Get All Comments by Post Id.
     * @param postId The ID of the post for which comments are requested.
     * @return list of comments.
     */
    @GetMapping("/{postId}")
    public List<CommentReportOutDTO> getAllCommentsByPostId(
            @PathVariable final String postId) {
        LOGGER.info("Request for get all comments by postId {}", postId);
        List<CommentReportOutDTO> commentsByPostId = commentService
                .getCommentsByPostId(postId);
        LOGGER.info("Successfully retrieved list of comments by postId {}",
                postId);
        return commentsByPostId;
    }

    /**
     * Add a new comment.
     * @param commentInDTO The CommentInDTO object containing comment details.
     * @return result of the comment addition.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseOutDTO addComments(
            @Valid @RequestBody final CommentInDTO commentInDTO) {
        LOGGER.info("Request recieved at add comment {}",
                commentInDTO.toString());
        ResponseOutDTO addComments = commentService.addComments(commentInDTO);
        LOGGER.info("Comment added successfully {}", commentInDTO.toString());
        return addComments;
    }
}
