package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAll();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product newProduct){
        return productService.addProduct(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable int id){
        productService.deleteProduct(id);
    }

    @GetMapping("/products/ASC")
    public List<Product> getAllByOrder(){
        return productService.getAllByOrder();
    }
}
