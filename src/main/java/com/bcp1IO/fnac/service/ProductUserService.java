package com.bcp1IO.fnac.service;

import com.bcp1IO.fnac.model.ProductUser;
import com.bcp1IO.fnac.repository.ProductUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductUserService {

    private final ProductUserRepository productUserRepository;

    public ProductUserService(ProductUserRepository productUserRepository) {
        this.productUserRepository = productUserRepository;
    }

    public List<ProductUser> getAllUsers() {
        return productUserRepository.findAll();
    }

    public ProductUser createUser(ProductUser user) {
        return productUserRepository.save(user);
    }

    public Optional<ProductUser> findUserById(int id) {
        return productUserRepository.findById(id);
    }

    public void deleteUser(int id) {
        productUserRepository.deleteById(id);
    }
}
