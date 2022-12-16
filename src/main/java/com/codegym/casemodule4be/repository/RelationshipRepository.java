package com.codegym.casemodule4be.repository;

import com.codegym.casemodule4be.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query(value = "select * from relationship where ((user1_id = :id1 and user2_id = :id2) or (user1_id = :id2 and user2_id = :id1)) and status <> 0", nativeQuery = true)
    Relationship findRelationship(@Param("id1") Long id1, @Param("id2") Long id2);

    @Query(value = "select relationship.id, user1_id, user2_id, relationship.status  from user_table join relationship on (user_table.id = relationship.user2_id) or (user_table.id=relationship.user1_id)\n" +
            "where user_table.id = :id and relationship.status = 2", nativeQuery = true)
    Iterable<Relationship> findAllFriendByUserId(@Param("id") Long id);

    @Query(value = "select relationship.id, user1_id, user2_id, relationship.status from relationship\n" +
            "where (user1_id in (\n" +
            "    select t1.userTemp from (\n" +
            "                                select\n" +
            "                                       (Case\n" +
            "                                            when user1_id = :id then user2_id\n" +
            "                                            when user2_id = :id then user1_id\n" +
            "                                           end) as userTemp\n" +
            "                                from user_table\n" +
            "                                         join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "                                where user_table.id = :id\n" +
            "                                  and relationship.status = 2) as t1\n" +
            "                                join (\n" +
            "        select relationship.id,\n" +
            "               (Case\n" +
            "                    when user1_id = :currentId then user2_id\n" +
            "                    when user2_id = :currentId then user1_id\n" +
            "                   end) as userTemp,\n" +
            "               relationship.status\n" +
            "        from user_table\n" +
            "                 join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "        where user_table.id = :currentId\n" +
            "          and relationship.status = 2) as t2\n" +
            "    where t1.userTemp = t2.userTemp) and user2_id = :id) or (user2_id in (\n" +
            "    select t1.userTemp from (\n" +
            "                                select\n" +
            "                                       (Case\n" +
            "                                            when user1_id = :id then user2_id\n" +
            "                                            when user2_id = :id then user1_id\n" +
            "                                           end) as userTemp\n" +
            "                                from user_table\n" +
            "                                         join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "                                where user_table.id = :id\n" +
            "                                  and relationship.status = 2) as t1\n" +
            "                                join (\n" +
            "        select relationship.id,\n" +
            "               (Case\n" +
            "                    when user1_id = :currentId then user2_id\n" +
            "                    when user2_id = :currentId then user1_id\n" +
            "                   end) as userTemp,\n" +
            "               relationship.status\n" +
            "        from user_table\n" +
            "                 join relationship on (user_table.id = relationship.user2_id) or (user_table.id = relationship.user1_id)\n" +
            "        where user_table.id = :currentId\n" +
            "          and relationship.status = 2) as t2\n" +
            "    where t1.userTemp = t2.userTemp) and user1_id = :id)", nativeQuery = true)
    Iterable<Relationship> findMutualFriend(@Param("currentId") Long currentId, @Param("id") Long id);

}