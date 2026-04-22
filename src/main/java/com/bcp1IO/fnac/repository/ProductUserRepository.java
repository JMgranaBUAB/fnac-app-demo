package com.bcp1IO.fnac.repository;

import com.bcp1IO.fnac.model.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductUserRepository extends JpaRepository<ProductUser, Integer> {

    List<ProductUser> findByIdIn(List<Integer> ids);

}
