package com.bcp1IO.fnac.service;

import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product addProduct(Product newProduct){
        return productRepository.save(newProduct);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllByOrder(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
