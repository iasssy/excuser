package com.jac.project.repository;

import com.jac.project.model.History;
import com.jac.project.service.HistoryService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Component
public class HistoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    final Long session_user_id = 1L;

    public List<History> getAllHistory() {
        String sql = "SELECT * FROM history_tbl WHERE user_id=? ORDER BY saved_at DESC";
        List<History> result = jdbcTemplate.query(sql, new HistoryRowMapper(), session_user_id);
        return result;
    }

    public Long saveHistory(Long session_user_id, Long excuse_id){
        String sql = "INSERT INTO history_tbl (user_id, excuse_id) VALUES (?, ?);";
        var result = jdbcTemplate.update(sql, new HistoryRowMapper(), session_user_id, excuse_id);
        return jdbcTemplate.queryForObject("SELECT MAX(id) from history_tbl", Long.class);
    }

}
