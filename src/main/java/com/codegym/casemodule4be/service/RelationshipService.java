package com.codegym.casemodule4be.service;


import com.codegym.casemodule4be.model.Relationship;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RelationshipService {

    void save(Relationship relationship);

    Optional<Relationship> findById(Long id);

    void delete(Long id);

    Relationship findRelationship(Long id1, Long id2);

    Iterable<Relationship> findAllFriendByUserId(Long id);

    Iterable<Relationship> findMutualFriend(@Param("currentId") Long currentId, @Param("id") Long id);
}

