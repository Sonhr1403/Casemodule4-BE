package com.codegym.casemodule4be.controller;

import com.codegym.casemodule4be.model.Relationship;
import com.codegym.casemodule4be.service.impl.RelationshipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/relationships")
public class RelationshipController {

    @Autowired
    RelationshipServiceImpl relationshipService;

    @PostMapping()
    public ResponseEntity<Relationship> addFriend(@RequestBody Relationship relationship) {
        relationship.setStatus(0);
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relationship> confirmRelationship(@PathVariable Long id) {
        Relationship relationship = new Relationship();
        Optional<Relationship> relationshipOptional = this.relationshipService.findById(id);
        if (!relationshipOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        relationship.setId(relationshipOptional.get().getId());
        relationship.setUser1(relationshipOptional.get().getUser1());
        relationship.setUser2(relationshipOptional.get().getUser2());
        relationship.setStatus(1);
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRelationship(@PathVariable Long id) {
        Optional<Relationship> relationshipOptional = relationshipService.findById(id);
        if (!relationshipOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        relationshipService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search-relationship")
    public ResponseEntity<Relationship> findRelationship(@RequestParam("id1") Long id1, @RequestParam("id2") Long id2) {
        Relationship relationship = relationshipService.findRelationship(id1, id2);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @GetMapping("/{id}/find-all-friend-by-userId")
    public ResponseEntity<Iterable<Relationship>> findAllFriendByUserId(@PathVariable Long id) {
        Iterable<Relationship> relationships = relationshipService.findAllFriendByUserId(id);
        return new ResponseEntity<>(relationships, HttpStatus.OK);
    }

    @GetMapping("/find-mutual-friends")
    public ResponseEntity<Iterable<Relationship>> findMutualFriend(@RequestParam Long currentId,@RequestParam Long id) {
        Iterable<Relationship> relationships = relationshipService.findMutualFriend(currentId,id);
        return new ResponseEntity<>(relationships, HttpStatus.OK);
    }
}

