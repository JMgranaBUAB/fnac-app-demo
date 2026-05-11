package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.dto.product.ProductDTORequest;
import com.bcp1IO.fnac.dto.product.ProductDTOResponse;
import com.bcp1IO.fnac.dto.product.ProductMapper;
import com.bcp1IO.fnac.exception.ObjectNotFoundException;
import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.model.ProductUser;
import com.bcp1IO.fnac.service.CategoryService;
import com.bcp1IO.fnac.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAll();
    }

    @PostMapping("/products")
//    public Product createProduct(@RequestBody Product newProduct){
//        return productService.addProduct(newProduct);
//    }
    public ResponseEntity<ProductDTOResponse> createProduct(@RequestBody ProductDTORequest productDTORequest){
        try{
            //mapear-convertir del dto-> entidad
            Optional<Category> optionalCategory = categoryService.findCategory(productDTORequest.categoryId());
            if (optionalCategory.isEmpty()) throw new ObjectNotFoundException("Categoria", productDTORequest.categoryId());

            // obtener los productiUsers a partir de las userIds
            List<ProductUser> productUsers = productService.findProductUsersByIds(productDTORequest.userIds());


            // maperar DTP 2 entity de products
            Product newProduct = ProductMapper.toEntity(productDTORequest, optionalCategory.get(), productUsers);

            // guardar el nuevo producto
            Product createProduct = productService.addProduct(newProduct);

            //convertir la entidad de producto en DTO parra dar una respuesta
            ProductDTOResponse newProductDTO = ProductMapper.toDtoResponse(createProduct);

            // retornar
            return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);


            //Product newProduct = new Product(productDTO.name(), productDTO.description(), productDTO.price(), optionalCategory.get());
            //Product newProduct = ProductMapper.toEntity(productDTORequest, optionalCategory.get());

            //Product createdProduct = productService.addProduct(newProduct);

            //transformar Entity -> DTO antes de mandarlo en json
            //ProductDTO newProductDTO = ProductMapper.entity2DTO(createdProduct);
            //ProductDTOResponse newProductDTO = ProductMapper.toDtoResponse(createdProduct);

            //return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
            //return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);

        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //throw new ObjectNotFoundException("Este Producto que contiene la ID " + id + " no existe");
        throw new ObjectNotFoundException("Producto", id);

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
