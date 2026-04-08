package com.bcp1IO.fnac.repository;

import com.bcp1IO.fnac.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
