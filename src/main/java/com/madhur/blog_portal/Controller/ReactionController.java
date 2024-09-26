package com.madhur.blog_portal.Controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhur.blog_portal.DTO.InDTO.ReactionInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Service.ReactionService;

/**
 * Reaction Controller class to handle Reaction related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/reaction")
public class ReactionController {
    /**
     * Logger for track log reports.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ReactionController.class);
    /**
     * ReactionService instance.
     */
    @Autowired
    private ReactionService reactionService;

    /**
     * Updates/creates/delete the reaction of a user on a post.
     * @param reactionDTO The input DTO containing user ID, post ID, and the
     *            current reaction status.
     * @return A StatusManager indicating the result of the reaction update.
     */
    @PutMapping
    public ResponseOutDTO updateReaction(
            @Valid @RequestBody final ReactionInDTO reactionDTO) {
        LOGGER.info("Request for update reaction {}", reactionDTO.toString());
        ResponseOutDTO updateReaction = reactionService
                .updateReaction(reactionDTO);
        LOGGER.info("Successfully reaction is updated {}",
                reactionDTO.toString());
        return updateReaction;
    }
}
