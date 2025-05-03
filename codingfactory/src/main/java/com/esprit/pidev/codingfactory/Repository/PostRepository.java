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

    @Query("SELECT p FROM Post p WHERE p.type = 'question' AND (:user_id IS NULL OR p.user_id != :user_id)")
    List<Post> findAllPosts(@Param("user_id") String user_id);

    @Query("SELECT p FROM Post p WHERE p.type = 'question'")
    List<Post> findAllPosts();

    @Query("SELECT DISTINCT p.tags FROM Post p WHERE p.tags IS NOT NULL AND p.tags <> '' AND p.type = 'question'")
    List<String> findDistinctTechnologies();
    @Query("SELECT  p.tags FROM Post p WHERE p.tags IS NOT NULL AND p.tags <> '' AND p.type = 'question'")
    List<String> findTechnologies();
    @Query("SELECT p FROM Post p where p.parent_post_id = :parent_id")
    List<Post> findCommentsByPostId(@Param("parent_id") int parent_id);

    @Query("SELECT p FROM Post p where p.user_id = :userId and p.type = 'question' ")
    List<Post> getPostsByUser_id(@Param("userId") String userId);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.type = 'question'")
    Long countTotalPosts();

    @Query("SELECT COUNT(p) FROM Post p WHERE p.type = 'response'")
    Long countTotalComments();

    @Query("SELECT COUNT(p) FROM Post p WHERE p.bestAnswerId IS NOT NULL")
    Long countTotalBestAnswers();

    @Query("""
                SELECT
                    p.user_id,
                    SUM(CASE WHEN p.type = 'question' THEN 1 ELSE 0 END) as questionCount,
                    SUM(CASE WHEN p.type = 'response' THEN 1 ELSE 0 END) as answerCount,
                    COUNT(bp) as bestAnswerCount
                FROM Post p
                LEFT JOIN Post bp ON bp.bestAnswerId = p.id
                GROUP BY p.user_id
                ORDER BY bestAnswerCount DESC
            """)
    List<Object[]> findTopUsers();

    @Query("SELECT p.user_id, COUNT(p2.bestAnswerId) as bestAnswerCount FROM Post p LEFT JOIN Post p2 ON p2.bestAnswerId = p.id GROUP BY p.user_id ORDER BY bestAnswerCount DESC")
    List<Object[]> findTopBestAnswerers();

}
