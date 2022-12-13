package com.codegym.casemodule4be.repository;


import com.codegym.casemodule4be.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query(value = "select * from status order by id desc limit 1", nativeQuery = true)
    Status findLastStatus();

    @Query(value = "select * from status where owner_id = :id and status <> 0 order by create_at desc", nativeQuery = true)
    Iterable<Status> findAllByOwner(@Param("id") Long id);

    @Query(value = "select *\n" +
            " from status\n" +
            " where owner_id in\n" +
            "       (select (Case\n" +
            "                    when user1_id = :id then user2_id\n" +
            "                    when user2_id = :id then user1_id\n" +
            "           end) as userTemp\n" +
            "        from user_table\n" +
            "                 join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "        where user_table.id = :id\n" +
            "          and relationship.status = 2) and (status=1 or status=3)\n" +
            " order by create_at desc", nativeQuery = true)
    Iterable<Status> findAllByOwnerFriend(@Param("id") Long id);

    @Query(value = "select *\n" +
            " from status\n" +
            " where owner_id not in\n" +
            "       (select (Case\n" +
            "                    when user1_id = :id then user2_id\n" +
            "                    when user2_id = :id then user1_id\n" +
            "           end) as userTemp\n" +
            "        from user_table\n" +
            "                 join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "        where user_table.id = :id\n" +
            "          and relationship.status = 2) and status = 1\n" +
            "   and owner_id <> :id\n" +
            " order by create_at desc", nativeQuery = true)
    Iterable<Status> findAllByStranger(@Param("id") Long id);
}