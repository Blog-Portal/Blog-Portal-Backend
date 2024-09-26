package com.madhur.blog_portal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.DTO.InDTO.CommentInDTO;
import com.madhur.blog_portal.DTO.OutDTO.CommentReportOutDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Model.Comment;
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.CommentRepository;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.ServiceImpl.CommentServiceImpl;
import com.madhur.blog_portal.Utilities.ConstantMessages;

public class CommentServiceTest {
    @InjectMocks
    private CommentServiceImpl commentServiceImpl;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCommentsByPostIdSucess() {
        String postId = "fidysb32432";
        Post post = new Post();
        post.setPostId(postId);
        User user1 = new User();
        user1.setFirstName("Name 1");
        user1.setLastName("Surname 1");
        Comment comment1 = new Comment();
        comment1.setUser(user1);
        comment1.setCommentId("fdnbshj3279");
        comment1.setMessage("Comment 1");
        Comment comment2 = new Comment();
        comment2.setCommentId("fdyushj32789");
        comment2.setMessage("Comment 2");
        User user2 = new User();
        user2.setFirstName("Name 2");
        user2.setLastName("Surname 2");
        comment2.setUser(user2);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findByPost(post)).thenReturn(comments);
        List<CommentReportOutDTO> result = commentServiceImpl
                .getCommentsByPostId(postId);
        List<CommentReportOutDTO> expected = new ArrayList<>();
        CommentReportOutDTO commentReportOutDTO1 = new CommentReportOutDTO();
        commentReportOutDTO1.setId(comment1.getCommentId());
        commentReportOutDTO1.setBody(comment1.getMessage());
        commentReportOutDTO1.setFirstName(user1.getFirstName());
        commentReportOutDTO1.setLastName(user1.getLastName());
        CommentReportOutDTO commentReportOutDTO2 = new CommentReportOutDTO();
        commentReportOutDTO2.setId(comment2.getCommentId());
        commentReportOutDTO2.setBody(comment2.getMessage());
        commentReportOutDTO2.setFirstName(user2.getFirstName());
        commentReportOutDTO2.setLastName(user2.getLastName());
        expected.add(commentReportOutDTO1);
        expected.add(commentReportOutDTO2);
        assertEquals(expected, result);
    }

    @Test
    public void testGetCommentsByPostIdFailPostNotFound() {
        Post post = new Post();
        post.setPostId("ndsiuyr3y292fd");
        when(userRepository.findById("fdbhser23")).thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    commentServiceImpl.getCommentsByPostId(post.getPostId());
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testAddCommentSucess() {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("fdbhser23");
        commentInDTO.setPostId("fhedsk329");
        commentInDTO.setMessage("Comment");
        User user = new User();
        when(userRepository.findById("fdbhser23"))
                .thenReturn(Optional.of(user));
        Post post = new Post();
        when(postRepository.findById("fhedsk329"))
                .thenReturn(Optional.of(post));
        ResponseOutDTO result = commentServiceImpl.addComments(commentInDTO);
        assertEquals(ConstantMessages.COMMENT_ADDED, result.getMessage());
    }

    @Test
    public void testAddCommentFailUserNotFound() {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("bdhsgyi232321");
        when(userRepository.findById("dbshfu38729"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    commentServiceImpl.addComments(commentInDTO);
                });
        assertEquals(ConstantMessages.USER_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testAddCommentFailPostNotFound() {
        CommentInDTO commentInDTO = new CommentInDTO();
        commentInDTO.setUserId("fnds3974r1ewd");
        commentInDTO.setPostId("bdhsgyi232321");
        when(userRepository.findById("fnds3974r1ewd"))
                .thenReturn(Optional.of(new User()));
        when(postRepository.findById("jdus201343"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    commentServiceImpl.addComments(commentInDTO);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }
}
