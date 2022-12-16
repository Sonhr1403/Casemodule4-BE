package com.codegym.casemodule4be.controller;

import com.codegym.casemodule4be.model.LikePost;
import com.codegym.casemodule4be.model.Post;
import com.codegym.casemodule4be.model.User;
import com.codegym.casemodule4be.service.impl.LikePostServiceImpl;
import com.codegym.casemodule4be.service.impl.PostServiceImpl;
import com.codegym.casemodule4be.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/like-postes")
public class LikePostController {
    @Autowired
    LikePostServiceImpl likeStatusService;

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserServiceImpl userService;

    private boolean checkLikePost(User user, Post post, Iterable<LikePost> likePostes) {
        for (LikePost i : likePostes) {
            if (i.getStatus() == post && i.getUserLike() == user && i.getId() == null) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("")
    public ResponseEntity<LikePost> likeStatus(@RequestParam Long idPost, @RequestParam Long idUser) {
        LikePost likePost = new LikePost();
        Post post = postService.findById(idPost).get();
        User userOptional = userService.findById(idUser).get();
        LikePost likePost1 = likeStatusService.findByUserLikeIdAndPostId(userOptional.getId(), post.getId());
        if (checkLikePost(userOptional, post, likeStatusService.findAll())) {
            if (likePost1 == null) {
                likePost.setUserLike(userOptional);
                likePost.setStatus(post);
                likeStatusService.save(likePost);
            } else {
                likeStatusService.delete(likePost1.getId());
            }
        }
        return new ResponseEntity<>(likePost, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity check(@RequestParam Long idPost, @RequestParam Long idUser) {
        Post post = postService.findById(idPost).get();
        User userOptional = userService.findById(idUser).get();
        LikePost likeStatuses = likeStatusService.findByUserLikeIdAndPostId(userOptional.getId(), post.getId());
        if (likeStatuses == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        } else
            return new ResponseEntity<>(true, HttpStatus.OK);
    }
}