package com.codegym.casemodule4be.repository;



import com.codegym.casemodule4be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comment where status_id = :statusId and active <> 0\n" +
            "order by comment_id asc, create_at desc", nativeQuery = true)
    Iterable<Comment> findAllByStatus(@Param("statusId") Long id);

    @Query(value = "select count(id) from comment where status_id = :id group by status_id", nativeQuery = true)
    Integer findNumberOfComment(@Param("id") Long statusId);
}
