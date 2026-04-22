package com.bcp1IO.fnac.dto;

import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.model.Product;
import com.bcp1IO.fnac.model.ProductUser;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    //dto2entity
    public static Product toEntity(ProductDTORequest productDTORequest, Category category, List<ProductUser> productUsers){
        Product product = new Product(productDTORequest.name(), productDTORequest.description(), productDTORequest.price(), category);
        product.setProductUsers(productUsers);
        return product;
    }

    //entetity2DTO
    public static ProductDTOResponse toDtoResponse(Product product){

        List<String> userNames = product.getProductUsers().stream() // stream nos permite retornar una secuencia de informacion
                .map(ProductUser::getUsername)
                .collect(Collectors.toList()); // traerse kla colleccion

        String categoryTitle = (product.getCategory() != null) ? product.getCategory().getTitle() : "No hay categoria";

        return new ProductDTOResponse(product.getName(), product.getDescription(), product.getPrice(), categoryTitle, userNames );
    }

}
