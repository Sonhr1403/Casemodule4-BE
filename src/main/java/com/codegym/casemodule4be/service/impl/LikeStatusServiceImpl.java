package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.LikeStatus;
import com.codegym.casemodule4be.repository.LikeStatusRepository;
import com.codegym.casemodule4be.service.LikeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeStatusServiceImpl implements LikeStatusService {
    @Autowired
    private LikeStatusRepository likeStatusRepository;

    @Override
    public void save(LikeStatus likeStatus) {
        likeStatusRepository.save(likeStatus);
    }

    @Override
    public Optional<LikeStatus> findById(Long id) {
        return likeStatusRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        likeStatusRepository.deleteById(id);
    }

    @Override
    public Iterable<LikeStatus> findAll() {
        return likeStatusRepository.findAll();
    }

    @Override
    public LikeStatus findByUserLikeIdAndAndStatusId(Long idUser, Long idStatus) {
        return likeStatusRepository.findByUserLikeIdAndAndStatusId(idUser,idStatus);
    }

    @Override
    public Integer findNumberOfLikeByStatus(Long statusId) {
        return likeStatusRepository.findNumberOfLikeByStatus(statusId);
    }
}
