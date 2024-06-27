package com.jac.project.controller;


import com.jac.project.model.Comment;
import com.jac.project.model.History;
import com.jac.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService service;

    @GetMapping("/")
    public List<Comment> getAllComments(){
        return service.getAllComments();
    }

    @PostMapping("/save/")
    public ResponseEntity<Long> saveComment(@RequestBody Comment comment){
        try{
            return new ResponseEntity(service.saveComment(comment), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
