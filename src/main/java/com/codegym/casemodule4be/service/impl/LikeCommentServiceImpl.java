package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.LikeComment;
import com.codegym.casemodule4be.repository.LikeCommentRepository;
import com.codegym.casemodule4be.service.LikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {
    @Autowired
    private LikeCommentRepository likeCommentRepository;


    @Override
    public void save(LikeComment likeComment) {
        likeCommentRepository.save(likeComment);

    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return findById(id);
    }

    @Override
    public void delete(Long id) {
        likeCommentRepository.deleteById(id);

    }

    @Override
    public Iterable<LikeComment> findAll() {
        return likeCommentRepository.findAll();
    }

    @Override
    public LikeComment findByUserLikeIdAndAndCommentId(Long idUser, Long idComment) {
        return likeCommentRepository.findByUserLikeIdAndAndCommentId(idUser,idComment);
    }

    @Override
    public Integer findNumberOfLikeCommentOfComment(Long commentId) {
        return likeCommentRepository.findNumberOfLikeCommentOfComment(commentId);
    }
}
