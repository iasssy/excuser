package com.jac.project.service;

import com.jac.project.adapter.ExcuserAdapter;
import com.jac.project.model.Excuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcuserService {
    @Autowired
    ExcuserAdapter excuserAdapter;

    public Excuse getRandomExcuse(){
        return excuserAdapter.getRandomExcuse();
    }
}
