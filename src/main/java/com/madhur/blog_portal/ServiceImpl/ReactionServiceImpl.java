package com.madhur.blog_portal.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhur.blog_portal.DTO.InDTO.ReactionInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Reaction;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.ReactionRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.Service.ReactionService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

/**
 * Service implementation for managing reactions on posts.
 */
@Service
public class ReactionServiceImpl implements ReactionService {
    /**
     * ReactionRepository instance.
     */
    @Autowired
    private ReactionRepository reactionRepository;
    /**
     * UserRepository instance.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * PostRepository instance.
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Updates/creates/delete the reaction of a user on a post.
     * @param reactionDTO The input DTO containing user ID, post ID, and the
     *            current reaction status.
     * @return A string indicating whether the reaction was updated
     *         successfully.
     * @throws RecordNotFoundException if the user or post is not found.
     */
    @Override
    public ResponseOutDTO updateReaction(final ReactionInDTO reactionDTO) {
        User user = userRepository.findById(reactionDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = postRepository.findById(reactionDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        Reaction existingReaction = reactionRepository.getByUserAndPost(user,
                post);
        if (Objects.isNull(existingReaction)) {
            Reaction reaction = new Reaction();
            reaction.setPost(post);
            reaction.setUser(user);
            reaction.setReaction(reactionDTO.isCurrentReaction());
            reactionRepository.save(reaction);
        } else {
            if (Objects.equals(existingReaction.isReaction(),
                    reactionDTO.isCurrentReaction())) {
                reactionRepository.delete(existingReaction);
            } else {
                existingReaction.setReaction(reactionDTO.isCurrentReaction());
                reactionRepository.save(existingReaction);
            }
        }
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REACTION);
        return responseOutDTO;
    }
}
