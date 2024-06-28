package com.jac.project.controller;

import com.jac.project.model.User;
import com.jac.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<Long> saveUser(@RequestBody User user){
        try {
            Long userId = userService.saveUser(user);
            return new ResponseEntity<>(userId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String user_email, @RequestParam String user_password) {
        try {
            User result = userService.loginUser(user_email, user_password);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                // 401, if login fails
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
