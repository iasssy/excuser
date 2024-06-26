package com.jac.project.repository;

import com.jac.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.dao.EmptyResultDataAccessException;

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



    public Long saveUser(User user){
        String sql = "INSERT INTO user_tbl (user_name, user_email, user_password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUser_name(), user.getUser_email(), user.getUser_password());

        return jdbcTemplate.queryForObject("SELECT MAX(user_id) from user_tbl", Long.class);
    }

    public User loginUser(String user_email, String user_password) {
        try {
            User user = getUserByEmail(user_email);
            if (user!=null  && user.getUser_password().equals(user_password)){
                // user exists, now check password
                String sql = "SELECT * FROM user_tbl WHERE user_email=? AND user_password=?";
                return jdbcTemplate.queryForObject(sql, new UserRowMapper(), user_email, user_password);
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }

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

    private User getUserByEmail(String user_email) {
        try {
            String sql = "SELECT * FROM user_tbl WHERE user_email=?";
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), user_email);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }
}
