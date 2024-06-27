package com.jac.project.service;

import com.jac.project.model.User;
import com.jac.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long saveUser(User user) {
        if (userRepository.userExists(user.getUser_email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.saveUser(user);
    }

    // to log in
    public User loginUser(String user_email, String user_password) {
        return userRepository.loginUser(user_email, user_password);
    }
}
