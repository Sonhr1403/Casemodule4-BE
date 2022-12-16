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
@RequestMapping("/like-statuses")
public class LikeStatusController {
    @Autowired
    LikePostServiceImpl likeStatusService;

    @Autowired
    PostServiceImpl statusService;

    @Autowired
    UserServiceImpl userService;

    private boolean checkLikeStatus(User user, Post post, Iterable<LikePost> likeStatuses) {
        for (LikePost i : likeStatuses) {
            if (i.getStatus() == post && i.getUserLike() == user && i.getId() == null) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("")
    public ResponseEntity<LikePost> likeStatus(@RequestParam Long idStatus, @RequestParam Long idUser) {
        LikePost likePost = new LikePost();
        Post post = statusService.findById(idStatus).get();
        User userOptional = userService.findById(idUser).get();
        LikePost likeStatuses = likeStatusService.findByUserLikeIdAndPostId(userOptional.getId(), post.getId());
        if (checkLikeStatus(userOptional, post, likeStatusService.findAll())) {
            if (likeStatuses == null) {
                likePost.setUserLike(userOptional);
                likePost.setStatus(post);
                likeStatusService.save(likePost);
            } else {
                likeStatusService.delete(likeStatuses.getId());
            }
        }
        return new ResponseEntity<>(likePost, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity check(@RequestParam Long idStatus, @RequestParam Long idUser) {
        Post post = statusService.findById(idStatus).get();
        User userOptional = userService.findById(idUser).get();
        LikePost likeStatuses = likeStatusService.findByUserLikeIdAndPostId(userOptional.getId(), post.getId());
        if (likeStatuses == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        } else
            return new ResponseEntity<>(true, HttpStatus.OK);
    }
}