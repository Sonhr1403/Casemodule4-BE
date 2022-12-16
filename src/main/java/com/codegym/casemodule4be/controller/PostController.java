package com.codegym.casemodule4be.controller;

import com.codegym.casemodule4be.model.Image;
import com.codegym.casemodule4be.model.Post;
import com.codegym.casemodule4be.model.User;
import com.codegym.casemodule4be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/postes")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    LikePostService likePostService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<ArrayList<?>> findAll(@RequestParam("currentId") Long currentId) {
        ArrayList<Iterable> result = new ArrayList<>();
        ArrayList<Post> postOwner = (ArrayList<Post>) postService.findAllByOwner(currentId);
        ArrayList<Post> postFriend = (ArrayList<Post>) postService.findAllByOwnerFriend(currentId);
        ArrayList<Post> postStranger = (ArrayList<Post>) postService.findAllByStranger(currentId);
        ArrayList<Post> listPosts = new ArrayList<>();
        listPosts.addAll(postOwner);
        listPosts.addAll(postFriend);
        listPosts.addAll(postStranger);
        result.add(listPosts);
        ArrayList<Iterable<Image>> listImage = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        ArrayList<Integer> listNumberOfComment = new ArrayList<>();
        for (Post post : listPosts) {
            Iterable<Image> images = imageService.findAllByPost(post.getId());
            listImage.add(images);
            Integer numberOfLike = likePostService.findNumberOfLikeByPost(post.getId());
            if (numberOfLike == null) {
                numberOfLike = 0;
            }
            listNumberOfLike.add(numberOfLike);
            Integer numberOfComment = commentService.findNumberOfComment(post.getId());
            if (numberOfComment == null) {
                numberOfComment = 0;
            }
            listNumberOfComment.add(numberOfComment);
        }
        result.add(listImage);
        result.add(listNumberOfLike);
        result.add(listNumberOfComment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> saveStatus(@Valid @RequestBody Post post) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        post.setCreateAt(LocalDateTime.now());
        post.setOwner(user);
        postService.save(post);
        return new ResponseEntity(postService.findLastPost(), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArrayList<?>> findById(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ArrayList<Iterable> result = new ArrayList<>();
        ArrayList<Optional<Post>> statuses = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        statuses.add(post);
        result.add(statuses);
        Iterable<Image> images = imageService.findAllByPost(post.get().getId());
        result.add(images);
        Integer numberOfLike = likePostService.findNumberOfLikeByPost(post.get().getId());
        if (numberOfLike == null) {
            numberOfLike = 0;
        }
        listNumberOfLike.add(numberOfLike);
        result.add(listNumberOfLike);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updateStatus(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> oldPostOptional = postService.findById(id);
        if (!oldPostOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        giữ nguyên đối tượng ko thay đổi
        post.setId(oldPostOptional.get().getId());
        post.setOwner(oldPostOptional.get().getOwner());
        post.setCreateAt(oldPostOptional.get().getCreateAt());
        postService.save(post);
        imageService.deleteAllByPost(post.getId());
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deleteComment(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        Post post = postOptional.get();
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find-all-by-user/{id}")
    public ResponseEntity<ArrayList<?>> findAllByUser(@PathVariable Long id) {
        ArrayList<Iterable> result = new ArrayList<>();
        Iterable<Post> listStatus = postService.findAllByOwner(id);
        result.add(listStatus);
        ArrayList<Iterable<Image>> listImage = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        ArrayList<Integer> listNumberOfComment = new ArrayList<>();
        for (Post post : listStatus) {
            Iterable<Image> images = imageService.findAllByPost(post.getId());
            listImage.add(images);
            Integer numberOfLike = likePostService.findNumberOfLikeByPost(post.getId());
            if (numberOfLike == null) {
                numberOfLike = 0;
            }
            listNumberOfLike.add(numberOfLike);
            Integer numberOfComment = commentService.findNumberOfComment(post.getId());
            if (numberOfComment == null) {
                numberOfComment = 0;
            }
            listNumberOfComment.add(numberOfComment);
        }
        result.add(listImage);
        result.add(listNumberOfLike);
        result.add(listNumberOfComment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
