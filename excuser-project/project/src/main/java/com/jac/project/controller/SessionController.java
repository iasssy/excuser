package com.jac.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@CrossOrigin
public class SessionController {
    private Long globalSessionUserId;

    @GetMapping("/{userId}")
    public ResponseEntity<String> storeSessionUserId(@PathVariable Long userId) {
        globalSessionUserId = userId; // Store session user ID globally
        return ResponseEntity.ok("Session user ID stored successfully.");
    }

    @GetMapping("/current")
    public ResponseEntity<Long> getCurrentSessionUserId() {
        return ResponseEntity.ok(globalSessionUserId); // Retrieve current session user ID
    }
}
