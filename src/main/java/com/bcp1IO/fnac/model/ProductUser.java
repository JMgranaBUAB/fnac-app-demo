package com.bcp1IO.fnac.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_user")
public class ProductUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;


    //montamos la many2many
    @ManyToMany(mappedBy = "productUsers")
    @JsonIgnoreProperties("product")
    private List<Product> products = new ArrayList<>();



    public ProductUser(String username) {
        this.username = username;
    }

    public ProductUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProductUsers() {
        return products;
    }

    public void setProductUsers(List<Product> products) {
        this.products = products;
    }

}
