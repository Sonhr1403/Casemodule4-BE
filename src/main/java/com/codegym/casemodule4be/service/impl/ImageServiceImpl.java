package com.codegym.casemodule4be.service.impl;

import com.codegym.casemodule4be.model.Image;
import com.codegym.casemodule4be.repository.ImageRepository;
import com.codegym.casemodule4be.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService<Image> {
    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void save(@RequestBody Image image) {
        imageRepository.save(image);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Iterable<Image> findAllByStatus(Long id) {
        return imageRepository.findAllByStatus(id);
    }

    @Transactional
    @Override
    public void deleteAllByStatus(Long statusId) {
        imageRepository.deleteAllByStatusId(statusId);
    }

    @Override
    public Iterable<Image> findAllImageByUserId(Long idUser) {
        return imageRepository.findAllImageByUserId(idUser);
    }

    @Override
    public Iterable<Image> top5ImageByUserId(Long idUser) {
        return imageRepository.top5ImageByUserId(idUser);
    }
}
