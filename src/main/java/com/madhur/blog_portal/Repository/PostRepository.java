package com.madhur.blog_portal.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madhur.blog_portal.Model.Post;
import com.madhur.blog_portal.Model.User;
import com.madhur.blog_portal.Utilities.Status;
import com.madhur.blog_portal.Utilities.Technology;

/**
 * Repository for managing posts.
 */
public interface PostRepository extends MongoRepository<Post, String> {
    /**
     * Get posts by status.
     * @param status The status of posts to retrieve.
     * @return A list of posts with the specified status.
     */
    List<Post> getByStatusOrderByUpdatedAtDesc(Status status);
    /**
     * Get posts by user.
     * @param user The user whose posts are to be retrieved.
     * @param status Status of post.
     * @return A list of posts created by the specified user.
     */
    List<Post> searchByUserAndStatusOrderByUpdatedAtDesc(User user,
            Status status);
    /**
     * @param user The user whose posts are to be retrieved.
     * @return A list of posts created by the this user.
     */
    List<Post> getByUserOrderByUpdatedAtDesc(User user);
    /**
     * Search for posts by heading, technology, and status.
     * @param heading The heading to search for in posts.
     * @param technology The technology associated with posts.
     * @param status The status of posts to retrieve.
     * @return A list of posts matching the search criteria.
     */
    List<Post> searchByHeadingContainsAllIgnoreCaseAndTechnologyAndStatusOrderByUpdatedAtDesc(
            String heading, Technology technology, Status status);
    /**
     * Search for posts by heading and status.
     * @param heading The heading to search for in posts.
     * @param status The status of posts to retrieve.
     * @return A list of posts matching the search criteria.
     */
    List<Post> searchByHeadingContainsAllIgnoreCaseAndStatusOrderByUpdatedAtDesc(
            String heading, Status status);
    /**
     * Get posts by technology and status.
     * @param technology The technology associated with posts.
     * @param status The status of posts to retrieve.
     * @return A list of posts with the specified technology and status.
     */
    List<Post> getByTechnologyAndStatusOrderByUpdatedAtDesc(
            Technology technology, Status status);
}
