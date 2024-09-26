package com.madhur.blog_portal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.DTO.InDTO.ReactionInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Reaction;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.ReactionRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.ServiceImpl.ReactionServiceImpl;
import com.madhur.blog_portal.Utilities.ConstantMessages;

public class ReactionServiceTest {
    @InjectMocks
    private ReactionServiceImpl reactionServiceImpl;
    @Mock
    private ReactionRepository reactionRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateReactionSucess() {
        ReactionInDTO reactionDTO = new ReactionInDTO();
        reactionDTO.setUserId("gdysfghsdf321");
        reactionDTO.setPostId("fhdsyry764932r");
        reactionDTO.setCurrentReaction(true);
        Post post = new Post();
        post.setPostId("fhdsyry764932r");
        User user = new User();
        user.setUserId("gdysfghsdf321");
        user.setFirstName("Name 1");
        user.setLastName("Surname 1");
        Reaction reaction = new Reaction();
        reaction.setUser(user);
        reaction.setPost(post);
        reaction.setReaction(true);
        reaction.setReactionId("fdskfhbds");
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));
        when(postRepository.findById(post.getPostId()))
                .thenReturn(Optional.of(post));
        when(reactionRepository.getByUserAndPost(user, post))
                .thenReturn(reaction);
        ResponseOutDTO result = reactionServiceImpl.updateReaction(reactionDTO);
        assertEquals(ConstantMessages.UPDATE_REACTION, result.getMessage());
    }

    @Test
    public void updateReactionSuccessUpdateExistingReaction() {
        ReactionInDTO reactionDTO = new ReactionInDTO();
        reactionDTO.setUserId("gdysfghsdf321");
        reactionDTO.setPostId("fhdsyry764932r");
        reactionDTO.setCurrentReaction(false);
        Post post = new Post();
        post.setPostId("fhdsyry764932r");
        User user = new User();
        user.setUserId("gdysfghsdf321");
        Reaction reaction = new Reaction();
        reaction.setUser(user);
        reaction.setPost(post);
        reaction.setReaction(true);
        reaction.setReactionId("fdskfhbds");
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));
        when(postRepository.findById(post.getPostId()))
                .thenReturn(Optional.of(post));
        when(reactionRepository.getByUserAndPost(user, post))
                .thenReturn(reaction);
        ResponseOutDTO result = reactionServiceImpl.updateReaction(reactionDTO);
        assertFalse(reaction.isReaction());
        assertEquals(ConstantMessages.UPDATE_REACTION, result.getMessage());
    }

    @Test
    public void updateReactionCreateNewReaction() {
        ReactionInDTO reactionDTO = new ReactionInDTO();
        reactionDTO.setUserId("hfdfh8769");
        reactionDTO.setPostId("nfdisuy78");
        reactionDTO.setCurrentReaction(true);
        User user = new User();
        user.setUserId("hfdfh8769");
        Post post = new Post();
        post.setPostId("nfdisuy78");
        when(userRepository.findById(reactionDTO.getUserId()))
                .thenReturn(Optional.of(user));
        when(postRepository.findById(reactionDTO.getPostId()))
                .thenReturn(Optional.of(post));
        when(reactionRepository.getByUserAndPost(user, post)).thenReturn(null);
        ResponseOutDTO result = reactionServiceImpl.updateReaction(reactionDTO);
        assertEquals(ConstantMessages.UPDATE_REACTION, result.getMessage());
    }

    @Test
    public void updateReactionFailUserNotFound() {
        ReactionInDTO reactionDTO = new ReactionInDTO();
        reactionDTO.setUserId("gdysfghsdf321");
        when(userRepository.findById("fudshbfew43"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    reactionServiceImpl.updateReaction(reactionDTO);
                });
        assertEquals(ConstantMessages.USER_NOT_FOUND, result.getMessage());
    }

    @Test
    public void updateReactionFailPostNotFound() {
        ReactionInDTO reactionDTO = new ReactionInDTO();
        reactionDTO.setUserId("gdysfghsdf321");
        reactionDTO.setPostId("fdbshy372429");
        when(userRepository.findById("gdysfghsdf321"))
                .thenReturn(Optional.of(new User()));
        when(postRepository.findById("fdniy7324324"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    reactionServiceImpl.updateReaction(reactionDTO);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }
}
