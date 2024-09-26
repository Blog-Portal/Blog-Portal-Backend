package com.madhur.blog_portal.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhur.blog_portal.DTO.InDTO.CommentInDTO;
import com.madhur.blog_portal.DTO.OutDTO.CommentReportOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Comment;
import com.madhur.blog_portal.Repository.CommentRepository;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.Service.CommentService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

/**
 * Implementation of the CommentService interface for managing comments on
 * posts.
 */
@Service
public class CommentServiceImpl implements CommentService {
    /**
     * CommentRepository Instance.
     */
    @Autowired
    private CommentRepository commentRepository;
    /**
     * PostRepository Instance.
     */
    @Autowired
    private PostRepository postRepository;
    /**
     * UserRepository Instance.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves comments for a post based on its ID.
     * @param postId The ID of the post.
     * @return A list of CommentOutDTO representing the comments for the post.
     * @throws RecordNotFoundException if the post is not found or no comments
     *             exist.
     */
    @Override
    public List<CommentReportOutDTO> getCommentsByPostId(final String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        List<Comment> comments = commentRepository.findByPost(post);
        List<CommentReportOutDTO> commentsOutDTO = new ArrayList<>();
        for (Comment comment : comments) {
            CommentReportOutDTO commentOutDTO = new CommentReportOutDTO();
            commentOutDTO.setId(comment.getCommentId());
            commentOutDTO.setBody(comment.getMessage());
            commentOutDTO.setFirstName(comment.getUser().getFirstName());
            commentOutDTO.setLastName(comment.getUser().getLastName());
            commentsOutDTO.add(commentOutDTO);
        }
        return commentsOutDTO;
    }

    /**
     * Adds a comment to a post.
     * @param commentInDTO The input DTO containing the comment details.
     * @return A string indicating whether the comment was added successfully.
     * @throws RecordNotFoundException if the user or post is not found.
     */
    public ResponseOutDTO addComments(final CommentInDTO commentInDTO) {
        User user = userRepository.findById(commentInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = postRepository.findById(commentInDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        Comment comment = new Comment();
        comment.setMessage(commentInDTO.getMessage().trim());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.COMMENT_ADDED);
        return responseOutDTO;
    }
}
