package com.jac.project.controller;


import com.jac.project.model.Comment;
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
    public ResponseEntity<List<Comment>> getAllComments(){
        return  new ResponseEntity<>(service.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/id/{comment_id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long comment_id){
        try{
            return new ResponseEntity<>(service.getCommentById(comment_id), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save/")
    public ResponseEntity<Long> saveComment(@RequestBody Comment comment){
        try{
            return new ResponseEntity(service.saveComment(comment), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long comment_id){
        try{
            service.deleteCommentById(comment_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{comment_id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long comment_id, @RequestParam String comment_content){
        try {
            service.updateComment(comment_id, comment_content);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
