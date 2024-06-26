package com.jac.project.repository;

import com.jac.project.model.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryRowMapper implements RowMapper<History> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {

        History history = new History();
        history.setHistory_id(rs.getLong("history_id"));
        history.setUser_id(rs.getLong("user_id"));
        history.setExcuse_id(rs.getLong("excuse_id"));

        String dateTimeString = rs.getString("saved_at"); // Replace with your actual timestamp column name
        history.setSaved_at(LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER));

        return history;
    }
}
