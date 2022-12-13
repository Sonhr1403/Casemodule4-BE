package com.codegym.casemodule4be.controller;

import com.codegym.casemodule4be.model.LikeStatus;
import com.codegym.casemodule4be.model.Status;
import com.codegym.casemodule4be.model.User;
import com.codegym.casemodule4be.service.impl.LikeStatusServiceImpl;
import com.codegym.casemodule4be.service.impl.StatusServiceImpl;
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
    LikeStatusServiceImpl likeStatusService;

    @Autowired
    StatusServiceImpl statusService;

    @Autowired
    UserServiceImpl userService;

    private boolean checkLikeStatus(User user, Status status, Iterable<LikeStatus> likeStatuses) {
        for (LikeStatus i : likeStatuses) {
            if (i.getStatus() == status && i.getUserLike() == user && i.getId() == null) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("")
    public ResponseEntity<LikeStatus> likeStatus(@RequestParam Long idStatus, @RequestParam Long idUser) {
        LikeStatus likeStatus = new LikeStatus();
        Status status = statusService.findById(idStatus).get();
        User userOptional = userService.findById(idUser).get();
        LikeStatus likeStatuses = likeStatusService.findByUserLikeIdAndAndStatusId(userOptional.getId(), status.getId());
        if (checkLikeStatus(userOptional, status, likeStatusService.findAll())) {
            if (likeStatuses == null) {
                likeStatus.setUserLike(userOptional);
                likeStatus.setStatus(status);
                likeStatusService.save(likeStatus);
            } else {
                likeStatusService.delete(likeStatuses.getId());
            }
        }
        return new ResponseEntity<>(likeStatus, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity check(@RequestParam Long idStatus, @RequestParam Long idUser) {
        Status status = statusService.findById(idStatus).get();
        User userOptional = userService.findById(idUser).get();
        LikeStatus likeStatuses = likeStatusService.findByUserLikeIdAndAndStatusId(userOptional.getId(), status.getId());
        if (likeStatuses == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        } else
            return new ResponseEntity<>(true, HttpStatus.OK);
    }
}