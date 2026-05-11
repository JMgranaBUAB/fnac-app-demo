package com.bcp1IO.fnac.dto.category;

import com.bcp1IO.fnac.model.Category;

public class CategoryMapper {
    public static Category dtoToEntity(CategoryDTO categoryDTO){
        return new Category(categoryDTO.title(), categoryDTO.description());
    }

    public static CategoryDTO entityToDTO(Category category){
        return new CategoryDTO(category.getTitle(), category.getDescription());
    }
}
