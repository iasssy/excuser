package com.jac.project.controller;


import com.jac.project.model.Comment;
import com.jac.project.model.History;
import com.jac.project.service.ExcuseService;
import com.jac.project.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin
public class HistoryController {

    @Autowired
    HistoryService service;

    @GetMapping("/")
    public ResponseEntity<List<History>> getAllHistory(){
        return new ResponseEntity<>(service.getAllHistory(), HttpStatus.OK);
    }

    @GetMapping("/id/{history_id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Long history_id){
        try{
            return new ResponseEntity<>(service.getHistoryById(history_id), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveHistory(@RequestParam Long session_user_id, @RequestParam Long excuse_id, @RequestParam String excuse_content, @RequestParam String category_name ){
        try{
            Long history_id=service.saveHistory(session_user_id, excuse_id, excuse_content, category_name);
            return new ResponseEntity<>(history_id, HttpStatus.CREATED);
        } catch(Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{history_id}")
    public ResponseEntity<Void> deleteHistoryWithId(@PathVariable Long history_id){
        try{
            service.deleteHistoryWithId(history_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
