package com.jac.project.service;

import com.jac.project.model.History;
import com.jac.project.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository repository;

    public List<History> getAllHistory(){
        return repository.getAllHistory();
    }
    public Long saveHistory(Long session_user_id, Long excuse_id, String excuse_content, String category_name){
        return repository.saveHistory(session_user_id, excuse_id, excuse_content, category_name);
    }

    public void deleteHistoryWithId(Long history_id){
        repository.deleteHistoryWithId(history_id);
    }

}
