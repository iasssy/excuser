package com.jac.project.repository;

import com.jac.project.model.Comment;
import com.jac.project.model.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentRowMapper implements RowMapper<Comment> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setComment_id(rs.getLong("comment_id"));
            comment.setUser_id(rs.getLong("user_id"));
            comment.setHistory_id(rs.getLong("history_id"));
            comment.setComment_content(rs.getString("comment_content"));

            String dateTimeString = rs.getString("saved_at");
            comment.setSaved_at(LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER));

            return comment;
        }


}