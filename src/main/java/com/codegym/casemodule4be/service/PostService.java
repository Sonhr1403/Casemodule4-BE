package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.Post;


import java.util.Optional;

public interface PostService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

    T findLastPost();

    Iterable<Post> findAllByOwnerFriend(Long id);

    Iterable<T> findAllByOwner(Long id);

    Iterable<T> findAllByStranger(Long id);
}
