package com.madhur.blog_portal.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.madhur.blog_portal.Service.ReportService;
import com.madhur.blog_portal.Utilities.ConstantMessages;

/**
 * Service implementation for managing reports on posts.
 */
@Service
public class ReportServiceImpl implements ReportService {
    /**
     * ReportRepository instance.
     */
    @Autowired
    private ReportRepository reportRepository;
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
     * Updates/creates/delete a report on a post by a user.
     * @param reportInDTO The input DTO containing user ID, post ID, and the
     *            report message.
     * @return A string indicating whether the report was updated or created
     *         successfully.
     * @throws RecordNotFoundException if the user or post is not found.
     */
    @Override
    public ResponseOutDTO updateReport(final ReportInDTO reportInDTO) {
        User user = userRepository.findById(reportInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = postRepository.findById(reportInDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        Report existingReport = reportRepository.getByUserAndPost(user, post);
        if (Objects.isNull(existingReport)) {
            Report report = new Report();
            report.setPost(post);
            report.setUser(user);
            reportRepository.save(report);
        } else {
            reportRepository.delete(existingReport);
        }
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REPORT);
        return responseOutDTO;
    }

    /**
     * delete post with post ID.
     */
    @Override
    public ResponseOutDTO deleteReport(final String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        if (reportRepository.existsByPost(post)) {
            reportRepository.deleteByPost(post);
            ResponseOutDTO responseOutDTO = new ResponseOutDTO();
            responseOutDTO.setMessage(ConstantMessages.DELETE_REPORT);
            return responseOutDTO;
        } else {
            throw new InvalidRecordException(ConstantMessages.REPORT_NOT_EXIT);
        }
    }
}
