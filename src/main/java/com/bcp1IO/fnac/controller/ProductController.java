package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id){
        Optional<Product> foundProduct = productService.findProduct(id);

        if(foundProduct.isPresent()){
            return new ResponseEntity<>(foundProduct.get(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product updatedProduct){
        try{
            //actualizo los campos si existe el id en la BBDD
            Product product = productService.updateProduct(id, updatedProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
