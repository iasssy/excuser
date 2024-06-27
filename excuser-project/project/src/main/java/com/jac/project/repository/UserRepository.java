package com.jac.project.repository;

import com.jac.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Component
@CrossOrigin
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user_tbl ORDER BY user_id ASC";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User getUserById(Long user_id) {
        String sql = "SELECT * FROM user_tbl WHERE user_id=?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), user_id);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user_tbl WHERE user_name=?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
    }

    public Long saveUser(User user){
        String sql = "INSERT INTO user_tbl (user_name, user_email, user_password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUser_name(), user.getUser_email(), user.getUser_password());

        return jdbcTemplate.queryForObject("SELECT MAX(user_id) from user_tbl", Long.class);
    }

    public User loginUser(String user_email, String user_password) {
        String sql = "SELECT * FROM user_tbl WHERE user_email=? AND user_password=?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), user_email, user_password);
    }

    public void deleteUserById(Long user_id){
        String sql = "DELETE FROM user_tbl WHERE user_id=?";
        jdbcTemplate.update(sql, user_id);
    }

    public boolean userExists(String user_email) {
        String sql = "SELECT COUNT(*) FROM user_tbl WHERE user_email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,  Integer.class, user_email);
        return count != null && count > 0;
    }
}
