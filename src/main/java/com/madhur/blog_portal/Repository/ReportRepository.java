package com.madhur.blog_portal.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.Report;
import com.madhur.blog_portal.Model.User;

/**
 * Repository for managing reports.
 */
public interface ReportRepository extends MongoRepository<Report, String> {
    /**
     * Delete reports associated with a specific post.
     * @param post The post for which reports should be deleted.
     */
    void deleteByPost(Post post);
    /**
     * Count the number of reports for a specific post.
     * @param post The post for which the report count is needed.
     * @return The number of reports for the specified post.
     */
    int countByPost(Post post);
    /**
     * Get a report by user and post.
     * @param user The user who reported the post.
     * @param post The post that was reported.
     * @return The report associated with the user and post.
     */
    Report getByUserAndPost(User user, Post post);
    /**
     * @param post that want to know exist or not
     * @param user that want to know exist or not
     * @return return exist or not
     */
    boolean existsByPostAndUser(Post post, User user);
    /**
     * @param post that want to know exist or not
     * @return return exist or not
     */
    boolean existsByPost(Post post);
}
