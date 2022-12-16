package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.Post;
import com.codegym.casemodule4be.repository.PostRepository;
import com.codegym.casemodule4be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class PostServiceImpl implements PostService<Post> {
    @Autowired
    private PostRepository repository;


    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Post post) {
        repository.save(post);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Post findLastPost() {
        return repository.findLastPost();
    }

    @Override
    public Iterable<Post> findAllByOwnerFriend(Long id) {
        return repository.findAllByOwnerFriend(id);
    }

    @Override
    public Iterable<Post> findAllByOwner(Long id) {
        return repository.findAllByOwner(id);
    }

    @Override
    public Iterable<Post> findAllByStranger(Long id) {
        return repository.findAllByStranger(id);
    }
}
