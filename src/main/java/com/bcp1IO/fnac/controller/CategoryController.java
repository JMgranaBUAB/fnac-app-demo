package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.dto.category.CategoryDTO;
import com.bcp1IO.fnac.dto.category.CategoryMapper;
import com.bcp1IO.fnac.exception.ObjectNotFoundException;
import com.bcp1IO.fnac.model.Category;

import com.bcp1IO.fnac.service.CategoryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTO);
        Category createdCategory = categoryService.addCategory(newCategory);
        CategoryDTO createdCategoryDTO = CategoryMapper.entityToDTO(createdCategory);

        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);

        //return categoryService.addCategory(newCategory);
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
