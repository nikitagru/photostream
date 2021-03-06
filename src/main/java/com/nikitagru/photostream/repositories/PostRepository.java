package com.nikitagru.photostream.repositories;

import com.nikitagru.photostream.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/***
 * Repository for posts
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /***
     * Inserts into table "users_posts" new posts
     * @param userId user's id of the post
     * @param postId post id (generate automatically by spring)
     */
    @Query(value = "INSERT INTO users_posts (user_id, post_id) VALUES (:#{#userid}, :#{#postid})", nativeQuery = true)
    @Modifying
    @Transactional
    void createUserPost(@Param("userid") Long userId, @Param("postid") Long postId);
}
