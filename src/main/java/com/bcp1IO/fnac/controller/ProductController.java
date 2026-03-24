package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
