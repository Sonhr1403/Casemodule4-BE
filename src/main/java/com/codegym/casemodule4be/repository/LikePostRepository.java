package com.codegym.casemodule4be.repository;

import com.codegym.casemodule4be.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    @Query(value = "select count(id) from like_post where post_id = :id group by post_id", nativeQuery = true)
    Integer findNumberOfLikeByStatus(@Param("id") Long idPost);

    LikePost findByUserLikeIdAndAndPostId(Long idUser, Long idPost);
}