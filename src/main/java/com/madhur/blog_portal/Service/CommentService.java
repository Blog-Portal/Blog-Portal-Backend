package com.madhur.blog_portal.Service;

import java.util.List;

import com.madhur.blog_portal.DTO.InDTO.CommentInDTO;
import com.madhur.blog_portal.DTO.OutDTO.CommentReportOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;

/**
 * Comment Service interface managing Comment operations.
 */
public interface CommentService {
    /**
     * Get comments for a specific post by postId.
     * @param postId The postId of the post for which comments are requested.
     * @return A list of CommentOutDTO objects containing comments for the post.
     */
    List<CommentReportOutDTO> getCommentsByPostId(String postId);
    /**
     * Add a new comment to a post.
     * @param commentInDTO The CommentInDTO object containing comment details.
     * @return A message indicating the result of adding the comment.
     */
    ResponseOutDTO addComments(CommentInDTO commentInDTO);
}
