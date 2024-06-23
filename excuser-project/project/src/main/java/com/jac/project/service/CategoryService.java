package com.jac.project.service;

import com.jac.project.model.Category;
import com.jac.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> getAllCategories() {
        return repository.getAllCategories();
    }

    public Category getCategoryById(Long id){
        return repository.getCategoryById(id);
    }
}
