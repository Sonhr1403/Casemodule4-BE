package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.Image;

import java.util.Optional;

public interface ImageService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

    Iterable<Image> findAllByPost(Long id);

    void deleteAllByPost(Long postId);

    Iterable<Image> findAllImageByUserId(Long idUser);

    Iterable<Image> top5ImageByUserId( Long idUser);
}
