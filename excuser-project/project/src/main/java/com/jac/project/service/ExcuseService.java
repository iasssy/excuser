package com.jac.project.service;

import com.jac.project.adapter.ExcuseAdapter;
import com.jac.project.model.Excuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcuseService {
    @Autowired
    ExcuseAdapter excuseAdapter;

    public Excuse getRandomExcuse(){
        return excuseAdapter.getRandomExcuse();
    }


    public Excuse getExcuseById(Long id){
        return excuseAdapter.getExcuseById(id);
    }

    public List<Excuse> getListRandomExcuses(int number){
        return excuseAdapter.getListRandomExcuses(number);
    }

}
