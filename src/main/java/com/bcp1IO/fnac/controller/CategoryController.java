package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.model.Category;

import com.bcp1IO.fnac.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryService.getAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category newCategory){
        return categoryService.addCategory(newCategory);
    }


}
