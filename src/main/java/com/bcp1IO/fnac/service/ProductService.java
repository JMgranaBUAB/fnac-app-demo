package com.bcp1IO.fnac.service;

import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.repository.CategoryRepository;
import com.bcp1IO.fnac.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product addProduct(Product newProduct){
        int categoryId = newProduct.getCategory().getId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            newProduct.setCategory(category);
            return productRepository.save(newProduct);
        }

        throw new RuntimeException("Category not found");

        //return productRepository.save(newProduct);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllByOrder(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Product> findProduct(int id){
        return productRepository.findById(id);
    }


    public Product updateProduct(int id, Product updatedProduct){

        //verificamos que aun existe el objeto despues de rellenar el formulario
        Optional<Product> foundProduct = productRepository.findById(id);

        if(foundProduct.isPresent()){
            Product existingProduct = foundProduct.get();

            //Actualizamos los campos
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            //guardar el producto
            return productRepository.save(existingProduct);

        }

        //si no existe el producto, enviar un mensaje de error al usuario
        throw new RuntimeException("Product not found in DDBB with id : " + id);
    }
}
