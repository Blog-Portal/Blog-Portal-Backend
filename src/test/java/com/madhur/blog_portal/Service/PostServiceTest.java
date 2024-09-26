package com.madhur.blog_portal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
import com.madhur.blog_portal.ServiceImpl.PostServiceImpl;
import com.madhur.blog_portal.Utilities.ConstantMessages;
import com.madhur.blog_portal.Utilities.DateFormatUtility;
import com.madhur.blog_portal.Utilities.Designation;
import com.madhur.blog_portal.Utilities.Gender;
import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PostServiceImpl postServiceImpl;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ReactionRepository reactionRepository;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPostSuccess() {
        AddPostInDTO postInDTO = new AddPostInDTO();
        postInDTO.setUserId("dhsgbhsdy47382");
        postInDTO.setHeading("Heading");
        postInDTO.setParagraph("Paragraph");
        postInDTO.setTechnology(Technology.JAVA);
        User user = new User();
        when(userRepository.findById("dhsgbhsdy47382"))
                .thenReturn(Optional.of(user));
        Post post = new Post();
        post.setCreatedAt(DateFormatUtility.newDate());
        post.setUser(user);
        post.setHeading(postInDTO.getHeading().trim());
        post.setParagraph(postInDTO.getParagraph());
        post.setTechnology(postInDTO.getTechnology());
        post.setStatus(Status.PENDING);
        post.setUpdatedAt(DateFormatUtility.newDate());
        when(postRepository.save(post)).thenReturn(post);
        ResponseOutDTO result = postServiceImpl.addPost(postInDTO);
        assertEquals(ConstantMessages.ADD_POST, result.getMessage());
    }

    @Test
    public void testAddPostFail() {
        AddPostInDTO postInDTO = new AddPostInDTO();
        when(userRepository.findById("fndsibf32432sa")).thenReturn(null);
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    postServiceImpl.addPost(postInDTO);
                });
        assertEquals(ConstantMessages.USER_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testUpdatePostSuccess() {
        UpdatePostInDTO postInDTO = new UpdatePostInDTO();
        postInDTO.setPostId("fdbshfbsk834");
        postInDTO.setHeading("Updated Heading");
        postInDTO.setParagraph("Updated Paragraph");
        postInDTO.setTechnology(Technology.CSS);
        User user = new User();
        Post existingPost = new Post();
        existingPost.setUser(user);
        existingPost.setHeading("Initial Heading");
        existingPost.setParagraph("Initial Paragraph");
        existingPost.setTechnology(Technology.JAVA);
        existingPost.setStatus(Status.PENDING);
        when(postRepository.findById("fdbshfbsk834"))
                .thenReturn(Optional.of(existingPost));
        ResponseOutDTO result = postServiceImpl.updatePost(postInDTO);
        assertEquals(ConstantMessages.UPDATE_POST, result.getMessage());
    }

    @Test
    public void testUpdatePostFail() {
        UpdatePostInDTO postInDTO = new UpdatePostInDTO();
        when(postRepository.findById("FDsfdsihfnsd89997"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    postServiceImpl.updatePost(postInDTO);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testGetPostByPostIdSuccess() {
        String postId = "fdbshfbsk834";
        Post post = new Post();
        post.setHeading("Heading");
        post.setParagraph("Paragraph");
        post.setTechnology(Technology.DATA_ENGINEERING);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        UpdatePostOutDTO expectedDTO = new UpdatePostOutDTO();
        expectedDTO.setHeading(post.getHeading());
        expectedDTO.setParagraph(post.getParagraph());
        expectedDTO.setTechnology(post.getTechnology());
        UpdatePostOutDTO result = postServiceImpl.getPostByPostId(postId);
        assertEquals(expectedDTO, result);
    }

    @Test
    public void testGetPostByPostIdFail() {
        String postId = "fdbshfbsk834";
        when(postRepository.findById("gfbds3728gdfs32"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    postServiceImpl.getPostByPostId(postId);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testGetAllUnapprovedPost() {
        User user1 = new User();
        user1.setFirstName("Name 1");
        user1.setLastName("Surname 1");
        user1.setDesignation(Designation.ARCHITECT);
        Post post1 = new Post();
        post1.setUser(user1);
        post1.setPostId("fhsdbfhjb231ds");
        post1.setHeading("Heading 1");
        post1.setParagraph("Paragraph 1");
        post1.setTechnology(Technology.MICROSERVICES);
        post1.setStatus(Status.PENDING);
        User user2 = new User();
        user2.setFirstName("Name 2");
        user2.setLastName("Surname 2");
        user2.setDesignation(Designation.DELIVERY_HEAD);
        Post post2 = new Post();
        post2.setUser(user2);
        post2.setPostId("hfdsyut37284");
        post2.setHeading("Heading 2");
        post2.setParagraph("Paragraph 2");
        post2.setTechnology(Technology.MICROSERVICES);
        post2.setStatus(Status.PENDING);
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        when(postRepository.getByStatusOrderByUpdatedAtDesc(Status.PENDING))
                .thenReturn(posts);
        List<UserPostOutDTO> result = postServiceImpl.getAllUnapprovedPost();
        UserPostOutDTO userPostOutDTO1 = new UserPostOutDTO();
        userPostOutDTO1.setFirstName(user1.getFirstName());
        userPostOutDTO1.setLastName(user1.getLastName());
        userPostOutDTO1.setDesignation(
                user1.getDesignation().toString().replace("_", " "));
        userPostOutDTO1.setPostId(post1.getPostId());
        userPostOutDTO1.setHeading(post1.getHeading());
        userPostOutDTO1.setParagraph(post1.getParagraph());
        userPostOutDTO1.setTechnology(
                post1.getTechnology().toString().replace("_", " "));
        userPostOutDTO1.setUpdatedAt(post1.getUpdatedAt());
        UserPostOutDTO userPostOutDTO2 = new UserPostOutDTO();
        userPostOutDTO2.setFirstName(user2.getFirstName());
        userPostOutDTO2.setLastName(user2.getLastName());
        userPostOutDTO2.setDesignation(
                user2.getDesignation().toString().replace("_", " "));
        userPostOutDTO2.setPostId(post2.getPostId());
        userPostOutDTO2.setHeading(post2.getHeading());
        userPostOutDTO2.setParagraph(post2.getParagraph());
        userPostOutDTO2.setTechnology(
                post2.getTechnology().toString().replace("_", " "));
        userPostOutDTO2.setUpdatedAt(post1.getUpdatedAt());
        List<UserPostOutDTO> userPostsOutDTO = new ArrayList<>();
        userPostsOutDTO.add(userPostOutDTO1);
        userPostsOutDTO.add(userPostOutDTO2);
        assertEquals(userPostsOutDTO, result);
    }

    @Test
    public void testPostApprovalSuccess() {
        PostApprovalInDTO approvalInDTO = new PostApprovalInDTO();
        approvalInDTO.setPostId("post123");
        approvalInDTO.setStatus(Status.APPROVED);
        Post pendingPost = new Post();
        pendingPost.setStatus(Status.PENDING);
        when(postRepository.findById(approvalInDTO.getPostId()))
                .thenReturn(java.util.Optional.of(pendingPost));
        ResponseOutDTO result = postServiceImpl.postApproval(approvalInDTO);
        assertEquals(ConstantMessages.APPROVE_POST, result.getMessage());
    }

    @Test
    public void testPostApprovalFail() {
        PostApprovalInDTO approvalInDTO = new PostApprovalInDTO();
        approvalInDTO.setPostId("post123");
        approvalInDTO.setStatus(Status.PENDING);
        when(postRepository.findById("post123")).thenReturn(Optional.empty());
        assertThrows(InvalidRecordException.class, () -> {
            postServiceImpl.postApproval(approvalInDTO);
        });
    }

    @Test
    public void testGetAllReportedPost() {
        User user1 = new User();
        user1.setFirstName("Name 1");
        user1.setLastName("Surname 1");
        user1.setDesignation(Designation.ARCHITECT);
        Post post1 = new Post();
        post1.setUser(user1);
        post1.setPostId("fhsdbfhjb231ds");
        post1.setHeading("Heading 1");
        post1.setParagraph("Paragraph 1");
        post1.setTechnology(Technology.MICROSERVICES);
        post1.setStatus(Status.PENDING);
        Report report1 = new Report();
        report1.setReportId("nfdsjk3r9y232");
        report1.setPost(post1);
        report1.setUser(user1);
        User user2 = new User();
        user2.setFirstName("Name 2");
        user2.setLastName("Surname 2");
        user2.setDesignation(Designation.DELIVERY_HEAD);
        Post post2 = new Post();
        post2.setUser(user2);
        post2.setPostId("hfdsyut37284");
        post2.setHeading("Heading 2");
        post2.setParagraph("Paragraph 2");
        post2.setTechnology(Technology.MICROSERVICES);
        post2.setStatus(Status.PENDING);
        Report report2 = new Report();
        report2.setReportId("gewtusy728643");
        report2.setPost(post2);
        report2.setUser(user2);
        List<Report> reports = new ArrayList<>();
        reports.add(report1);
        reports.add(report2);
        when(reportRepository.findAll()).thenReturn(reports);
        when(reportRepository.countByPost(post1)).thenReturn(14);
        when(reportRepository.countByPost(post2)).thenReturn(10);
        List<ReportedPostOutDTO> result = postServiceImpl.getAllReportedPost();
        ReportedPostOutDTO reportedPostOutDTO1 = new ReportedPostOutDTO();
        UserPostOutDTO userPostOutDTO1 = new UserPostOutDTO();
        userPostOutDTO1.setFirstName(user1.getFirstName());
        userPostOutDTO1.setLastName(user1.getLastName());
        userPostOutDTO1.setDesignation(
                user1.getDesignation().toString().replace("_", " "));
        userPostOutDTO1.setPostId(post1.getPostId());
        userPostOutDTO1.setHeading(post1.getHeading());
        userPostOutDTO1.setParagraph(post1.getParagraph());
        userPostOutDTO1.setTechnology(
                post1.getTechnology().toString().replace("_", " "));
        userPostOutDTO1.setUpdatedAt(post1.getUpdatedAt());
        reportedPostOutDTO1.setUserPostOutDTO(userPostOutDTO1);
        reportedPostOutDTO1.setReportCount(14);
        ReportedPostOutDTO reportedPostOutDTO2 = new ReportedPostOutDTO();
        UserPostOutDTO userPostOutDTO2 = new UserPostOutDTO();
        userPostOutDTO2.setFirstName(user2.getFirstName());
        userPostOutDTO2.setLastName(user2.getLastName());
        userPostOutDTO2.setDesignation(
                user2.getDesignation().toString().replace("_", " "));
        userPostOutDTO2.setPostId(post2.getPostId());
        userPostOutDTO2.setHeading(post2.getHeading());
        userPostOutDTO2.setParagraph(post2.getParagraph());
        userPostOutDTO2.setTechnology(
                post2.getTechnology().toString().replace("_", " "));
        userPostOutDTO2.setUpdatedAt(post1.getUpdatedAt());
        reportedPostOutDTO2.setUserPostOutDTO(userPostOutDTO2);
        reportedPostOutDTO2.setReportCount(10);
        List<ReportedPostOutDTO> reportedPostsOutDTO = new ArrayList<>();
        reportedPostsOutDTO.add(reportedPostOutDTO1);
        reportedPostsOutDTO.add(reportedPostOutDTO2);
        Collections.reverse(reportedPostsOutDTO);
        assertEquals(reportedPostsOutDTO, result);
    }

    @Test
    public void testDeletePostSuccess() {
        Post post = new Post();
        post.setPostId("fjkbdhs231");
        when(postRepository.findById(post.getPostId()))
                .thenReturn(java.util.Optional.of(post));
        ResponseOutDTO result = postServiceImpl.deletePost(post.getPostId());
        commentRepository.deleteByPost(post);
        reactionRepository.deleteByPost(post);
        reportRepository.deleteByPost(post);
        postRepository.deleteById("post123");
        assertEquals(ConstantMessages.DELETE_POST, result.getMessage());
    }

    @Test
    public void testDeletePostFail() {
        String postId = "fhdnhk8324fd";
        when(userRepository.findById(postId)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> {
            postServiceImpl.deletePost(postId);
        });
    }

    @Test
    public void testGetAllPostByUserIdSuccess() {
        MyPostInDTO myPostInDTO = new MyPostInDTO();
        myPostInDTO.setUserId("jdgbs237848y");
        myPostInDTO.setHeading("Heading");
        myPostInDTO.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        myPostInDTO.setStatus(Status.APPROVED);
        User user = new User();
        user.setUserId("jdgbs237848y");
        user.setFirstName("Name");
        user.setLastName("Surname");
        user.setDesignation(Designation.ARCHITECT);
        user.setEmail("xyz@nucleusteq.com");
        user.setPassword("XYZ@12345");
        user.setMobile("999999999");
        user.setGender(Gender.MALE);
        user.setCreatedAt("20-01-2023 13:23:53");
        Post post = new Post();
        post.setUser(user);
        post.setPostId("fhsdbfhjb231ds");
        post.setHeading("Heading");
        post.setParagraph("Paragraph");
        post.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        post.setStatus(Status.APPROVED);
        post.setCreatedAt("13-02-2023 23:13:54");
        post.setUpdatedAt("23-04-2023 13:23:53");
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(userRepository.findById(myPostInDTO.getUserId()))
                .thenReturn(Optional.of(user));
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
        when(mongoTemplate.find(query.with(sort), Post.class))
                .thenReturn(posts);
        Reaction reaction = new Reaction();
        reaction.setReactionId("hsdysfs432");
        reaction.setPost(post);
        reaction.setUser(user);
        reaction.setReaction(false);
        when(reactionRepository.countByPostAndReaction(post, true))
                .thenReturn(4);
        when(reactionRepository.countByPostAndReaction(post, false))
                .thenReturn(2);
        when(reactionRepository.getByUserAndPost(user, post))
                .thenReturn(reaction);
        when(commentRepository.countByPost(post)).thenReturn(2);
        List<GetAllMyPostOutDTO> result = postServiceImpl
                .getAllPostByUserId(myPostInDTO);
        List<GetAllMyPostOutDTO> expected = new ArrayList<>();
        GetAllMyPostOutDTO getAllMyPostOutDTO = new GetAllMyPostOutDTO();
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName(user.getFirstName());
        userPostOutDTO.setLastName(user.getLastName());
        userPostOutDTO.setDesignation(
                user.getDesignation().toString().replace("_", " "));
        userPostOutDTO.setHeading(post.getHeading());
        userPostOutDTO.setParagraph(post.getParagraph());
        userPostOutDTO.setPostId(post.getPostId());
        userPostOutDTO.setTechnology(
                Technology.BUSINESS_INTELLIGENCE.toString().replace("_", " "));
        userPostOutDTO.setUpdatedAt(post.getUpdatedAt());
        getAllMyPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        getAllMyPostOutDTO.setLike(false);
        getAllMyPostOutDTO.setDislike(true);
        getAllMyPostOutDTO.setLikeCount(4);
        getAllMyPostOutDTO.setDislikeCount(2);
        getAllMyPostOutDTO.setCommentCount(2);
        getAllMyPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        getAllMyPostOutDTO.setStatus(Status.APPROVED);
        expected.add(getAllMyPostOutDTO);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllPostByUserIdFail() {
        MyPostInDTO myPostInDTO = new MyPostInDTO();
        myPostInDTO.setUserId("jdgbs237848y");
        myPostInDTO.setHeading("Heading");
        myPostInDTO.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        myPostInDTO.setStatus(Status.APPROVED);
        when(userRepository.findById("hfdsru327642"))
                .thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> {
            postServiceImpl.getAllPostByUserId(myPostInDTO);
        });
    }

    @Test
    public void testGetAllApprovedPostSuccess() {
        GetPostInDTO getPostInDTO = new GetPostInDTO();
        getPostInDTO.setUserId("jdgbs237848y");
        getPostInDTO.setHeading("Heading");
        getPostInDTO.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        User user = new User();
        user.setUserId("jdgbs237848y");
        user.setFirstName("Name 1");
        user.setLastName("Surname 1");
        user.setDesignation(Designation.ARCHITECT);
        user.setEmail("xyz@nucleusteq.com");
        user.setPassword("XYZ@12345");
        user.setMobile("999999999");
        user.setGender(Gender.MALE);
        user.setCreatedAt("20-01-2023 13:23:53");
        Post post = new Post();
        post.setUser(user);
        post.setPostId("fhsdbfhjb231ds");
        post.setHeading("Heading");
        post.setParagraph("Paragraph");
        post.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        post.setStatus(Status.APPROVED);
        post.setCreatedAt("13-02-2023 23:13:54");
        post.setUpdatedAt("23-04-2023 13:23:53");
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(userRepository.findById(getPostInDTO.getUserId()))
                .thenReturn(Optional.of(user));
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
        when(mongoTemplate.find(query.with(sort), Post.class))
                .thenReturn(posts);
        Reaction reaction = new Reaction();
        reaction.setReactionId("hsdysfs432");
        reaction.setPost(post);
        reaction.setUser(user);
        reaction.setReaction(false);
        when(reactionRepository.countByPostAndReaction(post, true))
                .thenReturn(4);
        when(reactionRepository.countByPostAndReaction(post, false))
                .thenReturn(2);
        when(reactionRepository.getByUserAndPost(user, post))
                .thenReturn(reaction);
        when(reportRepository.existsByPostAndUser(post, user)).thenReturn(true);
        when(reportRepository.countByPost(post)).thenReturn(3);
        when(commentRepository.countByPost(post)).thenReturn(2);
        List<GetAllPostOutDTO> result = postServiceImpl
                .getAllApprovePosts(getPostInDTO);
        List<GetAllPostOutDTO> expected = new ArrayList<>();
        GetAllPostOutDTO getAllMyPostOutDTO = new GetAllPostOutDTO();
        UserPostOutDTO userPostOutDTO = new UserPostOutDTO();
        userPostOutDTO.setFirstName(user.getFirstName());
        userPostOutDTO.setLastName(user.getLastName());
        userPostOutDTO.setDesignation(
                user.getDesignation().toString().replace("_", " "));
        userPostOutDTO.setHeading(post.getHeading());
        userPostOutDTO.setParagraph(post.getParagraph());
        userPostOutDTO.setPostId(post.getPostId());
        userPostOutDTO.setTechnology(
                Technology.BUSINESS_INTELLIGENCE.toString().replace("_", " "));
        userPostOutDTO.setUpdatedAt(post.getUpdatedAt());
        getAllMyPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        getAllMyPostOutDTO.setLike(false);
        getAllMyPostOutDTO.setDislike(true);
        getAllMyPostOutDTO.setReport(true);
        getAllMyPostOutDTO.setLikeCount(4);
        getAllMyPostOutDTO.setDislikeCount(2);
        getAllMyPostOutDTO.setCommentCount(2);
        getAllMyPostOutDTO.setReportCount(3);
        getAllMyPostOutDTO.setMyPost(true);;
        getAllMyPostOutDTO.setUserPostOutDTO(userPostOutDTO);
        expected.add(getAllMyPostOutDTO);
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllApprovedPostFail() {
        GetPostInDTO getPostInDTO = new GetPostInDTO();
        getPostInDTO.setUserId("jdgbs237848y");
        getPostInDTO.setHeading("Heading");
        getPostInDTO.setTechnology(Technology.BUSINESS_INTELLIGENCE);
        when(userRepository.findById("hfdsru327642"))
                .thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> {
            postServiceImpl.getAllApprovePosts(getPostInDTO);
        });
    }
}
