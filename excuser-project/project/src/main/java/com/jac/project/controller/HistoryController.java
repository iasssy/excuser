package com.jac.project.controller;


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
    public List<History> getAllCategories(){
        return service.getAllHistory();
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveHistory(@RequestParam Long session_user_id, @RequestParam Long excuse_id){
        try{
            Long history_id=service.saveHistory(session_user_id, excuse_id);
            return new ResponseEntity<>(history_id, HttpStatus.CREATED);
        } catch(Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
