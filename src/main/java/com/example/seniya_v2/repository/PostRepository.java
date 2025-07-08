package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);
    List<Post> findByUserRoleRoleName(String roleName);
    @Query("SELECT p FROM Post p LEFT JOIN p.comments c GROUP BY p.postId ORDER BY COUNT(c) DESC")
    List<Post> findTopPostsByCommentCount(Pageable pageable);
}
