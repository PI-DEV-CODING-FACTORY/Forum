package com.esprit.pidev.codingfactory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.pidev.codingfactory.Entity.Post;

import jakarta.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Post p SET " +
            "p.title = :#{#p.title}, " +
            "p.content = :#{#p.content}, " +
            "p.createdAt = :#{#p.createdAt} " +
            "WHERE p.id = :id")
    void updatePostById(@Param("p") Post p, @Param("id") int id);

    @Query("SELECT p FROM Post p WHERE p.type = 'question' AND (:user_id IS NULL OR p.user.id != :user_id)")
    List<Post> findAllPosts(@Param("user_id") int user_id);

    @Query("SELECT p FROM Post p WHERE p.type = 'question'")
    List<Post> findAllPosts();

    @Query("SELECT DISTINCT p.tags FROM Post p WHERE p.tags IS NOT NULL AND p.tags <> '' AND p.type = 'question'")
    List<String> findDistinctTechnologies();

    @Query("SELECT p FROM Post p where p.parent_post_id = :parent_id")
    List<Post> findCommentsByPostId(@Param("parent_id") int parent_id);

    @Query("SELECT p FROM Post p where p.user.id = :userId and p.type = 'question' ")
    List<Post> getPostsByUser_id(@Param("userId") int userId);
}
