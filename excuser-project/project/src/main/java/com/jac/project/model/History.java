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
public class History {
    private Long history_id;
    private Long user_id;
    private Long excuse_id;
    private String excuse_content;
    private String category_name;
    private LocalDateTime saved_at;
}
