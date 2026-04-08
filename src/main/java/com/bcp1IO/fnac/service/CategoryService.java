package com.bcp1IO.fnac.service;

import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category newCategory){
        return categoryRepository.save(newCategory);
    }
}
