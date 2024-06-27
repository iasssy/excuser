package com.jac.project.service;

import com.jac.project.model.Comment;
import com.jac.project.model.History;
import com.jac.project.repository.CommentRepository;
import com.jac.project.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;

    public List<Comment> getAllComments(){
        return repository.getAllComments();
    }
    public Long saveComment(Comment comment){
        return repository.saveComment(comment);
    }

    public Comment getCommentById(Long commentId) {
        return repository.getCommentById(commentId);
    }

    public void deleteCommentById(Long comment_id){
        repository.deleteCommentById(comment_id);
    }

    public void updateComment(Long comment_id, String comment_content) {
        repository.updateComment(comment_id, comment_content);
    }

}
