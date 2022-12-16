package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.LikeStatus;

import java.util.Optional;

public interface LikeStatusService {
    void save(LikeStatus likeStatus);

    Optional<LikeStatus> findById(Long id);

    void delete(Long id);

    Iterable<LikeStatus> findAll();

    LikeStatus findByUserLikeIdAndAndStatusId(Long idUser, Long idStatus);

    Integer findNumberOfLikeByStatus(Long statusId);
}
