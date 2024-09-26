package com.madhur.blog_portal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhur.blog_portal.DTO.InDTO.ReportInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Exception.InvalidRecordException;
import com.madhur.blog_portal.Exception.RecordNotFoundException;
import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Report;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Repository.PostRepository;
import com.madhur.blog_portal.Repository.ReportRepository;
import com.madhur.blog_portal.Repository.UserRepository;
import com.madhur.blog_portal.ServiceImpl.ReportServiceImpl;
import com.madhur.blog_portal.Utilities.ConstantMessages;

public class ReportServiceTest {
    @InjectMocks
    private ReportServiceImpl reportServiceImpl;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateReportSucess() {
        ReportInDTO reportDTO = new ReportInDTO();
        reportDTO.setUserId("gdysfghsdf321");
        reportDTO.setPostId("fhdsyry764932r");
        Post post = new Post();
        post.setPostId("fhdsyry764932r");
        User user = new User();
        user.setUserId("gdysfghsdf321");
        user.setFirstName("Name 1");
        user.setLastName("Surname 1");
        Report report = new Report();
        report.setUser(user);
        report.setPost(post);
        report.setReportId("fdskfhbds");
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));
        when(postRepository.findById(post.getPostId()))
                .thenReturn(Optional.of(post));
        when(reportRepository.getByUserAndPost(user, post)).thenReturn(report);
        ResponseOutDTO result = reportServiceImpl.updateReport(reportDTO);
        assertEquals(ConstantMessages.UPDATE_REPORT, result.getMessage());
    }

    @Test
    public void updateReportSuccessNotExistTest() {
        Post post = new Post();
        post.setPostId("fhdsyry764932r");
        User user = new User();
        user.setUserId("gdysfghsdf321");
        user.setFirstName("Name 1");
        user.setLastName("Surname 1");
        ReportInDTO reportInDTO = new ReportInDTO();
        reportInDTO.setUserId("gdysfghsdf321");
        reportInDTO.setPostId("fhdsyry764932r");
        when(userRepository.findById("gdysfghsdf321"))
                .thenReturn(Optional.of(new User()));
        when(postRepository.findById("fhdsyry764932r"))
                .thenReturn(Optional.of(new Post()));
        when(reportRepository.getByUserAndPost(user, post)).thenReturn(null);
        ResponseOutDTO result = reportServiceImpl.updateReport(reportInDTO);
        assertEquals(ConstantMessages.UPDATE_REPORT, result.getMessage());
    }

    @Test
    public void updateReportFailUserNotFound() {
        ReportInDTO reportDTO = new ReportInDTO();
        reportDTO.setUserId("gdysfghsdf321");
        when(userRepository.findById("fudshbfew43"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    reportServiceImpl.updateReport(reportDTO);
                });
        assertEquals(ConstantMessages.USER_NOT_FOUND, result.getMessage());
    }

    @Test
    public void updateReportFailPostNotFound() {
        ReportInDTO reportDTO = new ReportInDTO();
        reportDTO.setUserId("gdysfghsdf321");
        reportDTO.setPostId("fdbshy372429");
        when(userRepository.findById("gdysfghsdf321"))
                .thenReturn(Optional.of(new User()));
        when(postRepository.findById("fdniy7324324"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    reportServiceImpl.updateReport(reportDTO);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testDeleteReportSuccess() {
        String postId = "dnfbhsg34234";
        Post post = new Post();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(reportRepository.existsByPost(post)).thenReturn(true);
        ResponseOutDTO result = reportServiceImpl.deleteReport(postId);
        assertEquals(ConstantMessages.DELETE_REPORT, result.getMessage());
    }

    @Test
    public void testDeleteReportPostNotFound() {
        String postId = "nfdshg32482";
        when(postRepository.findById("fndsjyr39487523"))
                .thenReturn(Optional.empty());
        RecordNotFoundException result = assertThrows(
                RecordNotFoundException.class, () -> {
                    reportServiceImpl.deleteReport(postId);
                });
        assertEquals(ConstantMessages.POST_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testDeleteReportNotExist() {
        String postId = "nfdshg32482";
        Post post = new Post();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(reportRepository.existsByPost(post)).thenReturn(false);
        InvalidRecordException result = assertThrows(
                InvalidRecordException.class, () -> {
                    reportServiceImpl.deleteReport(postId);
                });
        assertEquals(ConstantMessages.REPORT_NOT_EXIT, result.getMessage());
    }
}
