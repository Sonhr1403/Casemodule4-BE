package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.Relationship;
import com.codegym.casemodule4be.repository.RelationshipRepository;
import com.codegym.casemodule4be.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationshipServiceImpl implements RelationshipService {
    @Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public void save(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    @Override
    public Optional<Relationship>findById(Long id) {
        return relationshipRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        relationshipRepository.deleteById(id);
    }

    @Override
    public Relationship findRelationship(Long id1, Long id2) {
        return relationshipRepository.findRelationship(id1,id2);
    }

    @Override
    public Iterable<Relationship> findAllFriendByUserId(Long id) {
        return relationshipRepository.findAllFriendByUserId(id);
    }

    @Override
    public Iterable<Relationship> findMutualFriend(Long currentId, Long id) {
        return relationshipRepository.findMutualFriend(currentId,id);
    }


}
