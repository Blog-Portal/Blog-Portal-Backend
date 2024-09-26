package com.madhur.blog_portal.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Reaction;
import com.madhur.blog_portal.Model.Report;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.CommentRepository;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.ReactionRepository;
import com.madhur.blog_portal.Repository.ReportRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.Service.PostService;
import com.madhur.blog_portal.Utilities.ConstantMessages;
import com.madhur.blog_portal.Utilities.DateFormatUtility;
import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

/**
 * Implementation of the PostService interface for managing posts in the
 * application.
 */
@Service
public class PostServiceImpl implements PostService {
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
     * MongoTemplate Instance.
     */
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * ReactionRepository Instance.
     */
    @Autowired
    private ReactionRepository reactionRepository;
    /**
     * CommentRepository Instance.
     */
    @Autowired
    private CommentRepository commentRepository;
    /**
     * ReportRepository Instance.
     */
    @Autowired
    private ReportRepository reportRepository;

    /**
     * Adds a new post.
     * @param postInDTO The input DTO containing post details.
     * @return A string indicating whether the post was added successfully.
     * @throws RecordNotFoundException if the user is not found.
     */
    @Override
    public ResponseOutDTO addPost(final AddPostInDTO postInDTO) {
        User user = userRepository.findById(postInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = new Post();
        post.setCreatedAt(DateFormatUtility.newDate());
        post.setUser(user);
        post.setHeading(postInDTO.getHeading().trim());
        post.setParagraph(postInDTO.getParagraph());
        post.setTechnology(postInDTO.getTechnology());
        post.setStatus(Status.PENDING);
        post.setUpdatedAt(DateFormatUtility.newDate());
        postRepository.save(post);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.ADD_POST);
        return responseOutDTO;
    };

    /**
     * Updates an existing post.
     * @param postInDTO The input DTO containing updated post details.
     * @return A string indicating whether the post was updated successfully.
     * @throws RecordNotFoundException if the post is not found.
     */
    @Override
    public ResponseOutDTO updatePost(final UpdatePostInDTO postInDTO) {
        Post exitingPost = postRepository.findById(postInDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        exitingPost.setHeading(postInDTO.getHeading().trim());
        exitingPost.setParagraph(postInDTO.getParagraph());
        exitingPost.setTechnology(postInDTO.getTechnology());
        exitingPost.setStatus(Status.PENDING);
        exitingPost.setUpdatedAt(DateFormatUtility.newDate());
        postRepository.save(exitingPost);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_POST);
        return responseOutDTO;
    }

    /**
     * Retrieves a post by its ID.
     * @param postId The ID of the post.
     * @return An UpdatePostOutDTO representing the post.
     * @throws RecordNotFoundException if the post is not found.
     */
    @Override
    public UpdatePostOutDTO getPostByPostId(final String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        UpdatePostOutDTO updatePostOutDTO = new UpdatePostOutDTO();
        updatePostOutDTO.setHeading(post.getHeading());
        updatePostOutDTO.setParagraph(post.getParagraph());
        updatePostOutDTO.setTechnology(post.getTechnology());
        return updatePostOutDTO;
    };

