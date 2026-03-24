package com.bcp1IO.fnac.repository;

import com.bcp1IO.fnac.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {



}
