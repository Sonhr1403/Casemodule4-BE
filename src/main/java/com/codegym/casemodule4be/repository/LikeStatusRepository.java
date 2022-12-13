package com.codegym.casemodule4be.repository;

import com.codegym.casemodule4be.model.LikeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeStatusRepository extends JpaRepository<LikeStatus, Long> {
    @Query(value = "select count(id) from like_status where status_id = :id group by status_id", nativeQuery = true)
    Integer findNumberOfLikeByStatus(@Param("id") Long statusId);
    LikeStatus findByUserLikeIdAndAndStatusId(Long idUser,Long idStatus);
}
