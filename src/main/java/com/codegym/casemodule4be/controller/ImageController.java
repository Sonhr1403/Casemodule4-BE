package com.codegym.casemodule4be.controller;

import com.codegym.casemodule4be.model.Image;
import com.codegym.casemodule4be.service.ImageService;
import com.codegym.casemodule4be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    PostService postService;


    @GetMapping
    public ResponseEntity<Iterable<Image>> findAllImage() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> saveImage(@Valid @RequestBody Image image) {
        imageService.save(image);
        return new ResponseEntity(imageService, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Image> findByIdImage(@PathVariable Long id) {
        return new ResponseEntity(imageService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        image.setId(imageOptional.get().getId());
        imageService.save(image);
        return new ResponseEntity(image, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{idUser}/find-all-image-by-user")
    public ResponseEntity<Iterable<Image>> findAllImageByUserId(@PathVariable("idUser") Long idUser){
        return new ResponseEntity(imageService.findAllImageByUserId(idUser),HttpStatus.OK);
    }

    @GetMapping("{idUser}/top5-image-by-user")
    public ResponseEntity<Iterable<Image>> top5ImageByUserId(@PathVariable("idUser") Long idUser){
        return new ResponseEntity(imageService.top5ImageByUserId(idUser),HttpStatus.OK);
    }
}
