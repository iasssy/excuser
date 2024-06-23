package com.jac.project.repository;

import com.jac.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategories() {
        String sql = "SELECT category_id, category_name FROM category_tbl";
        List<Category> result = jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new Category(rs.getLong("category_id"),
                                rs.getString("category_name"))
        );
        return result;
    }
}