    /**
     * Retrieves all posts by a specific user.
     * @param myPostInDTO My Post In DTO instance
     * @return A list of MyPostOutDTO representing the user's posts.
     * @throws RecordNotFoundException if the user is not found or no posts
     *             exist.
     */
    @Override
    public List<GetAllMyPostOutDTO> getAllPostByUserId(
            final MyPostInDTO myPostInDTO) {
        User user = userRepository.findById(myPostInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Query query = new Query();
        String heading = myPostInDTO.getHeading();
        Technology technology = myPostInDTO.getTechnology();
        Status status = myPostInDTO.getStatus();
        query.addCriteria(Criteria.where("user").is(user));
        if (Objects.nonNull(heading)) {
            Pattern pattern = Pattern.compile(Pattern.quote(heading),
                    Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("heading").regex(pattern));
        }
        if (Objects.nonNull(technology)) {
            query.addCriteria(Criteria.where("technology").is(technology));
        }
        if (Objects.nonNull(status)) {
            query.addCriteria(Criteria.where("status").is(status));
        }
        Sort sort = Sort.by("updatedAt").descending();
        List<Post> posts = mongoTemplate.find(query.with(sort), Post.class);
        List<GetAllMyPostOutDTO> myPostsOutDTO = new ArrayList<>();
        for (Post post : posts) {
            GetAllMyPostOutDTO myPostOutDTO = new GetAllMyPostOutDTO();
            UserPostOutDTO userPostOutDTO = modelToDTOConvertor(post, user);
            myPostOutDTO.setUserPostOutDTO(userPostOutDTO);
            myPostOutDTO.setStatus(post.getStatus());
            myPostOutDTO.setLikeCount(
                    reactionRepository.countByPostAndReaction(post, true));
            myPostOutDTO.setDislikeCount(
                    reactionRepository.countByPostAndReaction(post, false));
            myPostOutDTO.setCommentCount(commentRepository.countByPost(post));
            Reaction reaction = reactionRepository.getByUserAndPost(user, post);
            if (Objects.isNull(reaction)) {
                myPostOutDTO.setLike(false);
                myPostOutDTO.setDislike(false);
            } else if (reaction.isReaction()) {
                myPostOutDTO.setLike(true);
                myPostOutDTO.setDislike(false);
            } else {
                myPostOutDTO.setLike(false);
                myPostOutDTO.setDislike(true);
            }
            myPostsOutDTO.add(myPostOutDTO);
        }
        return myPostsOutDTO;
    };

    /**
     * Retrieves all approved posts based on filter criteria.
     * @param getPostInDTO The input DTO containing filter criteria.
     * @return A list of GetAllPostOutDTO representing the approved posts.
     * @throws RecordNotFoundException if no results are found.
     */
    @Override
    public List<GetAllPostOutDTO> getAllApprovePosts(
            final GetPostInDTO getPostInDTO) {
        User user = userRepository.findById(getPostInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Query query = new Query();
        String heading = getPostInDTO.getHeading();
        Technology technology = getPostInDTO.getTechnology();
        Status status = Status.APPROVED;
        query.addCriteria(Criteria.where("status").is(status));
        if (Objects.nonNull(heading)) {
            Pattern pattern = Pattern.compile(Pattern.quote(heading),
                    Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("heading").regex(pattern));
        }
        if (Objects.nonNull(technology)) {
            query.addCriteria(Criteria.where("technology").is(technology));
        }
        Sort sort = Sort.by("updatedAt").descending();
        List<Post> posts = mongoTemplate.find(query.with(sort), Post.class);
        List<GetAllPostOutDTO> getAllPostOutDTOs = new ArrayList<>();
        for (Post post : posts) {
            GetAllPostOutDTO getAllPostOutDTO = new GetAllPostOutDTO();
            UserPostOutDTO userPostOutDTO = modelToDTOConvertor(post,
                    post.getUser());
            getAllPostOutDTO.setUserPostOutDTO(userPostOutDTO);
            getAllPostOutDTO.setLikeCount(
                    reactionRepository.countByPostAndReaction(post, true));
            getAllPostOutDTO.setDislikeCount(
                    reactionRepository.countByPostAndReaction(post, false));
            getAllPostOutDTO
                    .setCommentCount(commentRepository.countByPost(post));
            getAllPostOutDTO.setReport(
                    reportRepository.existsByPostAndUser(post, user));
            getAllPostOutDTO.setReportCount(reportRepository.countByPost(post));
            getAllPostOutDTO.setMyPost(post.getUser().equals(user));
            Reaction reaction = reactionRepository.getByUserAndPost(user, post);
            if (Objects.isNull(reaction)) {
                getAllPostOutDTO.setLike(false);
                getAllPostOutDTO.setDislike(false);
            } else if (reaction.isReaction()) {
                getAllPostOutDTO.setLike(true);
                getAllPostOutDTO.setDislike(false);
            } else {
                getAllPostOutDTO.setLike(false);
                getAllPostOutDTO.setDislike(true);
            }
            getAllPostOutDTOs.add(getAllPostOutDTO);
        }
        return getAllPostOutDTOs;
    }

    /**
     * Retrieves all UNAPPROVED posts based on filter criteria.
     * @return A list of PostApprovalOutDTO representing the unproved posts.
     */
    @Override
    public List<UserPostOutDTO> getAllUnapprovedPost() {
        List<Post> posts = postRepository
                .getByStatusOrderByUpdatedAtDesc(Status.PENDING);
        List<UserPostOutDTO> postsOutDTO = new ArrayList<>();
        for (Post post : posts) {
            UserPostOutDTO postOutDTO = modelToDTOConvertor(post,
                    post.getUser());
            postsOutDTO.add(postOutDTO);
        }
        return postsOutDTO;
    }

    /**
     * Approves or rejects a post based on its status.
     * @param postApprovalInDTO The input DTO containing the post ID and status.
     * @return A string indicating whether the post was approved or rejected.
     * @throws RecordNotFoundException if the post is not found or the status is
     *             invalid.
     */
    @Override
    public ResponseOutDTO postApproval(
            final PostApprovalInDTO postApprovalInDTO) {
        if (postApprovalInDTO.getStatus().equals(Status.PENDING)) {
            throw new InvalidRecordException(ConstantMessages.INVALID_STATUS);
        }
        Post exitingPost = postRepository
                .findById(postApprovalInDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        exitingPost.setStatus(postApprovalInDTO.getStatus());
        postRepository.save(exitingPost);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.APPROVE_POST);
        return responseOutDTO;
    }

    /**
     * Retrieves all reported posts along with user and post details.
     * @return A list of ReportedPostOutDTO representing reported posts.
     */
    @Override
    public List<ReportedPostOutDTO> getAllReportedPost() {
        List<Report> reports = reportRepository.findAll();
        Set<Post> existingPost = new HashSet<>();
        List<ReportedPostOutDTO> reportedPostsOutDTO = new ArrayList<>();
        for (Report report : reports) {
            if (!existingPost.contains(report.getPost())) {
                existingPost.add(report.getPost());
                ReportedPostOutDTO reportedPostOutDTO = new ReportedPostOutDTO();
                Post post = report.getPost();
                UserPostOutDTO userPostOutDTO = modelToDTOConvertor(post,
                        post.getUser());
                reportedPostOutDTO.setUserPostOutDTO(userPostOutDTO);
                reportedPostOutDTO
                        .setReportCount(reportRepository.countByPost(post));
                reportedPostsOutDTO.add(reportedPostOutDTO);
            }
        }
        Collections.reverse(reportedPostsOutDTO);
        return reportedPostsOutDTO;
    }

    /**
     * Deletes a post by its ID, including associated reactions, comments, and
     * reports.
     * @param postId The ID of the post to delete.
     * @return A string indicating whether the post was deleted successfully.
     * @throws RecordNotFoundException if the post is not found.
     */
    @Override
    public ResponseOutDTO deletePost(final String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        reactionRepository.deleteByPost(post);
        commentRepository.deleteByPost(post);
        reportRepository.deleteByPost(post);
        postRepository.deleteById(postId);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.DELETE_POST);
        return responseOutDTO;
    }

    /**
     * @param post post model.
     * @param user user model.
     * @return UserPostOutDTO instance.
     */
    public UserPostOutDTO modelToDTOConvertor(final Post post,
            final User user) {
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName(user.getFirstName());
        userPostOutDTO.setLastName(user.getLastName());
        String designation = user.getDesignation().toString();
        userPostOutDTO.setDesignation(designation.replace("_", " "));
        userPostOutDTO.setPostId(post.getPostId());
        userPostOutDTO.setHeading(post.getHeading());
        userPostOutDTO.setParagraph(post.getParagraph());
        String tech = post.getTechnology().toString();
        userPostOutDTO.setTechnology(tech.replace("_", " "));
        userPostOutDTO.setUpdatedAt(post.getUpdatedAt());
        return userPostOutDTO;
    }
}
