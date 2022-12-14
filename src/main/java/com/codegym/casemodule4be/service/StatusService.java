package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.Status;


import java.util.Optional;

public interface StatusService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

    T findLastStatus();

    Iterable<Status> findAllByOwnerFriend( Long id);

    Iterable<T> findAllByOwner(Long id);

    Iterable<T> findAllByStranger(Long id);
}
