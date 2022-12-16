package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.LikePost;
import com.codegym.casemodule4be.repository.LikePostRepository;
import com.codegym.casemodule4be.service.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostServiceImpl implements LikePostService {
    @Autowired
    private LikePostRepository likePostRepository;

    @Override
    public void save(LikePost likePost) {
        likePostRepository.save(likePost);
    }

    @Override
    public Optional<LikePost> findById(Long id) {
        return likePostRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public Iterable<LikePost> findAll() {
        return likePostRepository.findAll();
    }

    @Override
    public LikePost findByUserLikeIdAndPostId(Long idUser, Long idPost) {
        return likePostRepository.findByUserLikeIdAndAndPostId(idUser, idPost);
    }

    @Override
    public Integer findNumberOfLikeByPost(Long postId) {
        return likePostRepository.findNumberOfLikeByStatus(postId);
    }
}