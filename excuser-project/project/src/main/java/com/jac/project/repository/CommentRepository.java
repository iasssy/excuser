package com.jac.project.repository;

import com.jac.project.model.Comment;
import com.jac.project.model.History;
import com.jac.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@CrossOrigin
public class CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    final Long session_user_id = 1L;

    public List<Comment> getAllComments() {
        String sql = "SELECT * FROM comment_tbl WHERE user_id=? ORDER BY saved_at DESC";
        List<Comment> result = jdbcTemplate.query(sql, new CommentRowMapper(), session_user_id);
        return result;
    }

    public Long saveComment(Comment comment){
        String sql = "INSERT INTO comment_tbl  (user_id, excuse_id, excuse_content, category_name, comment_content) VALUES  (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session_user_id, comment.getExcuse_id(), comment.getExcuse_content(), comment.getCategory_name(),
                comment.getComment_content());

        //to see the id of saved comment
        return jdbcTemplate.queryForObject("SELECT MAX(id) from comment_tbl", Long.class);
    }

}
