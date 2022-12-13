package com.codegym.casemodule4be.service;


import java.util.Optional;

public interface CommentService <T>{
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

    Iterable<T> findAllByStatus(Long statusId);

    Integer findNumberOfComment(Long statusId);
}
