package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.LikePost;

import java.util.Optional;

public interface LikePostService {
    void save(LikePost likePost);

    Optional<LikePost> findById(Long id);

    void delete(Long id);

    Iterable<LikePost> findAll();

    LikePost findByUserLikeIdAndPostId(Long idUser, Long idPost);

    Integer findNumberOfLikeByPost(Long idPost);
}
