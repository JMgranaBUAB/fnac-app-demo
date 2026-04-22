package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.exception.ObjectNotFoundException;
import com.bcp1IO.fnac.model.ProductUser;
import com.bcp1IO.fnac.service.ProductUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") //definir le url de manejo desde la api
public class ProductUserController {

    private final ProductUserService productUserService;

    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    @GetMapping
    public List<ProductUser> getAllUsers() {
        return productUserService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<ProductUser> createUser(@RequestBody ProductUser user) {
        ProductUser newUser = productUserService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductUser> getUserById(@PathVariable int id) {
        return productUserService.findUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new ObjectNotFoundException("User", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        productUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
