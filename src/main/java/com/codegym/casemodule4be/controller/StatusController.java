package com.codegym.casemodule4be.controller;





import com.codegym.casemodule4be.model.Status;
import com.codegym.casemodule4be.service.CommentService;
import com.codegym.casemodule4be.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/statuses")
public class StatusController {
    @Autowired
    StatusService statusService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    LikeStatusService likeStatusService;
    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<ArrayList<?>> findAll(@RequestParam("currentId") Long currentId) {
        ArrayList<Iterable> result = new ArrayList<>();
        ArrayList<Status> statusOwner = (ArrayList<Status>) statusService.findAllByOwner(currentId);
        ArrayList<Status> statusFriend = (ArrayList<Status>) statusService.findAllByOwnerFriend(currentId);
        ArrayList<Status> statusStranger = (ArrayList<Status>) statusService.findAllByStranger(currentId);
        ArrayList<Status> listStatus = new ArrayList<>();
        listStatus.addAll(statusOwner);
        listStatus.addAll(statusFriend);
        listStatus.addAll(statusStranger);
        result.add(listStatus);
        ArrayList<Iterable<Image>> listImage = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        ArrayList<Integer> listNumberOfComment = new ArrayList<>();
        for (Status status : listStatus) {
            Iterable<Image> images = imageService.findAllByStatus(status.getId());
            listImage.add(images);
            Integer numberOfLike = likeStatusService.findNumberOfLikeByStatus(status.getId());
            if (numberOfLike == null) {
                numberOfLike = 0;
            }
            listNumberOfLike.add(numberOfLike);
            Integer numberOfComment = commentService.findNumberOfComment(status.getId());
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
    public ResponseEntity<Status> saveStatus(@Valid @RequestBody Status status) {
        status.setCreateAt(LocalDateTime.now());
        statusService.save(status);
        return new ResponseEntity(statusService.findLastStatus(), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArrayList<?>> findById(@PathVariable Long id) {
        Optional<Status> status = statusService.findById(id);
        if (!status.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ArrayList<Iterable> result = new ArrayList<>();
        ArrayList<Optional<Status>> statuses = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        statuses.add(status);
        result.add(statuses);
        Iterable<Image> images = imageService.findAllByStatus(status.get().getId());
        result.add(images);
        Integer numberOfLike = likeStatusService.findNumberOfLikeByStatus(status.get().getId());
        if (numberOfLike == null) {
            numberOfLike = 0;
        }
        listNumberOfLike.add(numberOfLike);
        result.add(listNumberOfLike);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        Optional<Status> oldStatusOptional = statusService.findById(id);
        if (!oldStatusOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        giữ nguyên đối tượng ko thay đổi
        status.setId(oldStatusOptional.get().getId());
        status.setOwner(oldStatusOptional.get().getOwner());
        status.setCreateAt(oldStatusOptional.get().getCreateAt());
        statusService.save(status);
        imageService.deleteAllByStatus(status.getId());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Status> deleteComment(@PathVariable Long id) {
        Optional<Status> statusOptional = statusService.findById(id);
        Status status = statusOptional.get();
        if (!statusOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        status.setStatus(0);
        statusService.save(status);
        return new ResponseEntity<>(status, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find-all-by-user/{id}")
    public ResponseEntity<ArrayList<?>> findAllByUser(@PathVariable Long id) {
        ArrayList<Iterable> result = new ArrayList<>();
        Iterable<Status> listStatus = statusService.findAllByOwner(id);
        result.add(listStatus);
        ArrayList<Iterable<Image>> listImage = new ArrayList<>();
        ArrayList<Integer> listNumberOfLike = new ArrayList<>();
        ArrayList<Integer> listNumberOfComment = new ArrayList<>();
        for (Status status : listStatus) {
            Iterable<Image> images = imageService.findAllByStatus(status.getId());
            listImage.add(images);
            Integer numberOfLike = likeStatusService.findNumberOfLikeByStatus(status.getId());
            if (numberOfLike == null) {
                numberOfLike = 0;
            }
            listNumberOfLike.add(numberOfLike);
            Integer numberOfComment = commentService.findNumberOfComment(status.getId());
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
