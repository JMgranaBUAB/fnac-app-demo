package com.bcp1IO.fnac.dto;

import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.model.Product;

public class ProductMapper {

    //dto2entity
    public static Product dto2Entity(ProductDTORequest productDTORequest, Category category){
        return new Product(productDTORequest.name(), productDTORequest.description(), productDTORequest.price(), category);
    }

    //entetity2DTO
    public static ProductDTOResponse entity2DTO(Product product){
        return new ProductDTOResponse(product.getName(), product.getDescription(), product.getPrice(), product.getCategory().getTitle());
    }

}
