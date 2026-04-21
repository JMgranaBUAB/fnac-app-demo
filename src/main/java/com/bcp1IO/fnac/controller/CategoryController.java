package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.exception.ObjectNotFoundException;
import com.bcp1IO.fnac.model.Category;

import com.bcp1IO.fnac.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable int id){
        Optional<Category> foundCategory = categoryService.findCategory(id);

        if(foundCategory.isPresent()){
            return new ResponseEntity<>(foundCategory.get(), HttpStatus.FOUND);
        }

        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //throw new ProductNotFoundException("Este Producto que contiene la ID " + id + " no existe");
        throw new ObjectNotFoundException("Categoria", id);

    }

}
