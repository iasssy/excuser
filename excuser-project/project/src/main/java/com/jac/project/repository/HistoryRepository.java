package com.jac.project.repository;

import com.jac.project.model.History;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Component
@CrossOrigin
public class HistoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //final Long session_user_id = 1L;

    public List<History> getAllHistory(Long session_user_id) {
       /* Long userId = sessionData.getSessionUserId();
        if (userId == null) {
            throw new IllegalStateException("Session user ID not found.");
        }*/
        String sql = "SELECT * FROM history_tbl WHERE user_id=? ORDER BY history_id DESC";
        List<History> result = jdbcTemplate.query(sql, new HistoryRowMapper(), session_user_id);
        return result;
    }

    public History getHistoryById(Long history_id) {
        String sql = "SELECT * FROM history_tbl WHERE history_id=?";
        History result = jdbcTemplate.queryForObject(sql, new HistoryRowMapper(), history_id);
        return result;
    }

    public Long saveHistory(Long session_user_id, Long excuse_id, String excuse_content, String category_name){
        String sql = "INSERT INTO history_tbl (user_id, excuse_id, excuse_content, category_name) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, session_user_id, excuse_id, excuse_content, category_name);
        return jdbcTemplate.queryForObject("SELECT MAX(history_id) from history_tbl", Long.class);
    }

    public void deleteHistoryWithId(Long history_id){
        String sql="DELETE FROM history_tbl WHERE history_id=?";
        jdbcTemplate.update(sql, history_id);
    }

}
