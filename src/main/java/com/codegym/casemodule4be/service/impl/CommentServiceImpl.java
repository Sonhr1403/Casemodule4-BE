package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.Comment;
import com.codegym.casemodule4be.model.Image;
import com.codegym.casemodule4be.repository.CommentRepository;
import com.codegym.casemodule4be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService<Comment> {
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.get().setActive(0);
        commentRepository.save(comment.get());
    }

    @Override
    public Iterable<Comment> findAllByStatus(Long statusId) {
        return commentRepository.findAllByStatus(statusId);
    }

    @Override
    public Integer findNumberOfComment(Long statusId) {
        return commentRepository.findNumberOfComment(statusId);
    }
}
