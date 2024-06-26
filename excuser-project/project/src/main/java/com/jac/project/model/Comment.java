package com.jac.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long comment_id;
    private Long user_id;
    private Long excuse_id;
    private String excuse_content;
    private String category_name;
    private String comment_content;
    private LocalDateTime saved_at;
}
